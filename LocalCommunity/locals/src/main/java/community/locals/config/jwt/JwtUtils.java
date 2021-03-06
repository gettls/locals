package community.locals.config.jwt;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Base64;
import java.util.Date;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import community.locals.config.auth.PrincipalDetails;
import community.locals.config.auth.PrincipalDetailsService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


@RequiredArgsConstructor
@Component
@Slf4j
public class JwtUtils {
	
	private final long VALID_MILISECOND = 1000L * 60 * 60; // 1 시간
	private final PrincipalDetailsService principalDetailsService;
	
	@Value("${jwt.secret}")
	private String secretKey;
	
	private Key getSecretKey(String secretKey) {
		byte[] KeyBytes = secretKey.getBytes(StandardCharsets.UTF_8);
		return Keys.hmacShaKeyFor(KeyBytes);
	}

	public String getUsername(String jwtToken) {
		return Jwts.parserBuilder()
			.setSigningKey(getSecretKey(secretKey))
			.build()
			.parseClaimsJws(jwtToken)
			.getBody()
			.getSubject();
	}
	/*
	 * 유효기간을 확인한다.
	 */
	public boolean validateToken(String jwtToken) {
		try {
			log.info("validate..");
			Jws<Claims>  claims = Jwts.parserBuilder()
										.setSigningKey(getSecretKey(secretKey))
										.build()
										.parseClaimsJws(jwtToken);
			log.info("{}",claims.getBody().getExpiration());
			return !claims.getBody().getExpiration().before(new Date());
		}catch(Exception e) {
			return false;
		}
	}
	
	/*
	 * UserDetailsService.loadByUsername() 을 통해 JWT의 토큰에 있는
	 * sub claim(username)의 유저가 DB에 있는지 확인한다. 
	 */
	public Authentication getAuthentication(String jwtToken) {
		UserDetails userDetails = principalDetailsService.loadUserByUsername(getUsername(jwtToken));
		log.info("PASSWORD : {}",userDetails.getPassword());
		return new UsernamePasswordAuthenticationToken(userDetails, userDetails.getPassword(), userDetails.getAuthorities());
	}
	
	
	public String generateToken(String username) {
		return Jwts.builder()
					.setSubject(username)
					.setIssuedAt(new Date())
					.setExpiration(new Date(new Date().getTime() + VALID_MILISECOND))
					.signWith(getSecretKey(secretKey), SignatureAlgorithm.HS256)
					.compact();
	}
	
	public String parseJwt(HttpServletRequest request) {
		String headerAuth = request.getHeader("Authorization");
		if (StringUtils.hasText(headerAuth) && headerAuth.startsWith("Bearer ")) {
			return headerAuth.substring(7, headerAuth.length());
		}
		return null;
	}
	
}
