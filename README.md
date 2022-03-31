# locals



## 🛠기술스택

### `Spring Boot` `JAVA11` `Spring Security`
### `JPA` `Spring Data JPA` `QueryDsl`
### `H2 Database` 

<hr> 

## 🚀Trouble Shooting

### 1. JwtAuthorizationFilter extends UsernamePasswordAuthenticationFilter 미작동

- username/password를 넣었지만 로그인 시 해당 필터를 거치지 않는 에러 발견
- UsernamePasswordAuthenticationFilter는 미리 있는 세팅되어 있는 url("/login")에서만 동작함

#### 해결 방법
- setFilterProcessesUrl()를 호출해 사용할 url 설정


### 2. UsernameNotfoundException 미작동

- 잘못된 username이 들어와도 `UsernameNotfoundException`가 아닌, `BadCredentialsException`을 던지는 것을 확인
- 의도했던 `UsernameNotfoundException`로 예외 처리가 되지 않음

#### 해결 방법

![image](https://user-images.githubusercontent.com/80764368/161055701-b804592e-b506-4c3f-98bf-03d9214c74e5.png)

- 로직상의 문제가 아닌 Spring Security가 기본 값으로 `BadCredentialsException`를 던지도록 설정되어 있는 것을 확인
- 위 주석에서 알 수 있듯, 해당 이유는 보안상의 문제로 일부로 불명확한 `Exception`을 던짐
- `UsernameNotFoundException`을 사용하기 위해서는 `DaoAuthenticationProvider`의 `setHideUserNotFoundExceptions()`를 호출해 true로 설정함으로써 사용할 수 있음.
