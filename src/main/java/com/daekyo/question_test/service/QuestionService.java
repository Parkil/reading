package com.daekyo.question_test.service;

import com.daekyo.question_test.component.Cache;
import com.daekyo.question_test.component.QuestionProvider;
import com.daekyo.question_test.component.QuestionParser;
import com.daekyo.question_test.component.Scoring;
import com.daekyo.question_test.util.HttpUtil;
import com.daekyo.question_test.vo.Question;
import com.daekyo.question_test.vo.QuestionTime;
import com.daekyo.question_test.vo.Score;
import com.daekyo.question_test.vo.Text;
import com.daekyo.question_test.vo.UserReply;
import java.util.ArrayList;
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

    private final QuestionProvider questionProvider;

    private final Scoring scoring;

    private final Cache cache;

    private Text fetch() {
        String jsonStr = HttpUtil.
            httpGet("https://d-krt-api.daekyo.com/expr/krt/problem/beta?problemId=377034");
        return questionParser.parse(jsonStr);
    }

    public void saveCache() {
        cache.initCache(fetch());
    }

    public Text getBaseQuestionInfo() {
        return new Text(cache.getText(), questionProvider.getBaseQuestionList());
    }

    public void saveQuestionTime(List<QuestionTime> timeList) {
        //todo 시간 저장
    }

    public List<Score> scoring(List<UserReply> userReplyList) {
        return scoring.scoring(userReplyList);
    }

    public Question getNextDrillQuestion() {
        /*

        [본 로직]
        PREV_SCORE_INFO_KEY 체크
            없으면 - DRILL_START_QUESTION_INFO_KEY 에서 한개 제거 한 문제 반환
            있으면 - PREV_SCORE_INFO_KEY를 기반으로 문제를 계산해서 1개 반환

        본 로직 종료 조건 : DRILL_START_QUESTION_INFO_KEY 가 빈 리스트가 될때까지 반복
         */

        // 전처리 - 최초 드릴문제 계산 + 계산이 끝나면 이전 채점 결과를 캐시에서 삭제
        if(scoring.isPrevScoreTypeBase()) {
            questionProvider.saveDrillStartQuestionList();
        }

        /*
        List<Question> targetQuestionList = new ArrayList<>();

        if(scoring.isPrevScoreEmpty()) {
            targetQuestionList.add(questionProvider.getDrillStartQuestion());
        }else {
            List<Score> prevScoreList = scoring.getPrevScore();
            targetQuestionList.add(questionProvider.getDrillNextQuestion(prevScoreList.get(0)));
        }

        cache.setCurrentQuestionList(targetQuestionList);
        return targetQuestionList.get(0);

         */
      return null;
    }
}