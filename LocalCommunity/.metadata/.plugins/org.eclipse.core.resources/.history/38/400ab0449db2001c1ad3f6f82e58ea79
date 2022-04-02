package community.locals.service;

import javax.servlet.http.HttpServletRequest;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import static community.locals.exception.PostNotExistException.*;

import java.util.List;

import community.locals.config.jwt.JwtUtils;
import community.locals.domain.Comment;
import community.locals.domain.Member;
import community.locals.domain.Post;
import community.locals.dto.comment.CommentCrud;
import community.locals.dto.comment.CommentResponse;
import community.locals.exception.PostNotExistException;
import community.locals.repository.comment.CommentRepository;
import community.locals.repository.member.MemberRepository;
import community.locals.repository.post.PostRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CommentService {

	private final JwtUtils jwtUtils;
	private final PostRepository postRepository;
	private final MemberRepository memberRepository;
	private final CommentRepository commentRepository;
	
	public void register(CommentCrud commentCrud, String postTitle, HttpServletRequest request) {
		String username = jwtUtils.getUsername(jwtUtils.parseJwt(request));
		Member findMember = memberRepository.findByUsername(username).get();
		
		Post findPost = postRepository.findByTitle(postTitle)
				.orElseThrow(() -> new PostNotExistException(POST_NOT_EXCEPTION)); 
		
		
		commentCrud.setMember(findMember);
		commentCrud.setPost(findPost);
		commentRepository.save(commentCrud.toEntity());
	}
	
	
	public List<CommentResponse> search(String postTitle) {
		return commentRepository.findCommentsByTitle(postTitle);
	}
	
}
