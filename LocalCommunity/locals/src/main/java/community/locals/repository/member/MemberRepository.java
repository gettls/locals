package community.locals.repository.member;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import community.locals.domain.Member;

public interface MemberRepository extends JpaRepository<Member, Long>, CustomMemberRepository{

	Optional<Member> findByUsername(String username);
	
//	Page
}
