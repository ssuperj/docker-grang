package com.grang.service;

import com.grang.repository.LikesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;

@Service
public class LikesService {
	@Autowired
	LikesRepository likesRepository;

	@Transactional
	public void saveLikes(int boardId, int userId) {
		likesRepository.insertLikes(boardId, userId, LocalDateTime.now());
	}
	
	
	@Transactional
	public void deleteLikes(int boardId, int userId) {
		System.out.println("likeservice 삭제호출");
		likesRepository.deleteByBoardIdAndUserId(boardId, userId);
	}
	
	

}
