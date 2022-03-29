package community.locals.dto.member;

import java.util.ArrayList;
import java.util.List;

import com.querydsl.core.annotations.QueryProjection;

import community.locals.domain.Post;
import lombok.Data;

@Data
public class MemberResponse {

	String username;
	List<Post> posts = new ArrayList<>();
	
	@QueryProjection
	public MemberResponse(String username, List<Post> posts) {
		this.username = username;
		this.posts = posts;
	}
	
	@QueryProjection
	public MemberResponse(String username) {
		this.username = username;
	}
}
