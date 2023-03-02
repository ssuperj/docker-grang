package com.grang.repository;

import com.grang.model.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface RoomRepository extends JpaRepository<Room, Integer> {

    @Query(value = "select * from Room where sendId = ?1 order by timestamp", nativeQuery = true)
    List<Room> findByRoom(int sendId);

    @Transactional
    @Modifying
    @Query(value = "delete FROM Room where sendId = ?1 and recvId = ?2", nativeQuery = true)
    int deleteRoom(int sendId, int recvId);
}
