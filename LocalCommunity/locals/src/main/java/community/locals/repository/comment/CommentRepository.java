package community.locals.repository.comment;

import org.springframework.data.jpa.repository.JpaRepository;

import community.locals.domain.Comment;

public interface CommentRepository extends JpaRepository<Comment, Long>{

}
