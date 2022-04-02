package community.locals.exception;

public class PostMismatchUsernameException extends CustomException{

	public static final String POST_MISMATCH_USERNAME_EXCEPTION = "해당 포스트의 작성자가 아닙니다."; 
	
	public PostMismatchUsernameException(String message) {
		super(message);
	}
}
