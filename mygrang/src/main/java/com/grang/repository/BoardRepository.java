package com.grang.repository;

import com.grang.model.board;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface BoardRepository  extends JpaRepository<board, Integer>{

    @Query(value="select (Select COUNT(*) from likes where boardId = ?1) likeCount FROM board where id = ?1", nativeQuery = true)
    int Likes(int boardId);

    @Query(value="select count(*) from likes where userId = ?1 and boardId = ?2", nativeQuery = true)
    int state(int userId, int boardId);
}
