package community.locals.service;

import javax.servlet.http.HttpServletRequest;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import static community.locals.exception.PostNotExistException.*;

import community.locals.config.jwt.JwtUtils;
import community.locals.domain.Member;
import community.locals.domain.Post;
import community.locals.dto.member.MemberResponse;
import community.locals.dto.post.PostDelete;
import community.locals.dto.post.PostRegister;
import community.locals.dto.post.PostResponse;
import community.locals.exception.PostNotExistException;
import community.locals.repository.member.MemberRepository;
import community.locals.repository.post.PostRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class PostService {
	
	private final PostRepository postRepository;
	private final MemberRepository memberRepository;
	private final JwtUtils jwtUtils;
	
	public void register(PostRegister postRegister,HttpServletRequest request) {
		String username = jwtUtils.getUsername(jwtUtils.parseJwt(request));
		Member findMember = memberRepository.findByUsername(username).get();
		postRepository.save(new Post(postRegister, findMember));
	}
	
	public void delete(PostDelete postDelete) {
		Post post = postRepository.findByTitle(postDelete.getTitle())
						.orElseThrow(() -> new PostNotExistException(POST_NOT_EXCEPTION));
		postRepository.delete(post);
	}
	
	public Page<Post> findAll(Pageable pageable) {
		return postRepository.findAll(pageable);
	}
	
	// Dynamic Query
	public Page<PostResponse> findAllBy(Pageable pageable){
		return postRepository.findAllBy(pageable);
	}
	
}
