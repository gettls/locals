package community.locals.dto;

import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class MemberLogin {
	@NotBlank
	private String username;
	@NotBlank
	private String password;
}
