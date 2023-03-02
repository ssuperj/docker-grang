package com.grang.controller.api;

import com.grang.config.auth.PrincipalDetail;
import com.grang.dto.ResponseDto;
import com.grang.dto.RoomDto;
import com.grang.dto.UpdateUserDto;
import com.grang.model.AuthType;
import com.grang.model.KakaoProfile;
import com.grang.model.User;
import com.grang.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@RequiredArgsConstructor
@RestController
public class UserApiController {

	private final UserService userService;
	
	private final AuthenticationManager authenticationManager;

	@PostMapping("/auth/api/user")
	public ResponseDto<Integer> join(@RequestBody User user) {
		userService.회원가입(user);
		return new ResponseDto<>(HttpStatus.OK.value(), 1);
	}
	
	@GetMapping("/auth/api/kakao")
	public ModelAndView join(@RequestAttribute KakaoProfile kakaoProfile) {
		User kakaoUser = User.builder()
			.username(kakaoProfile.getProperties().nickname)
			.password(String.valueOf(Objects.hash(kakaoProfile.id)))
			.email(kakaoProfile.kakao_account.email)
			.auth(AuthType.KAKAO)
			.build();
		System.out.println("kakaoProfile = " + kakaoProfile);


		User orginUser = userService.회원찾기(kakaoUser.getUsername());
		
		if(orginUser.getUsername() == null) {
			userService.회원가입(kakaoUser);
		}

		Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(kakaoProfile.getProperties().nickname, String.valueOf(Objects.hash(kakaoProfile.id))));
		SecurityContextHolder.getContext().setAuthentication(authentication);

		return new ModelAndView("redirect:/");
	}

	@GetMapping("/auth/api/findUser")
	public List<User> findUser(String username) {
		List<User> user = userService.회원검색(username);
		return user;
	}

	@PostMapping("/chat/findUser")
	public List<User> findUser(@RequestBody RoomDto roomDto) {
		User sendUser = userService.회원찾기ById(roomDto.getSendUserId());
		User recvUser = userService.회원찾기ById(roomDto.getRecvUserId());
		List<User> users = new ArrayList<>();
		users.add(sendUser);
		users.add(recvUser);
		return users;
	}

	@PostMapping("/auth/api/userUpdate")
	public ResponseEntity<String> updateUser(UpdateUserDto updateUserDto, @AuthenticationPrincipal PrincipalDetail principalDetail){
		System.out.println(updateUserDto);
		userService.회원수정(updateUserDto, principalDetail.getUser());
		return ResponseEntity.ok().body("회원수정이 완료되었습니다.");
	}

}
