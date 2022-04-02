package community.locals.dto.comment;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.ConstructorExpression;
import javax.annotation.processing.Generated;

/**
 * community.locals.dto.comment.QCommentResponse is a Querydsl Projection type for CommentResponse
 */
@Generated("com.querydsl.codegen.DefaultProjectionSerializer")
public class QCommentResponse extends ConstructorExpression<CommentResponse> {

    private static final long serialVersionUID = -300798815L;

    public QCommentResponse(com.querydsl.core.types.Expression<String> contents, com.querydsl.core.types.Expression<java.time.LocalDateTime> createdDate, com.querydsl.core.types.Expression<String> createdBy) {
        super(CommentResponse.class, new Class<?>[]{String.class, java.time.LocalDateTime.class, String.class}, contents, createdDate, createdBy);
    }

}

