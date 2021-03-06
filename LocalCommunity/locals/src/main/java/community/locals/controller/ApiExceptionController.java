package community.locals.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import community.locals.dto.exception.ErrorResult;
import community.locals.exception.InputNotFoundException;
import community.locals.exception.MemberMismatchMatchException;
import community.locals.exception.MemberNotExistException;
import community.locals.exception.PostMismatchUsernameException;
import community.locals.exception.PostNotExistException;
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
	public ResponseEntity<ErrorResult> memberMismatchExHandler(MemberMismatchMatchException e){
		ErrorResult errorResult = new ErrorResult("MEMBER-MISMATCH-EXCEPTION", e.getMessage());
		return new ResponseEntity<>(errorResult, HttpStatus.BAD_REQUEST);
	}

	
	@ExceptionHandler
	public ResponseEntity<ErrorResult> memberNotExistExHandler(MemberNotExistException e){
		ErrorResult errorResult = new ErrorResult("MEMBER-NOT-EXISTS-EXCEPTION", e.getMessage());
		return new ResponseEntity<>(errorResult, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler
	public ResponseEntity<ErrorResult> postNotExistException(PostNotExistException e){
		ErrorResult errorResult = new ErrorResult("POST-NOT-EXISTS-EXCEPTION", e.getMessage());
		return new ResponseEntity<>(errorResult, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler
	public ResponseEntity<ErrorResult> postMismatchUsernameException(PostMismatchUsernameException e){
		ErrorResult errorResult = new ErrorResult("POST-MISMATCH-USERNAME-EXCEPTION", e.getMessage());
		return new ResponseEntity<>(errorResult, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler
	public ResponseEntity<List<ErrorResult>> bindException(BindException e){
		
		List<ErrorResult> errors = new ArrayList<>();
		e.getBindingResult().getAllErrors()
				.forEach(ex -> errors.add(new ErrorResult("NOT-BIND_EXCEPTION",ex.getDefaultMessage())));
		
		return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
	}
	
}
