package community.locals.exception;

public class MemberMismatchMatchException extends CustomException{

	public static final String MEMBER_MISMATCH = "���̵� Ȥ�� ��й�ȣ�� ��ġ���� �ʽ��ϴ�.";
	
	public MemberMismatchMatchException(String message) {
		super(message);
	}
}
