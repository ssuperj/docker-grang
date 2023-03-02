package com.grang.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table( name = "likes",
		uniqueConstraints = {
				@UniqueConstraint(
						name="likes_uk",
						columnNames = {"boardId", "userId"}
				)
		}
)
@SequenceGenerator(name = "USER_SEQ_GENERATOR6", sequenceName = "USER_SEQ6", allocationSize = 1)
public class likes {
	@Id
	@GeneratedValue(generator = "USER_SEQ_GENERATOR6", strategy = GenerationType.AUTO)
	private int id;
	
	//조인한 컬럼이 삭제되면 같이 삭제됨
	@OnDelete(action = OnDeleteAction.CASCADE)
	@JoinColumn(name="boardId")
	@ManyToOne
	private com.grang.model.board board;
	
	@JoinColumn(name="userId")
	@ManyToOne
	private User user;
	
	@CreationTimestamp
	private Timestamp createDate;

}
