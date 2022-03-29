package community.locals.dto.member;

import javax.validation.constraints.NotBlank;

import community.locals.domain.Member;
import lombok.Data;

@Data
public class MemberCrud {
	@NotBlank
	private String username;
	@NotBlank
	private String password;
	
	public Member toEntity() {
		return Member.builder()
					.username(username)
					.password(password)
					.build();
	}
}
