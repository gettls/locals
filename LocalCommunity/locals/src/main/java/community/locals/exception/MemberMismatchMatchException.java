package community.locals.exception;

public class MemberMismatchMatchException extends CustomException{

	public static final String MEMBER_MISMATCH = "아이디 혹은 비밀번호가 일치하지 않습니다.";
	
	public MemberMismatchMatchException(String message) {
		super(message);
	}
}
