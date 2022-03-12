package community.locals.dto.jwt;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
public class JwtResponse {
	private String token;
	private String type;
	private String username;
}
