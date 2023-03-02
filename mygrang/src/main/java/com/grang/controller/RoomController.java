package com.grang.controller;

import com.grang.config.auth.PrincipalDetail;
import com.grang.model.Room;
import com.grang.service.RoomService;
import com.grang.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class RoomController {

    private final RoomService roomService;
    private final UserService userService;

    @GetMapping("/chat/{id}")
    public String chat(@PathVariable int id, Model model, @AuthenticationPrincipal PrincipalDetail principalDetail) {
        if(id != principalDetail.getUser().getId()) {
            return "redirect:/";
        }
        List<Room> rooms = roomService.방전체찾기(id);
        model.addAttribute("rooms", rooms);
        return "/chat";
    }

    @GetMapping("/detail/")
    public String detail() {
        return "/welcome";
    }

    @GetMapping("/detail/{id}")
    public String detail(@PathVariable int id, @AuthenticationPrincipal PrincipalDetail principalDetail, Model model) {
        model.addAttribute("sendUsers",principalDetail.getUser().getId());
        model.addAttribute("recvUsers", userService.회원찾기ById(id).getId());
        return "/detail";
    }
/*
    @ResponseBody
    @GetMapping("/room/{id}")
    public Room findRoom(@PathVariable int id) {
        return roomService.방찾기(id);
    }
*/

}
