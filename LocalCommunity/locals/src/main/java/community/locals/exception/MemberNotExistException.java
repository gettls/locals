package community.locals.exception;

public class MemberNotExistException extends CustomException{

	public static final String NOT_EXIST_MESSAGE = "�������� �ʴ� ȸ���Դϴ�.";
	
	public MemberNotExistException(String message) {
		super(message);
	}
	
}
