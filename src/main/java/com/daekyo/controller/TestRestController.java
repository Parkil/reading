package com.daekyo.controller;

import com.daekyo.async_component.AsyncCompo;
import com.daekyo.service.TestService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

@RestController
@RequiredArgsConstructor
public class TestRestController {

    private final TestService testService;

    private final AsyncCompo asyncCompo;

    private final Logger logger = LoggerFactory.getLogger(TestRestController.class);

    private static final ExecutorService executorService = Executors.newFixedThreadPool(1);

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

    @GetMapping(value = "/async_test")
    public ResponseEntity<String> asyncTest() throws IOException {
        Future<String> future = asyncCompo.callPython();

        executorService.execute(() -> {
            while(!future.isDone()) {
                logger.info("waiting....");
            }

            try {
                logger.info("future result : {}", future.get());
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                throw new RuntimeException(e);
            } catch (ExecutionException e) {
                throw new RuntimeException(e);
            }
        });

        /*
        while(!future.isDone()) {
            logger.info("waiting....");
        }

        String result = "";
        try {
            result = future.get();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }*/

        logger.info("11");

        return new ResponseEntity<>("가나다라223344", HttpStatus.OK);
    }
}