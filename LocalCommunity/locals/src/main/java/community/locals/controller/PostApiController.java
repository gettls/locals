package community.locals.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import community.locals.dto.PostRegister;
import community.locals.repository.PostRepository;
import community.locals.service.PostService;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/location")
@AllArgsConstructor
public class PostApiController {
	
	private final PostService postService;
	
}
