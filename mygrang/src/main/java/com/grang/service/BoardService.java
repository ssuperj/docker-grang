package com.grang.service;

import com.grang.dto.uploadDto;
import com.grang.model.board;
import com.grang.model.User;
import com.grang.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.nio.file.Paths;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class BoardService {

	private final BoardRepository boardRepository;

	@Transactional
	public boolean 글쓰기(uploadDto boarduploadDto,User user) {
		// 유저정보 가져오기
		String osName = System.getProperty("os.name");
		String userName = System.getProperty("user.name");
		String uploadPath = "";

		// 파일경로 생성
		if(osName.contains("Mac")) {
			 uploadPath = Paths.get("/Users",userName, "Pictures", "grangImg").toString();
		} else if (osName.contains("Windows")) {
			 uploadPath = Paths.get("C:/Users",userName, "Pictures", "grangImg").toString();
		} else if (osName.contains("Linux"))
			uploadPath = Paths.get("/",userName, "Pictures", "grangImg").toString();
		else {
			return false;
		}
		File dir = new File(uploadPath);
		dir.mkdirs();

		MultipartFile[] file = boarduploadDto.getFile();
		
		UUID uuid = UUID.randomUUID();
		
		String[] ImageName = new String[file.length];
		for(int i=0; i<file.length; i++) {
			ImageName[i] = uuid + "_" + file[i].getOriginalFilename();
			System.out.println(ImageName[i]);
		}
		
		for(int i=0; i<file.length; i++) {
			 try { File target = new File(uploadPath, ImageName[i]);
			 file[i].transferTo(target); 
			 } catch(Exception e) { 
				 return false; }
		}
		
		StringBuilder builder = new StringBuilder();
		for(String str : ImageName) {
			builder.append(str + "/");
		}
		builder.delete(builder.length()-1, builder.length());
		
		 board board = boarduploadDto.toEntity(boarduploadDto.getTitle(),
		 boarduploadDto.getContent(), builder.toString(), boarduploadDto.getCreateTime());
		 board.setUser(user);
		 boardRepository.save(board);

		return true;
	}
	
	
	public Page<board> 글전체보기(Pageable pageable) {
		return boardRepository.findAll(pageable);
	}
	
	@Transactional(readOnly=true)
	public board 글상세보기(int id) {
		return boardRepository.findById(id).orElseThrow(()->{
			throw new IllegalArgumentException("글 상세보기 실패: 아이디를 찾을 수 없습니다.");
		});
	}
	
	
	@Transactional
	public void 글수정하기(int id, board boards) {
		board board = boardRepository.findById(id).orElseThrow(()->{
			return new IllegalArgumentException("글 찾기 실패: 아이디를 찾을 수 없습니다.");
		});
		board.setTitle(boards.getTitle());
		board.setContent(boards.getContent());
		
	}
	
	@Transactional
	public void 글삭제하기(int id) {
		boardRepository.deleteById(id);
	}

	@Transactional public int likecnt(int boardId) {
		System.out.println(boardId); return boardRepository.Likes(boardId); }

	@Transactional(readOnly = true)
	public int 좋아요조회(int userId, int boardId) {
		System.out.println("userId = " + userId);
		System.out.println("boardId = " + boardId);
		return boardRepository.state(userId, boardId);
	}

}
