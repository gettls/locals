package community.locals.exception;

public class PostNotExistException extends CustomException{
	
	public static final String POST_NOT_EXCEPTION = "����Ʈ�� �����ϴ�";
	
	public PostNotExistException(String message) {
		super(message);
	}
}
