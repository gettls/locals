package community.locals.dto.post;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.ConstructorExpression;
import javax.annotation.processing.Generated;

/**
 * community.locals.dto.post.QPostResponse is a Querydsl Projection type for PostResponse
 */
@Generated("com.querydsl.codegen.DefaultProjectionSerializer")
public class QPostResponse extends ConstructorExpression<PostResponse> {

    private static final long serialVersionUID = 1956394559L;

    public QPostResponse(com.querydsl.core.types.Expression<String> contents, com.querydsl.core.types.Expression<String> title) {
        super(PostResponse.class, new Class<?>[]{String.class, String.class}, contents, title);
    }

}

