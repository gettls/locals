package community.locals.dto.comment;

import java.time.LocalDateTime;

import com.querydsl.core.annotations.QueryProjection;

import lombok.Data;

@Data
public class CommentResponse {
	
	private String contents;
	private LocalDateTime createdDate;
	private String createdBy;
	
	@QueryProjection
	public CommentResponse(String contents, LocalDateTime createdDate, String createdBy) {
		this.contents = contents;
		this.createdDate = createdDate;
		this.createdBy = createdBy;
	}	
}
