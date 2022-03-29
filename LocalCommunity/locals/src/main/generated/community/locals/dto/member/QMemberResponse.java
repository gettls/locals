package community.locals.dto.member;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.ConstructorExpression;
import javax.annotation.processing.Generated;

/**
 * community.locals.dto.member.QMemberResponse is a Querydsl Projection type for MemberResponse
 */
@Generated("com.querydsl.codegen.DefaultProjectionSerializer")
public class QMemberResponse extends ConstructorExpression<MemberResponse> {

    private static final long serialVersionUID = 587361663L;

    public QMemberResponse(com.querydsl.core.types.Expression<String> username, com.querydsl.core.types.Expression<? extends java.util.List<community.locals.domain.Post>> posts) {
        super(MemberResponse.class, new Class<?>[]{String.class, java.util.List.class}, username, posts);
    }

    public QMemberResponse(com.querydsl.core.types.Expression<String> username) {
        super(MemberResponse.class, new Class<?>[]{String.class}, username);
    }

}

