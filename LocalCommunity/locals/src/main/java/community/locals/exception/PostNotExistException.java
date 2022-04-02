package community.locals.exception;

public class PostNotExistException extends CustomException{
	
	public static final String POST_NOT_EXCEPTION = "포스트가 없습니다";
	
	public PostNotExistException(String message) {
		super(message);
	}
}
