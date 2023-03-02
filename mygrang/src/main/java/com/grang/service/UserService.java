package com.grang.service;

import com.grang.dto.UpdateUserDto;
import com.grang.model.AuthType;
import com.grang.model.RoleType;
import com.grang.model.User;
import com.grang.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class UserService {

	private final UserRepository userRepository;

	private final BCryptPasswordEncoder encoder;

	private final AuthenticationManager authenticationManager;

	@Transactional
	public void 회원가입(User user) {
		String rawPassword = user.getPassword();
		String encPassword = encoder.encode(rawPassword);
		user.setPassword(encPassword);
		user.setProfileImage("/image/normal.jpg");
		user.setAuth(AuthType.GENERAL);
		user.setRoleType(RoleType.USER);
		userRepository.save(user);
	}

	@Transactional(readOnly = true)
	public User 회원찾기(String username) {
		return userRepository.findByUsername(username)
				.orElse(new User());
	}

	@Transactional(readOnly = true)
	public User 회원찾기ById(int id) {
		return userRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("아이디 존재 x"));
	}

	@Transactional(readOnly = true)
	public List<User> 회원검색(String username) {
		return userRepository.findByUsername2(username);
	}

	@Transactional
	public boolean 회원수정(UpdateUserDto updateUserDto, User user) {
		User percitence = userRepository.findById(user.getId()).orElseThrow(()->{
			return new IllegalArgumentException("회원 찾기 실패");
		});
		if(updateUserDto.getFile() != null) {
			String osName = System.getProperty("os.name");
			String userName = System.getProperty("user.name");
			String uploadPath = "";

			// 파일경로 생성
			if(osName.contains("Mac")) {
				uploadPath = Paths.get("/Users",userName, "Pictures", "profile").toString();
			} else if (osName.contains("Windows")) {
				uploadPath = Paths.get("C:/Users",userName, "Pictures", "profile").toString();
			}else if (osName.contains("Linux"))
				uploadPath = Paths.get("/",userName, "Pictures", "profile").toString();
			else {
				return false;
			}
			File dir = new File(uploadPath);
			dir.mkdirs();

			MultipartFile file = updateUserDto.getFile();

			UUID uuid = UUID.randomUUID();

			String ImageName = uuid + "_" + file.getOriginalFilename();

			try {
				File target = new File(uploadPath, ImageName);
				file.transferTo(target);

			} catch(Exception e) {
				return false;
			} finally {
				percitence.setProfileImage(ImageName);
			}
		}

		percitence.setEmail(updateUserDto.getEmail());
		percitence.setPassword(encoder.encode(updateUserDto.getPassword()));

		Authentication authenticate = authenticationManager.authenticate(new
				UsernamePasswordAuthenticationToken(updateUserDto.getUsername(), updateUserDto.getPassword()));
		SecurityContextHolder.getContext().setAuthentication(authenticate);

		return true;
	}

}
