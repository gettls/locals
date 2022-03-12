package community.locals.controller;

import java.util.Enumeration;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import community.locals.config.auth.PrincipalDetails;
import community.locals.config.jwt.JwtUtils;
import community.locals.domain.Member;
import community.locals.dto.MemberDelete;
import community.locals.dto.MemberLogin;
import community.locals.dto.MemberRegister;
import community.locals.dto.MemberResponse;
import community.locals.dto.MemberUpdate;
import community.locals.dto.jwt.JwtResponse;
import community.locals.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/member")
@RequiredArgsConstructor
@Slf4j
public class MemberApiController {

	private final MemberService memberService;
	private final JwtUtils jwtUtils;
	
	@GetMapping("/register")
	public ResponseEntity<?> register(@Validated @ModelAttribute MemberRegister memberRegister) {
		log.info("register...");
		memberService.register(memberRegister);
		return ResponseEntity.ok("register success");
	}
	
	@PostMapping("/login")
	public ResponseEntity<?> login(@Validated @ModelAttribute MemberLogin memberLogin){
		String jwtToken = jwtUtils.generateToken(memberLogin.getUsername());
		log.info("Token = {}", jwtToken);
		return ResponseEntity.ok(new JwtResponse(
												jwtToken,
												"Bearer",
												memberLogin.getUsername()
												));
	}
	
	@DeleteMapping("/delete")
	public ResponseEntity<?> delete(@Validated @ModelAttribute MemberDelete memberDelete){
		memberService.delete(memberDelete);
		return ResponseEntity.ok("delete");
	}
	
	@PutMapping("/update")
	public ResponseEntity<?> update(@Validated @ModelAttribute MemberUpdate memberUpdate, HttpServletRequest request){
		memberService.update(memberUpdate, request);
		return ResponseEntity.ok("update");
	}
	
	@GetMapping("/search")
	public Page<Member> search(Pageable pageable){
		log.info("searching...");
		PageRequest pageRequest = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize());
		return memberService.findAll(pageRequest);
	}
}
