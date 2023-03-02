package com.grang.service;

import com.grang.dto.ReplyuploadDto;
import com.grang.model.board;
import com.grang.model.Reply;
import com.grang.model.User;
import com.grang.repository.BoardRepository;
import com.grang.repository.ReplyRepository;
import com.grang.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReplyService {

	private final ReplyRepository replyRepository;
	private final UserRepository userRepository;
	private final BoardRepository boardRepository;

	@Transactional
	public void 댓글작성(ReplyuploadDto replyuploadDto) throws IllegalAccessException {
		board board = boardRepository.findById(replyuploadDto.getBoardId())
			.orElseThrow(()->new IllegalAccessException("힘들다"));
		User user = userRepository.findById(replyuploadDto.getUserId())
				.orElseThrow(()->new IllegalAccessException("힘들다"));
		
		Reply reply = Reply.builder()
			.board(board)
			.user(user)
			.content(replyuploadDto.getContent())
			.build();
		
		replyRepository.save(reply);
	}
	
	@Transactional(readOnly=true)
	public List<Reply> 댓글목록() {
		return replyRepository.findAll();
	}
	
	@Transactional
	public void 댓글수정(int id, Reply requestReply) {
		Reply reply = replyRepository.findById(id).orElseThrow(() -> {
			return new IllegalArgumentException("아이디를 찾을 수 없습니다.");
		});
		reply.setContent(requestReply.getContent());
	}
//	
	@Transactional
	public void 댓글삭제(int id) {
		replyRepository.deleteById(id);
	};
}
