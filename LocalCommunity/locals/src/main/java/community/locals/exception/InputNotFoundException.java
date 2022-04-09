package community.locals.exception;

public class InputNotFoundException extends RuntimeException{

	public static final String INPUT_NOT_FOUND = "입력값이 없습니다"; 
	
	public InputNotFoundException(String message) {
		super(message);
	}
	
}
