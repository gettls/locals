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

	// ���� ���� ���� (true: not expired)
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	
	// ������ ������ ���� (true: not locked)
	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	// ��й�ȣ�� ������� �ʾҴ��� ���� (true: no expired)
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	// ���� Ȱ��ȭ(��밡��)���� ����
	@Override
	public boolean isEnabled() {
		return true;
	}

}
