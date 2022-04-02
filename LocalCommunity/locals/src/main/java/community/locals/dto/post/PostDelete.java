package community.locals.dto.post;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import community.locals.domain.Member;
import lombok.Data;

@Data
public class PostDelete {
	
	@NotNull(message = "제목을 입력해주세요")
	private String title;
}
