package community.locals.exception;

public class MemberNotExistException extends CustomException{

	public static final String NOT_EXIST_MESSAGE = "존재하지 않는 회원입니다.";
	
	public MemberNotExistException(String message) {
		super(message);
	}
	
}
