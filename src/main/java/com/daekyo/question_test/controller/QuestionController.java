package com.daekyo.question_test.controller;

import com.daekyo.question_test.Constant;
import com.daekyo.question_test.service.QuestionService;
import com.daekyo.question_test.vo.Lesson;
import com.daekyo.question_test.vo.Question;
import com.daekyo.question_test.vo.Reply;
import com.daekyo.question_test.vo.ReplyResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class QuestionController {

    public final QuestionService questionService;



    @GetMapping(value = "/question/init_info")
    public ResponseEntity<Lesson> test(HttpServletRequest request) {
        request.getSession().setAttribute(Constant.HTTP_SESSION_QUESTION_INFO_KEY, questionService.genTestLesson());
        request.getSession().setAttribute(Constant.HTTP_SESSION_REPLY_INFO_KEY, new ArrayList<Reply>());
        return new ResponseEntity<>((Lesson)request.getSession().getAttribute(Constant.HTTP_SESSION_QUESTION_INFO_KEY), HttpStatus.OK);
    }

    @GetMapping(value = "/question/session_chk")
    public ResponseEntity<Lesson> sessionChk(HttpServletRequest request) {
        Lesson lesson = (Lesson)request.getSession().getAttribute(Constant.HTTP_SESSION_QUESTION_INFO_KEY);
        return new ResponseEntity<>(lesson, HttpStatus.OK);
    }

    @GetMapping(value = "/question/view_reply")
    public ResponseEntity<List<Reply>> findReplyList(HttpServletRequest request) {
        return new ResponseEntity<>(questionService.findReplyList(request.getSession()), HttpStatus.OK);
    }

    @GetMapping(value = "/question/lesson_start")
    public ResponseEntity<Question> lessonStart(HttpServletRequest request) {
        Lesson lesson = (Lesson)request.getSession().getAttribute(Constant.HTTP_SESSION_QUESTION_INFO_KEY);
        Question firstQuestion = lesson.getSubLessonList().get(0).getTextList().get(0).getQuestionListIterator().next();
        return new ResponseEntity<>(firstQuestion, HttpStatus.OK);
    }

    @GetMapping(value = "/question/reply_and_next")
    public ResponseEntity<ReplyResponse> replyAndNext(HttpServletRequest request,
                                                      @RequestParam("questionKey") String questionKey,
                                                      @RequestParam("reply") String reply) {

        return new ResponseEntity<>(questionService.replyAndNextQuestion(request.getSession(), questionKey, reply),
                HttpStatus.OK);
    }

    @GetMapping(value = "/question/routing_test_db1")
    public ResponseEntity<String> routingTEst(){
        questionService.test();
        return new ResponseEntity<>("111222333", HttpStatus.OK);
    }

    @GetMapping(value = "/question/routing_test_db2")
    public ResponseEntity<String> routingTEst2(){
        questionService.test2();
        return new ResponseEntity<>("111222333", HttpStatus.OK);
    }
}