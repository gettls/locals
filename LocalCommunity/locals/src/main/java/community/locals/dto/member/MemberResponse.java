package community.locals.dto.member;

import com.querydsl.core.annotations.QueryProjection;

import lombok.Data;

@Data
public class MemberResponse {
	String username;
	
	
	@QueryProjection
	public MemberResponse(String username) {
		this.username = username;
	}
}
