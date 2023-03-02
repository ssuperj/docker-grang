package com.grang.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name="Reply")
@SequenceGenerator(name = "USER_SEQ_GENERATOR3", sequenceName = "USER_SEQ3", allocationSize = 1)
public class Reply {

	@Id
	@GeneratedValue(generator = "USER_SEQ_GENERATOR3", strategy = GenerationType.AUTO)
	private int id;

	@Column(nullable = false, length = 100)
	private String content;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "userId")
	private User user;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "boardId")
	private com.grang.model.board board;
	
	@CreationTimestamp
	private Timestamp createTime;
}
