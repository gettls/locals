package community.locals.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import community.locals.domain.Post;

public interface PostRepository extends JpaRepository<Post, Long>{
	
	Optional<Post> findByTitle(String title);
	
}
