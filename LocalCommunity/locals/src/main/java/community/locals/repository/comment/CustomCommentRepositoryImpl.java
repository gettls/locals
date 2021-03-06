
package community.locals.repository.comment;

import static community.locals.domain.QPost.post;
import static community.locals.exception.PostNotExistException.POST_NOT_EXCEPTION;
import static community.locals.domain.QMember.member;
import static community.locals.exception.MemberNotExistException.MEMBER_NOT_EXISTS;
import static community.locals.domain.QComment.comment;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.querydsl.jpa.impl.JPAQueryFactory;

import community.locals.domain.Comment;
import community.locals.domain.Member;
import community.locals.domain.Post;
import community.locals.dto.comment.CommentResponse;
import community.locals.dto.comment.QCommentResponse;
import community.locals.exception.MemberNotExistException;
import community.locals.exception.PostNotExistException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Slf4j
public class CustomCommentRepositoryImpl implements CustomCommentRepository{

	private final JPAQueryFactory jpaQueryFactory;
	
	@Override
	public List<CommentResponse> findCommentsByTitle(String postTitle) {

			List<Comment> comments = findPostByTitle(postTitle).get().getComments();
			
			List<CommentResponse> result = new ArrayList<>();
			comments.forEach(c -> {
				result.add(new CommentResponse(c.getContents(), c.getCreatedDate(), c.getMember().getUsername()));
			});
			
			return result;
	}
	
	private Optional<Post> findPostByTitle(String postTitle){
		Optional<Post> findPost = Optional.ofNullable(jpaQueryFactory
				.selectFrom(post)
				.where(post.title.eq(postTitle))
				.fetchOne());
		
		findPost.orElseThrow(()->new PostNotExistException(POST_NOT_EXCEPTION));
		
		return findPost;
	}

	@Override
	public List<CommentResponse> findByUsername(String username) {
		
		Member findMember = findMemberByUsername(username).get();
		
		return	jpaQueryFactory
						.select(new QCommentResponse(
								comment.contents, comment.createdDate, comment.createdBy))
						.from(comment)
						.where(comment.member.eq(findMember))
						.fetch();
	}
	
	private Optional<Member> findMemberByUsername(String username){
		Optional<Member> findMember = Optional.ofNullable(jpaQueryFactory.selectFrom(member)
								.where(member.username.eq(username))
								.fetchOne());
		findMember.orElseThrow(() -> new MemberNotExistException(MEMBER_NOT_EXISTS));
		
		return findMember;
	}

}
