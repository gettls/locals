package community.locals.exception;

public class MemberNotExistException extends CustomException{

	public static final String MEMBER_NOT_EXISTS = "�������� �ʴ� ȸ���Դϴ�.";
	
	public MemberNotExistException(String message) {
		super(message);
	}
	
}
