package community.locals.service;

import javax.servlet.http.HttpServletRequest;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import static community.locals.exception.PostNotExistException.*;
import static community.locals.exception.MemberNotExistException.*;
import static community.locals.exception.PostMismatchUsernameException.*;

import java.util.List;

import community.locals.config.jwt.JwtUtils;
import community.locals.domain.Member;
import community.locals.domain.Post;
import community.locals.dto.member.MemberResponse;
import community.locals.dto.post.PostDelete;
import community.locals.dto.post.PostRegister;
import community.locals.dto.post.PostResponse;
import community.locals.exception.MemberNotExistException;
import community.locals.exception.PostMismatchUsernameException;
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
		Member findMember = memberRepository.findByUsername(username)
				.orElseThrow(() -> new MemberNotExistException(MEMBER_NOT_EXISTS));
		
		postRegister.setMember(findMember);
		postRepository.save(postRegister.toEntity());
	}
	
	public void delete(PostDelete postDelete, HttpServletRequest request) {
		String username = jwtUtils.getUsername(jwtUtils.parseJwt(request));
		Member findMember = memberRepository.findByUsername(username).get();
		
		Post post = postRepository.findByTitle(postDelete.getTitle())
				.orElseThrow(() -> new PostNotExistException(POST_NOT_EXCEPTION));
		
		if(post.getMember().getUsername() != findMember.getUsername()) 
			throw new PostMismatchUsernameException(POST_MISMATCH_USERNAME_EXCEPTION);
		
		postRepository.delete(post);
	}
	
	public Page<Post> findAll(Pageable pageable) {
		return postRepository.findAll(pageable);
	}
	
	public List<Post> findAllByUsername(String username){
		Member findMember = memberRepository.findByUsername(username)
				.orElseThrow(() -> new MemberNotExistException(MEMBER_NOT_EXISTS));
		return postRepository.findByMember(findMember);
	}
	
	// Dynamic Query
	public Page<PostResponse> findAllBy(Pageable pageable){
		return postRepository.findAllBy(pageable);
	}
}
