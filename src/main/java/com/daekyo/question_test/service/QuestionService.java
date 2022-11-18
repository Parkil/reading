package com.daekyo.question_test.service;

import com.daekyo.question_test.component.QuestionCache;
import com.daekyo.question_test.component.QuestionParser;
import com.daekyo.question_test.component.Scoring;
import com.daekyo.question_test.util.HttpUtil;
import com.daekyo.question_test.vo.Question;
import com.daekyo.question_test.vo.QuestionTime;
import com.daekyo.question_test.vo.Score;
import com.daekyo.question_test.vo.Text;
import com.daekyo.question_test.vo.UserReply;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class QuestionService {

//    private final TestRepo testRepo;


//    private final UserRepo userRepo;

    /*
    @Transactional(readOnly = false) // readonly 여부에 따라 datasource 가 다르게 설정된다
    public void test() {
        testRepo.findAll().forEach(System.out::println);
    }

    public void test2() {
        userRepo.findAll().forEach(System.out::println);
    }

    front 에 필요한 backend api 목록
    본격독해 지문 + n개 문제 반환
    수능모의평가 지문 + n개 문제 반환
    진단평가 지문 + n개 문제 반환
    코어독해 문제 반환
    어휘학습 문제 반환
    답변 (문제 key + 답변을 리스트로 일괄 답변) -> 채점 결과 반환
    문제별 해설/동영상 정보 반환
    드릴 문제 반환 (1문제 씩)
    일별 결과보고서 데이터 반환
    월별 결과보고서 데이터 반환
    진단평가 결과보고서 데이터 반환
    수능모의평가 결과보고서 데이터 반환
    AI 평가 결과보고서 데이터 반환
    사용자별 오늘 레슨 상태 반환
    지식맵 데이터 반환
    문제별 시간 / 지문 읽은 시간 저장
    */

    private final QuestionParser questionParser;

    private final QuestionCache questionCache;

    private final Scoring scoring;

    private Text fetch() {
        String jsonStr = HttpUtil.
            httpGet("https://d-krt-api.daekyo.com/expr/krt/problem/beta?problemId=377034");
        return questionParser.parse(jsonStr);
    }

    public void saveCache() {
        questionCache.initCache(fetch());
    }

    public Text getBaseQuestionInfo() {
        return new Text(questionCache.getText(), questionCache.getBaseQuestionList());
    }

    public void saveQuestionTime(List<QuestionTime> timeList) {
        //todo 시간 저장
    }

    public List<Score> scoring(List<UserReply> userReplyList) {
        return scoring.scoring(userReplyList);
    }

    public Question getNextDrillQuestion() {
        //scoreCache.getScoreList() 의 questionGroup이 전부 BASE일 때만 questionCache.saveDrillStartQuestionList을 호출
        // questionCache.saveDrillStartQuestionList(resultList);
        //

        return null;
    }

    public List<Question> getCurrentQuestionList(){
        return questionCache.getCurrentQuestionList();
    }
}