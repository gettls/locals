package community.locals.config.auth;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import community.locals.domain.Member;

public class PrincipalDetails implements UserDetails{

	private Member member;
	
	public PrincipalDetails(Member member) {
		this.member = member;
	}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		Collection<GrantedAuthority> authorities = new ArrayList<>();
		return authorities;
	}

	@Override
	public String getPassword() {
		return member.getPassword();
	}

	@Override
	public String getUsername() {
		return member.getUsername();
	}

	// 계정 만료 여부 (true: not expired)
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	
	// 계정이 잠겼는지 여부 (true: not locked)
	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	// 비밀번호가 만료되지 않았는지 리턴 (true: no expired)
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	// 계정 활성화(사용가능)인지 리턴
	@Override
	public boolean isEnabled() {
		return true;
	}

}
