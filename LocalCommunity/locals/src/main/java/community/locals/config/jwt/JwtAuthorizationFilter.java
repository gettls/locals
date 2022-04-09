package community.locals.config.jwt;

import static community.locals.exception.InputNotFoundException.INPUT_NOT_FOUND;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.exc.StreamWriteException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;

import community.locals.config.auth.PrincipalDetails;
import community.locals.dto.exception.ErrorResult;
import community.locals.dto.member.MemberCrud;
import community.locals.exception.InputNotFoundException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class JwtAuthorizationFilter extends UsernamePasswordAuthenticationFilter{
	
	private final AuthenticationManager authenticationManager;
	private final JwtUtils jwtUtils;
	
	public JwtAuthorizationFilter(AuthenticationManager authenticationManager, JwtUtils jwtUtils) {
		this.authenticationManager = authenticationManager;
		this.jwtUtils = jwtUtils;
		
		setFilterProcessesUrl("/api/member/login");
	}
	
	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException {
		log.info("USERNAMEPASSWORD_FILTER");
		
		ObjectMapper om = new ObjectMapper();
		MemberCrud memberCrud = null;
		
		try {
			memberCrud = om.readValue(request.getInputStream(), MemberCrud.class);
		}catch (Exception e) {
			throw new InputNotFoundException(INPUT_NOT_FOUND);
		}
		
		log.info("member : {}", memberCrud);
		
		UsernamePasswordAuthenticationToken authenticationToken = 
				new UsernamePasswordAuthenticationToken(memberCrud.getUsername(), memberCrud.getPassword());
		
		Authentication authentication = authenticationManager.authenticate(authenticationToken);
		
		return authentication;
	}

	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			Authentication authResult) throws IOException, ServletException {
		
		PrincipalDetails principalDetails = (PrincipalDetails) authResult.getPrincipal();
		
		String jwtToken = jwtUtils.generateToken(principalDetails.getUsername());
		
		response.getWriter().write("Bearer " + jwtToken);
		response.getWriter().flush();
	}
}
