package com.grang.controller.api;

import com.grang.config.auth.PrincipalDetail;
import com.grang.dto.uploadDto;
import com.grang.model.board;
import com.grang.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;


@RestController
public class BoardApiController {
	
	@Autowired
	private BoardService boardService;
	
	@PostMapping("/api/board")
	public ResponseEntity<String> Upload(uploadDto boardUploadDto, @AuthenticationPrincipal PrincipalDetail principalDetail){
		boardService.글쓰기(boardUploadDto, principalDetail.getUser());
		return ResponseEntity.ok().body("게시글 작성이 완료되었습니다.");
	}
	
	@PutMapping("/api/board/{id}")
	public ResponseEntity<String> Update(@PathVariable int id, @RequestBody board board){
		boardService.글수정하기(id,board);
		return ResponseEntity.ok().body("게시글 수정이 완료되었습니다.");
	}
	
	@DeleteMapping("/api/board/{id}")
	public ResponseEntity<String> deleteById(@PathVariable int id){
		boardService.글삭제하기(id);
		return ResponseEntity.ok().body("게시글 삭제가 완료되었습니다.");
	}


}
