package community.locals.dto.post;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import community.locals.domain.Member;
import community.locals.domain.Post;
import lombok.Data;

@Data
public class PostRegister {
	
	@NotNull(message = "������ �Է����ּ���")
	private String title;
	@NotNull(message = "������ �Է����ּ���.")
	private String contents;
	
	private Member member;
	
	public Post toEntity() {
		return Post.builder()
					.contents(contents)
					.title(title)
					.member(member)
					.build();
	}
}
