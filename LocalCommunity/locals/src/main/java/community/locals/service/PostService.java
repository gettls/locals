package community.locals.service;

import org.springframework.stereotype.Service;

import community.locals.domain.Post;
import community.locals.dto.PostRegister;
import community.locals.repository.PostRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class PostService {
	
	private final PostRepository postRepository;
	
	public void register(PostRegister postRegister) {
		postRepository.save(new Post(postRegister));
	}
}
