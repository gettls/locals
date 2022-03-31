package community.locals.config.jwt;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/*
 * Header의 Authorization에 JWT가 있으면,
 * 해당 JWT를 파싱해서 검증을 하는 절차를 거친다.
 * 
 * SecurityContextHolder에 Authentication 객체(UsernamePasswordToken)를
 * 집어 넣음으로써 인증이 완료된다. 
 */

@Slf4j
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {
	
	private final JwtUtils jwtUtils;
	

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		log.info("jwt Filter...");
		String jwtToken = jwtUtils.parseJwt(request);
		log.info("jwtToken = {}",jwtToken);
		if (jwtToken != null && jwtUtils.validateToken(jwtToken)) {
			Authentication auth = jwtUtils.getAuthentication(jwtToken);
			SecurityContextHolder.getContext().setAuthentication(auth);
		}
		log.info("next Filter");
		filterChain.doFilter(request, response);
	}
}