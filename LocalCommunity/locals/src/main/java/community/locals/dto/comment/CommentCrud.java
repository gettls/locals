package community.locals.dto.comment;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Length;

import community.locals.domain.Comment;
import community.locals.domain.Member;
import community.locals.domain.Post;
import lombok.Data;

@Data
public class CommentCrud {
	
	@NotNull(message =  "내용을 입력하세요.")
	@Length(max = 100, message = "100자를 넘기실 수 없습니다.")
	private String contents;
	
	private Member member;
	private Post post;
	
	public Comment toEntity() {
		return new Comment(contents, member, post);
	}
}
