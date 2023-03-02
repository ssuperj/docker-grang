package com.grang.controller.api;

import com.grang.dto.ReplyuploadDto;
import com.grang.dto.ResponseDto;
import com.grang.model.Reply;
import com.grang.service.ReplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
public class ReplyApiController {
	
	@Autowired
	private ReplyService replyService;
	
	@Autowired
	public ReplyApiController(ReplyService replyService) {
		this.replyService = replyService;
	}

	@PostMapping("/api/reply")
	public ResponseDto<Integer> save(@RequestBody ReplyuploadDto replyuploadDto) {
		try {
			replyService.댓글작성(replyuploadDto);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		return new ResponseDto<Integer>(HttpStatus.OK.value(),1);
	}
	
	@DeleteMapping("/api/reply/{id}")
	public ResponseDto<Integer> deleteById(@PathVariable int id){
		replyService.댓글삭제(id);
		return new ResponseDto<Integer>(HttpStatus.OK.value(),1);
	}
	
	@PutMapping("/api/reply/{id}")
	public ResponseDto<Integer> update(@PathVariable int id, @RequestBody Reply reply) {
		replyService.댓글수정(id, reply);
		return new ResponseDto<Integer>(HttpStatus.OK.value(),1);
	}
}