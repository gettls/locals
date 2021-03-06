package community.locals.domain;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EntityListeners(AuditingEntityListener.class)
public class Comment {
	
	@Id @GeneratedValue
	@Column(name = "COMMENT_ID")
	private Long id;
	
	@Column(length = 100)
	private String contents;
	
	private String createdBy;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "MEMBER_ID")
	@JsonIgnore
	private Member member;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "POST_ID")
	@JsonIgnore
	private Post post;

	@CreatedDate
	private LocalDateTime createdDate;
	
	@LastModifiedDate
	private LocalDateTime modifiedDate;
	
	public Comment(String contents, Member member, Post post) {
		this.contents = contents;
		this.member = member;
		this.post = post;
		
		createdBy = member.getUsername();
	}
	
	
	
}
