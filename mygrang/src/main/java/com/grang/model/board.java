package com.grang.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name="board")
@SequenceGenerator(name = "USER_SEQ_GENERATOR2", sequenceName = "USER_SEQ2", allocationSize = 1)
public class board {

	@Id
	@GeneratedValue(generator = "USER_SEQ_GENERATOR2", strategy = GenerationType.AUTO)
	private int id;
	
	@Column(nullable = false, length = 50)
	private String title;
	
	@Lob
	private String content;
	
	@ColumnDefault("0")
	private int count;

	private int likeCount;
	
	@Lob
	private String storyImages;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "userId")
	private User user;
	
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "board")
	private List<Reply> replies;
	
	@CreationTimestamp
	private Timestamp createTime;
}
