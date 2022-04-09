package community.locals.config.auth;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import community.locals.domain.Member;
import community.locals.repository.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Service
@Slf4j
public class PrincipalDetailsService implements UserDetailsService{

	private final MemberRepository memberRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Member member = memberRepository.findByUsername(username)
					.orElseThrow(() -> new UsernameNotFoundException("해당 사용자를 찾을 수 없습니다."));
		
		log.info("{}", member);
		
		return new PrincipalDetails(member);
	}
	
	
	
}
