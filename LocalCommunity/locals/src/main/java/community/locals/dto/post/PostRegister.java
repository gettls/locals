package community.locals.dto.post;

import javax.validation.constraints.NotBlank;

import community.locals.domain.Member;
import community.locals.domain.Post;
import lombok.Data;

@Data
public class PostRegister {
	
	@NotBlank
	private String contents;
	@NotBlank
	private String title;
	
	private Member member;
	
	public Post toEntity() {
		return Post.builder()
					.contents(contents)
					.title(title)
					.member(member)
					.build();
	}
}
