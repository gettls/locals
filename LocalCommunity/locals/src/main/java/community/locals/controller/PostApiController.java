package community.locals.controller;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import community.locals.config.auth.PrincipalDetails;
import community.locals.config.jwt.JwtUtils;
import community.locals.domain.Post;
import community.locals.dto.member.MemberResponse;
import community.locals.dto.post.PostDelete;
import community.locals.dto.post.PostRegister;
import community.locals.dto.post.PostResponse;
import community.locals.exception.PostNotExistException;
import community.locals.service.PostService;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/post")
@AllArgsConstructor
@Slf4j
public class PostApiController {
	
	private final PostService postService;
	private final JwtUtils jwtUtils;
	
	@PostMapping("/register")
	public ResponseEntity<?> register(@Validated @ModelAttribute PostRegister postRegister, HttpServletRequest request) {
		postService.register(postRegister, request);
		return ResponseEntity.ok("post register success");
	}
	
	@GetMapping("/search")
	public Page<Post> findAll(Pageable pageable){
		return postService.findAll(pageable);
	}
	
	@GetMapping("/search/{username}")
	public List<Post> findAll(@PathVariable String username){
		return postService.findAllByUsername(username);
	}
	
	@DeleteMapping("/delete")
	public ResponseEntity<?> delete(@Validated @ModelAttribute PostDelete postDelete, HttpServletRequest request) {
		postService.delete(postDelete, request);
		return ResponseEntity.ok("post delete success");
	}
	
	@GetMapping("/search/createdDate")
	public Page<PostResponse> findAllBy(Pageable pageable){
		Page<PostResponse> result = postService.findAllBy(pageable);
		if(result.getNumberOfElements() == 0) throw new PostNotExistException(PostNotExistException.POST_NOT_EXCEPTION);
		return result;
	}
	
	
	
}
