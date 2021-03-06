package community.locals.repository.post;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import community.locals.dto.comment.CommentResponse;
import community.locals.dto.post.PostResponse;

public interface CusotmPostRepository {

	Page<PostResponse> findAllBy(Pageable pageable);
	
}
