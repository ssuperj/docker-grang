package com.grang.controller.api;

import com.grang.dto.ResponseDto;
import com.grang.dto.RoomDto;
import com.grang.model.User;
import com.grang.service.RoomService;
import com.grang.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class RoomApiController {

    private final UserService userService;
    private final RoomService roomService;

    @PostMapping("/room/userSave/")
    public ResponseDto<Integer> userSave(@RequestBody RoomDto roomDto) {
        if(roomDto.getSendUserId() == roomDto.getRecvUserId()) {
            return new ResponseDto<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), 0);
        }
        roomService.방생성(roomDto);
        return new ResponseDto<>(HttpStatus.OK.value(), 1);
    }

    @DeleteMapping("/room")
    public List<User> deleteChatUser(@RequestBody RoomDto roomDto) {
        roomService.방삭제(roomDto);
        List<User> users = new ArrayList<>();
        users.add(userService.회원찾기ById(roomDto.getSendUserId()));
        users.add(userService.회원찾기ById(roomDto.getRecvUserId()));
        return users;
    }
}
