package com.grang.service;

import com.grang.dto.RoomDto;
import com.grang.model.Room;
import com.grang.model.User;
import com.grang.repository.RoomRepository;
import com.grang.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RoomService {

    private final RoomRepository roomRepository;

    private final UserRepository userRepository;

    @Transactional
    public void 방생성(RoomDto roomDto) {
        User sendUser = userRepository.findById(roomDto.getSendUserId())
                .orElseThrow(() -> new IllegalArgumentException("없는 아이디"));
        User recvUser = userRepository.findById(roomDto.getRecvUserId())
                .orElseThrow(() -> new IllegalArgumentException("없는 아이디"));
        Room room = Room.builder()
                .sendUser(sendUser)
                .recvUser(recvUser)
                .build();
        System.out.println("room = " + room);
        roomRepository.save(room);
    }

    @Transactional(readOnly = true)
    public List<Room> 방전체찾기(int id) {
        return roomRepository.findByRoom(id);
    }

    public void 방삭제(RoomDto roomDto) {
        roomRepository.deleteRoom(roomDto.getSendUserId(), roomDto.getRecvUserId());
    }
}
