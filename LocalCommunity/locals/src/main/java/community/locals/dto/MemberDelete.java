package community.locals.dto;

import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class MemberDelete {
	@NotBlank
	private String username;
	@NotBlank
	private String password;
}
