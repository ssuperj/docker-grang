package com.grang.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.grang.model.KakaoProfile;
import com.grang.model.KakaoToken;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.client.RestTemplate;

@Controller
public class KakaoController {

	@GetMapping("/auth/kakao/login")
	public String kakaoToken(String code, Model model) {
		RestTemplate rt = new RestTemplate();
		
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Type", "application/x-www-form-urlencoded");
		
		MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
		params.add("grant_type", "authorization_code");
		params.add("client_id", "3c4f59e8f00dd3b8e07672a870968fbd");
		params.add("redirect_uri", "http://localhost:8090/auth/kakao/login");
		params.add("code", code);

		HttpEntity<MultiValueMap<String, String>> kakaoTokenRequest = 
				new HttpEntity<>(params, headers);
		
		ResponseEntity<String> response = rt.exchange(
				"https://kauth.kakao.com/oauth/token",
				HttpMethod.POST,
				kakaoTokenRequest,
				String.class);
		
		ObjectMapper objectMapper = new ObjectMapper();
		KakaoToken kakaoToken = null;
		try {
			kakaoToken = objectMapper.readValue(response.getBody(), KakaoToken.class);
		} catch (Exception e) {
			e.printStackTrace();
		} 
		
		model.addAttribute("kakaoToken" ,kakaoToken);
		return "forward:/auth/kakao/profile";
	}
	
	@GetMapping("/auth/kakao/profile")
	public String kakaoProfile(@RequestAttribute KakaoToken kakaoToken, Model model) {
		RestTemplate rt = new RestTemplate();
		
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Type", "application/x-www-form-urlencoded");
		headers.add("Authorization", "Bearer " + kakaoToken.access_token);

		HttpEntity<MultiValueMap<String, String>> kakaoTokenRequest = 
				new HttpEntity<>(headers);
		
		ResponseEntity<String> response = rt.exchange(
				"https://kapi.kakao.com/v2/user/me",
				HttpMethod.POST,
				kakaoTokenRequest,
				String.class);
		
		ObjectMapper objectMapper = new ObjectMapper();
		KakaoProfile kakaoProfile = null;
		try {
			kakaoProfile = objectMapper.readValue(response.getBody(), KakaoProfile.class);
		} catch (Exception e) {
			e.printStackTrace();
		} 
		
		model.addAttribute("kakaoProfile" ,kakaoProfile);
		return "forward:/auth/api/kakao";
	}
}
