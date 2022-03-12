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
import community.locals.dto.MemberDelete;
import community.locals.dto.MemberRegister;
import community.locals.dto.MemberResponse;
import community.locals.dto.MemberUpdate;
import community.locals.exception.MemberMismatchMatchException;
import community.locals.exception.MemberNotExistException;
import community.locals.repository.MemberRepository;
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
	public void register(MemberRegister memberRegister) {
		if(memberRepository.findByUsername(memberRegister.getUsername()).isPresent()) {
			throw new IllegalArgumentException("이미 존재하는 회원입니다.");
		}
		log.info("save...");
		memberRegister.setPassword(passwordEncoder.encode(memberRegister.getPassword()));
		memberRepository.save(new Member(memberRegister));
	}
	
	@Transactional
	public void update(MemberUpdate memberUpdate, HttpServletRequest request) {
		String username = jwtUtils.getUsername(jwtUtils.parseJwt(request));
		
		Member member = memberRepository.findByUsername(username).get();
		
		memberUpdate.setPassword(passwordEncoder.encode(memberUpdate.getPassword()));
		member.update(memberUpdate);
	}
	
	@Transactional
	public void delete(MemberDelete memberDelete) {
		Member member = memberRepository.findByUsername(memberDelete.getUsername())
				.orElseThrow(() -> new MemberNotExistException(NOT_EXIST_MESSAGE));
		
		if(!passwordEncoder.matches(memberDelete.getPassword(), member.getPassword())) {
			throw new MemberMismatchMatchException(MISMATCH_MESSAGE);
		}
		
		memberRepository.delete(member);
	}
	
	public Page<Member> findAll(Pageable pageable) {
		return memberRepository.findAll(pageable);
	}
}