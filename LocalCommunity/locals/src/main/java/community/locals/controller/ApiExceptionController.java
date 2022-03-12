package community.locals.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import community.locals.dto.exception.ErrorResult;
import community.locals.exception.MemberMismatchMatchException;
import community.locals.exception.MemberNotExistException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@ControllerAdvice
public class ApiExceptionController {
	
	@ExceptionHandler
	public ResponseEntity<ErrorResult> notFoundExHandler(BadCredentialsException e){
		ErrorResult errorResult = new ErrorResult("MISAMTCH", e.getMessage());
		return new ResponseEntity<>(errorResult, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler
	public ResponseEntity<ErrorResult> mismatchExHandler(MemberMismatchMatchException e){
		ErrorResult errorResult = new ErrorResult("MEMBER-MISMATCH-EXCEPTION", e.getMessage());
		return new ResponseEntity<>(errorResult, HttpStatus.BAD_REQUEST);
	}

	
	@ExceptionHandler
	public ResponseEntity<ErrorResult> notExistExHandler(MemberNotExistException e){
		ErrorResult errorResult = new ErrorResult("NOT-EXISTS-EXCEPTION", e.getMessage());
		return new ResponseEntity<>(errorResult, HttpStatus.BAD_REQUEST);
	}
}
