package community.locals.repository.comment;

import java.util.List;

import community.locals.dto.comment.CommentResponse;

public interface CustomCommentRepository {
	
	List<CommentResponse> findCommentsByTitle (String postTitle);
}
