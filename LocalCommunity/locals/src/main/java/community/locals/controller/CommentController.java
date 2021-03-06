package community.locals.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import community.locals.dto.comment.CommentCrud;
import community.locals.dto.comment.CommentResponse;
import community.locals.service.CommentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/comment")
@RequiredArgsConstructor
@Slf4j
public class CommentController {

	private final CommentService commentService;
	
	@PostMapping("/register")
	public String register(@Validated @ModelAttribute CommentCrud commentCrud, String postTitle, HttpServletRequest request) {
		commentService.register(commentCrud, postTitle, request);
		return "comment registered!";
	}
	
	@GetMapping("/search")
	public List<CommentResponse> search(String postTitle) {
		return commentService.search(postTitle);
	}
	
	@GetMapping("/search/{username}")
	public List<CommentResponse> searchByUsername(@PathVariable String username){
		return commentService.searchByUsername(username);
	}
}
