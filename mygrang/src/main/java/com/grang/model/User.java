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
@Table(name="User")
@SequenceGenerator(name = "USER_SEQ_GENERATOR", sequenceName = "USER_SEQ", allocationSize = 1)
public class User {

	@Id
	@GeneratedValue(generator = "USER_SEQ_GENERATOR", strategy = GenerationType.AUTO)
	private int id;
	
	@Column(nullable = false, length = 50, unique = true)
	private String username;
	
	@Column(nullable = false, length = 100)
	private String password;
	
	@Column(nullable = false, length = 50)
	private String email;
	
//	@ColumnDefault
	@Column(nullable = false, length = 100)
	private String profileImage;

	@Enumerated(EnumType.STRING)
	private RoleType roleType;
	
	@Enumerated(EnumType.STRING)
	private AuthType auth;

	@CreationTimestamp
	private Timestamp loginTime;

	@CreationTimestamp
	private Timestamp createTime;
}
