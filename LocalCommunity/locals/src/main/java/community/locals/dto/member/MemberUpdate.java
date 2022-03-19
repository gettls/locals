package community.locals.dto.member;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class MemberUpdate {
	
	@NotBlank
	private String username;
	@NotBlank
	private String password;
	
}
