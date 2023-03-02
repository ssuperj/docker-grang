package com.grang.dto;

import org.springframework.web.multipart.MultipartFile;

import com.grang.model.User;

import lombok.Data;

@Data
public class UpdateUserDto {

	private MultipartFile file;
	private String username;
	private String email;
	private String password;
	private String profileImage;
	
	public User toEntity(String username, String email, String password, String profileImage) {
		return User.builder()
				.username(username)
				.email(email)
				.password(password)
				.profileImage(profileImage)
				.build();
	}

}
