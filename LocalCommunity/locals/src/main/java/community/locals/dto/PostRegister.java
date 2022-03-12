package community.locals.dto;

import javax.validation.constraints.NotBlank;

import community.locals.domain.Member;
import lombok.Data;

@Data
public class PostRegister {
	
	@NotBlank
	private String contents;
	@NotBlank
	private String title;
	@NotBlank
	private Member member;
}
