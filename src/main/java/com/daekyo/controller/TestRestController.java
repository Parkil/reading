package com.daekyo.controller;

import com.daekyo.service.TestService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class TestRestController {

    private final TestService testService;

    @PostMapping(value = "/test")
    public ResponseEntity<String> test() {
        testService.jpaTest();
        return new ResponseEntity<>("111222333", HttpStatus.OK);
    }

    @PostMapping(value = "/test_save")
    public ResponseEntity<String> saveTest() {
        testService.jpaSaveTest();
        return new ResponseEntity<>("111222333", HttpStatus.OK);
    }

    @PostMapping(value = "/login")
    public ResponseEntity<String> login(@RequestParam("id") String id, @RequestParam("pw") String pw) {
        return new ResponseEntity<>(testService.login(id, pw), HttpStatus.OK);
    }
}