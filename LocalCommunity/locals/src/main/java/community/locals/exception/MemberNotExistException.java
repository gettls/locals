package community.locals.exception;

public class MemberNotExistException extends CustomException{

	public static final String MEMBER_NOT_EXISTS = "존재하지 않는 회원입니다.";
	
	public MemberNotExistException(String message) {
		super(message);
	}
	
}
