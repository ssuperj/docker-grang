package com.grang.repository;

import com.grang.model.likes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;

public interface LikesRepository extends JpaRepository<likes, Integer>{

	@Modifying
	@Query(value = "INSERT INTO likes(boardId, userId, createDate) VALUES(?1, ?2, ?3)", nativeQuery = true)
	void insertLikes(int boardId, int username, LocalDateTime now);
	
	void deleteByBoardIdAndUserId(int boardId, int userId);
}
