package community.locals.dto.post;

import com.querydsl.core.annotations.QueryProjection;

import community.locals.domain.Member;
import community.locals.dto.member.MemberResponse;
import lombok.Data;

@Data
public class PostResponse {
	
	String contents;
	String title;
	
	@QueryProjection
	public PostResponse(String contents, String title) {
		this.contents = contents;
		this.title = title;
	}
	
}
