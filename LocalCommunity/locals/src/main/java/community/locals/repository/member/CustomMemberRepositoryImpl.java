package community.locals.repository.member;

import static community.locals.domain.QMember.member;
import static community.locals.domain.QPost.post;
import community.locals.dto.member.QMemberResponse.*;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import com.querydsl.jpa.impl.JPAQueryFactory;

import community.locals.domain.Member;
import community.locals.domain.Post;
import community.locals.dto.member.MemberResponse;
import community.locals.dto.member.QMemberResponse;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CustomMemberRepositoryImpl implements CustomMemberRepository{

	private final JPAQueryFactory jpaQueryFactory;
	private final EntityManager em;
	
	@Override
	public Page<MemberResponse> findAllSortedByCreateDate(Pageable pageable) {
		// member : posts 제외한 부분 조회
		List<MemberResponse> contents = jpaQueryFactory
							.select(new QMemberResponse(
										member.username))
							.from(member)
							.orderBy(member.createdDate.desc())
							.offset(pageable.getOffset())
							.limit(pageable.getPageSize())
							.fetch();
		
		contents.forEach(
				m -> {
					List<Post> posts = findPosts(m.getUsername());
					m.setPosts(posts);
				});
		
		
		long total = contents.size();
		return new PageImpl<>(contents, pageable, total);
	}

	// post : username 을 기준으로 조회
	private List<Post> findPosts(String username){
		return jpaQueryFactory
							.selectFrom(post)
							.leftJoin(post.member, member).fetchJoin()
							.where(post.member.username.eq(username))
							.fetch();
	}
}
