package community.locals.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class MemberRegister {
	
	@NotBlank
	private String username;
	@NotBlank
	private String password;
	
}
