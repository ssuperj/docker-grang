package com.grang.controller.api;

import com.grang.config.auth.PrincipalDetail;
import com.grang.service.BoardService;
import com.grang.service.LikesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LikesApiController {
	
	@Autowired
	LikesService likesService;
	
	@Autowired
	BoardService boardService;
	
	@PostMapping("/api/board/likes/{boardId}")
	public ResponseEntity<String> likes(@PathVariable int boardId, @AuthenticationPrincipal PrincipalDetail principal) throws IllegalAccessException{
		likesService.saveLikes(boardId, principal.getUser().getId());
		return ResponseEntity.ok().body("좋아요성공");
	}
	
	@DeleteMapping("/api/board/likes/{boardId}")
	public ResponseEntity<String> unlikes(@PathVariable int boardId, @AuthenticationPrincipal PrincipalDetail principal){
		System.out.println("likeAPI 삭제호출");
		likesService.deleteLikes(boardId, principal.getUser().getId());
		return ResponseEntity.ok().body("좋아요해제성공");
	}

}
