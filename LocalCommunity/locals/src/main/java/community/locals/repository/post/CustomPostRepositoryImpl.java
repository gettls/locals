package community.locals.repository.post;

import static community.locals.domain.QPost.post;
import static community.locals.domain.QMember.member;
import static community.locals.domain.QComment.comment;
import static community.locals.exception.PostNotExistException.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import com.querydsl.jpa.impl.JPAQueryFactory;

import community.locals.domain.Comment;
import community.locals.domain.Post;
import community.locals.dto.comment.CommentResponse;
import community.locals.dto.comment.QCommentResponse;
import community.locals.dto.member.MemberResponse;
import community.locals.dto.member.QMemberResponse;
import community.locals.dto.post.PostResponse;
import community.locals.dto.post.QPostResponse;
import community.locals.exception.PostNotExistException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Slf4j
public class CustomPostRepositoryImpl implements CusotmPostRepository {

	private final JPAQueryFactory jpaQueryFactory;
	
	@Override
	public Page<PostResponse> findAllBy(Pageable pageable) {
		
		List<PostResponse> result = jpaQueryFactory
								.select(new QPostResponse(
										post.contents, post.title))
								.from(post)
								.leftJoin(post.member, member).fetchJoin()
								.orderBy(post.createdDate.desc())
								.offset(pageable.getOffset())
								.limit(pageable.getPageSize())
								.fetch();
		
		long total = result.size();
		return new PageImpl<>(result, pageable, total);
	}
	
}
