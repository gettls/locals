package community.locals.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import static community.locals.exception.MemberMismatchMatchException.*;
import static community.locals.exception.MemberNotExistException.*;

import javax.servlet.http.HttpServletRequest;

import community.locals.config.jwt.JwtUtils;
import community.locals.domain.Member;
import community.locals.dto.member.MemberCrud;
import community.locals.dto.member.MemberResponse;
import community.locals.exception.MemberMismatchMatchException;
import community.locals.exception.MemberNotExistException;
import community.locals.repository.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Slf4j
public class MemberService {
	
	private final MemberRepository memberRepository;
	private final BCryptPasswordEncoder passwordEncoder;
	private final JwtUtils jwtUtils;
	
	@Transactional
	public void register(MemberCrud memberCrud) {
		if(memberRepository.findByUsername(memberCrud.getUsername()).isPresent()) {
			throw new IllegalArgumentException("이미 존재하는 회원입니다.");
		}
		log.info("save...");
		memberCrud.setPassword(passwordEncoder.encode(memberCrud.getPassword()));
		memberRepository.save(memberCrud.toEntity());
	}
	
	@Transactional
	public void update(MemberCrud memberCrud, HttpServletRequest request) {
		String username = jwtUtils.getUsername(jwtUtils.parseJwt(request));
		
		Member member = memberRepository.findByUsername(username).get();
		
		memberCrud.setPassword(passwordEncoder.encode(memberCrud.getPassword()));
		member.update(memberCrud.toEntity());
	}
	
	@Transactional
	public void delete(MemberCrud memberCrud) {
		Member member = memberRepository.findByUsername(memberCrud.getUsername())
				.orElseThrow(() -> new MemberNotExistException(MEMBER_NOT_EXISTS));
		
		if(!passwordEncoder.matches(memberCrud.getPassword(), member.getPassword())) {
			throw new MemberMismatchMatchException(MEMBER_MISMATCH);
		}
		
		memberRepository.delete(member);
	}
	
	public Page<Member> findAll(Pageable pageable) {
		return memberRepository.findAll(pageable);
	}
	
	// Dynamic Query
	
	public Page<MemberResponse> findAllSortedByCreateDate(Pageable pageable){
		return memberRepository.findAllSortedByCreateDate(pageable);
	}
}