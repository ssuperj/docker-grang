package com.grang.controller;

import com.grang.config.auth.PrincipalDetail;
import com.grang.dto.ResponseDto;
import com.grang.model.Reply;
import com.grang.model.User;
import com.grang.service.BoardService;
import com.grang.service.ReplyService;
import com.grang.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class BoardController {

	private final UserService userService;
	private final BoardService boardService;
	private final ReplyService replyService;

	@GetMapping(value = {"", "/"})
	public String index(Model model, @PageableDefault Pageable pageable) {
		model.addAttribute("board", boardService.글전체보기(pageable));
		model.addAttribute("replys", replyService.댓글목록());
		return "/index";
	}

	@GetMapping("/userPage/{id}")
	public String detail(Model model, @PathVariable int id, @PageableDefault Pageable pageable, @AuthenticationPrincipal PrincipalDetail principalDetail) {
		User user = userService.회원찾기ById(id);
		model.addAttribute("user", user);
		model.addAttribute("board", boardService.글전체보기(pageable));
		model.addAttribute("userId", id);
		return "/userPage";
	}

	@GetMapping("/boardForm")
	public String upload() {
		return "/boardForm";
	}

	@GetMapping("/updateBoardForm/{id}")
	public String findById(@PathVariable int id, Model model) {
		model.addAttribute("board", boardService.글상세보기(id));
		return "/updateBoardForm";
	}


	@GetMapping({"/index"})
	public String story(Model model) {
		model.addAttribute("replys", replyService.댓글목록());
		return "/index";
	}

	@PutMapping("/index/{id}")
	public ResponseDto<Integer>
	editreply(@PathVariable int id, @RequestBody Reply reply) {
		replyService.댓글수정(id, reply);
		return new
				ResponseDto<Integer>(HttpStatus.OK.value(), 1);
	}

	@ResponseBody
	@GetMapping("/auth/index/likes/{id}")
	public List<Integer> getLikes(@PathVariable int id, @AuthenticationPrincipal PrincipalDetail principalDetail) {
		int count = boardService.likecnt(id);
		int state = boardService.좋아요조회(principalDetail.getUser().getId(), id);
		List<Integer> list = new ArrayList<>();
		list.add(count);
		list.add(state);
		return list;
	}
}
