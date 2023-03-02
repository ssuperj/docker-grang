package com.grang.config;

import com.grang.model.AuthType;
import com.grang.model.RoleType;
import com.grang.model.User;
import com.grang.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Slf4j
@RequiredArgsConstructor
@Component
public class SampleApplicationRunner implements ApplicationRunner {

        private final UserService userService;

        @Override
        public void run(ApplicationArguments args) throws Exception {
                // 더미 데이터 생성

                User user = User.builder()
                        .id(1)
                        .username("poqwer95")
                        .password("1234")
                        .email("poqwer95@gmail.com")
                        .roleType(RoleType.USER)
                        .auth(AuthType.GENERAL)
                        .build();

                User user2 = User.builder()
                        .id(2)
                        .username("poqwer96")
                        .password("1234")
                        .email("poqwer96@gmail.com")
                        .roleType(RoleType.USER)
                        .auth(AuthType.GENERAL)
                        .build();

                userService.회원가입(user);
                userService.회원가입(user2);
        }

}
