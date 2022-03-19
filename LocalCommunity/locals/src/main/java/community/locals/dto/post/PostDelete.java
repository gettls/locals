package community.locals.dto.post;

import javax.validation.constraints.NotBlank;

import community.locals.domain.Member;
import lombok.Data;

@Data
public class PostDelete {
	
	@NotBlank
	private String title;
}
