package com.daekyo.service;

import com.daekyo.entity.Test;
import com.daekyo.entity.User;
import com.daekyo.repo.TestRepo;
import com.daekyo.repo.UserRepo;
import com.daekyo.spring_security.JwtTokenProvider;
import com.daekyo.spring_security.UserAuthentication;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TestService {
    private final Logger logger = LoggerFactory.getLogger(TestService.class);

    private final TestRepo testRepo;

    private final UserRepo userRepo;

    private final PasswordEncoder passwordEncoder;

    public void jpaTest() {
        List<Test> findList = testRepo.findAll();
        logger.info("findList : {}", findList);
    }

    public void jpaSaveTest() {
        Test test = new Test(0, "value9", Timestamp.valueOf(LocalDateTime.now()));
        testRepo.save(test);
    }

    public String login(String id, String pw) {
        User user = userRepo.findUserByIdAndPw(id, passwordEncoder.encode(pw))
                .orElseThrow(() -> new IllegalArgumentException("user not found"));

        Authentication authentication = new UserAuthentication(user.getId(), null, null);
        return JwtTokenProvider.generateToken(authentication);
    }
}