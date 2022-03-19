package community.locals.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import community.locals.dto.member.MemberResponse;

public interface CustomMemberRepository {
	
	Page<MemberResponse> findAllSortedByCreateDate(Pageable pageable);
	
}
