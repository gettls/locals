package community.locals.dto.comment;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import community.locals.domain.Comment;
import community.locals.domain.Member;
import community.locals.domain.Post;
import lombok.Data;

@Data
public class CommentCrud {
	
	@NotNull(message =  "������ �Է��ϼ���.")
	@Size(max = 100, message = "100�ڸ� �ѱ�� �� �����ϴ�.")
	private String contents;
	
	private Member member;
	private Post post;
	
	public Comment toEntity() {
		return new Comment(contents, member, post);
	}
}
