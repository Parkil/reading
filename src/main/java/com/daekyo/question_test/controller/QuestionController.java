package com.daekyo.question_test.controller;

import com.daekyo.question_test.service.QuestionService;
import com.daekyo.question_test.vo.QuestionTime;
import com.daekyo.question_test.vo.UserReply;
import com.daekyo.question_test.vo.Score;
import com.daekyo.question_test.vo.Text;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequiredArgsConstructor
public class QuestionController {

    public final QuestionService questionService;


    @GetMapping(value = "/question/save_cache")
    public ResponseEntity<String> saveCache() {
        questionService.saveCache();
        return new ResponseEntity<>("cache saved", HttpStatus.OK);
    }

    @GetMapping(value = "/question/get_base_text")
    public ResponseEntity<Text> getBaseText() {
        return new ResponseEntity<>(questionService.getBaseQuestionInfo(), HttpStatus.OK);
    }

    @PostMapping(value = "/question/save_question_time")
    public ResponseEntity<String> saveQuestionTime(@RequestBody List<QuestionTime> questionTimeList) {
        questionService.saveQuestionTime(questionTimeList);
        return new ResponseEntity<>("time saved",  HttpStatus.OK);
    }

    @PostMapping(value = "/question/scoring")
    public ResponseEntity<List<Score>> scoring(@RequestBody List<UserReply> userReplyList) {
        return new ResponseEntity<>(questionService.scoring(userReplyList),  HttpStatus.OK);
    }

    /*
    @GetMapping(value = "/question/routing_test_db1")
    public ResponseEntity<String> routingTEst(){
        questionService.test();
        return new ResponseEntity<>("111222333", HttpStatus.OK);
    }

    @GetMapping(value = "/question/routing_test_db2")
    public ResponseEntity<String> routingTEst2(){
        questionService.test2();
        return new ResponseEntity<>("111222333", HttpStatus.OK);
    }*/
}