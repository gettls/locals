# locals

# 기능

#### 로그인 + JWT를 이용한 인증
- 로그인 시 JWT 토큰 발급
- 재접근 시 JWT 토큰을 이용한 인증 절차
#### 데이터 CRUD를 위한 API Server
- QueryDsl를 활용한 동적쿼리 사용
- 유지보수 및 성능향상을 위한 ORM 활용

<hr>

# 🛠 사용된 기술스택

- `Spring Boot 2.6.6` 
- `JAVA 11` 
- `Spring Security`
- `JPA` 
- `Spring Data JPA` 
- `QueryDsl`
- `H2 Database` 

<hr> 

# 🚀Trouble Shooting

## 1. JwtAuthorizationFilter extends UsernamePasswordAuthenticationFilter 미작동

- username/password를 넣었지만 로그인 시 해당 필터를 거치지 않는 에러 발견

### 해결 방법

- UsernamePasswordAuthenticationFilter는 미리 있는 세팅되어 있는 url("/login")에서만 동작하는 것을 파악
- setFilterProcessesUrl()를 호출해 사용할 url 설정


## 2. UsernameNotfoundException 미작동

- 잘못된 username이 들어와도 `UsernameNotfoundException`가 아닌, `BadCredentialsException`을 던지는 것을 확인
- 의도했던 `UsernameNotfoundException`로 예외 처리가 되지 않음

### 해결 방법

![image](https://user-images.githubusercontent.com/80764368/161055701-b804592e-b506-4c3f-98bf-03d9214c74e5.png)

- 로직상의 문제가 아닌 Spring Security가 기본 값으로 `BadCredentialsException`를 던지도록 설정되어 있는 것을 확인
- 위 주석에서 알 수 있듯, 해당 이유는 보안상의 문제로 일부로 불명확한 `Exception`을 던짐
- `UsernameNotFoundException`을 사용하기 위해서는 `DaoAuthenticationProvider`의 `setHideUserNotFoundExceptions()`를 호출해 true로 설정함으로써 사용할 수 있음.

## 3. Member 페이징 조회 쿼리 시 순환 참조 / 성능 문제 

![image](https://user-images.githubusercontent.com/80764368/161069359-50fa64f5-b4f0-43c1-8fec-4c722eac2e1f.png)

```
@Override
public Page<Member> findAllSortedByCreatedDate(Pageable pageable) {		
		List<Member> result = jpaQueryFactory
								.selectFrom(member)
								.leftJoin(member.posts, post).fetchJoin()
								.orderBy(member.createdDate.desc())
								.offset(pageable.getOffset())
								.limit(pageable.getPageSize())
								.fetch();
		
		return new PageImpl<>(result, pageable, result.size());
}
```

- Member를 CreatedDate를 기준으로 정렬하고 페이징을 사용했는데 순환참조가 발생하는 문제가 발생
- fetchJoin을 통해서 Posts의 내용을 가져오기 때문에 불필요한 데이터가 너무 많이 들어옴
- Member의 필요하지 않은 부분(password)가 노출됨

### 해결 방법

### 1. @JsonIgnore 사용

![image](https://user-images.githubusercontent.com/80764368/161070215-3e67cc73-a502-47b7-a0b6-874714c98eaa.png)

![image](https://user-images.githubusercontent.com/80764368/161070149-3b5af495-3469-4bc0-89dd-752085f39d8a.png)

- @JsonIgnore를 사용하면 조회 시 연관관계의 엔티티의 내용이 null 로 나타나기 때문에 순환참조를 예방할 수 있음
- 사실, 이 방법은 DTO를 사용한다면 필요 없음

### 2. DTO 사용/ 쿼리문 최적화

![image](https://user-images.githubusercontent.com/80764368/161071782-8e213cd6-1db3-4c2b-b142-1b9ec6c0f922.png)

![image](https://user-images.githubusercontent.com/80764368/161071900-7c052ab1-d35c-4be1-82a9-3ca6dabc4145.png)
 
![image](https://user-images.githubusercontent.com/80764368/161071952-7a1430b8-26ac-43ea-afdb-2425a03f77a7.png)
 
 ![image](https://user-images.githubusercontent.com/80764368/161073123-f6fa229d-4109-4fdf-b7fd-89ee77a49dc4.png)

 
 - 필요하지 않은 데이터를 DTO 를 통해서 받아오지 않고 쿼리문을 최적화 시켜서 필요한 데이터만 받아오게 됨
 - member 조회 따로 post 조회 따로 수행한 결과가 오히려 더 나은 결과를 만듦

## 4. 페치조인과 BatchSize

![image](https://user-images.githubusercontent.com/80764368/161077077-0ffb08c8-f422-4953-843c-cd05a8b0f47b.png)

- 일대다의 연관관계에서 일(1)쪽을 기준으로 조회 시 페치조인을 사용하면 중복된 데이터가 DB에 올라오는 것을 확인

### 해결방법

![image](https://user-images.githubusercontent.com/80764368/161076804-39b9d4f4-57cc-4ce7-8941-0a24c4b007c8.png)
![image](https://user-images.githubusercontent.com/80764368/161076893-587824e8-3f95-4199-9a0a-6ce93c69dc8b.png)

- BatchSize를 설정하면 다(N)쪽에서 IN 쿼리문을 통해 일(1)쪽에 해당하는 id 에 대해서만 데이터를 가져온다.
- 다만, 페치조인은 쿼리문이 1번, BatchSize 는 쿼리문이 2번 발생했다. 상황에 따라서 잘 사용하는 것이 필요할듯.
