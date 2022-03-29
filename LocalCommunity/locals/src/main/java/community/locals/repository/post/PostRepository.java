package community.locals.repository.post;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import community.locals.domain.Post;

public interface PostRepository extends JpaRepository<Post, Long>, CusotmPostRepository{
	
	Optional<Post> findByTitle(String title);
	
}
