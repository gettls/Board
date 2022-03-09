package lab.board.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.Getter;

@Entity
@Getter
public class Comment extends BaseEntity{

	@Id @GeneratedValue
	private Long id;
	private String contents;
	
}
