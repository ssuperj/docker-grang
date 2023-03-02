package com.grang.dto;

import com.grang.model.Reply;
import lombok.Data;

import java.sql.Timestamp;

@Data
public class ReplyuploadDto {
	private String content;
	private Timestamp createTime;
	private int userId;
	private int boardId;
	
	public Reply toEntity(String content,Timestamp createTime) {
		return Reply.builder()
				.content(content)
				.createTime(createTime)
				.build();
	}

}
