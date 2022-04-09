package community.locals.config.auth;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import com.fasterxml.jackson.databind.ObjectMapper;

import community.locals.dto.exception.ErrorResult;

public class AuthFailureHandler implements AuthenticationEntryPoint{


	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException authException) throws IOException, ServletException {
		response.setContentType(MediaType.APPLICATION_JSON_VALUE);
		response.setStatus(HttpStatus.UNAUTHORIZED.value());
		response.setCharacterEncoding("UTF-8");
		
		ErrorResult errorResult = new ErrorResult("UNAUTHORIZED", "인증에 실패했습니다");
		
		ObjectMapper objectMapper = new ObjectMapper();
		
		response.getWriter().print(errorResult);
		response.getWriter().flush();
	}

}
