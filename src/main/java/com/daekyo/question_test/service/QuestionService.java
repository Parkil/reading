package com.daekyo.question_test.service;

import com.daekyo.question_test.Constant;
import com.daekyo.question_test.component.QuestionCalcDifficulty;
import com.daekyo.question_test.component.QuestionParser;
import com.daekyo.question_test.util.CacheUtil;
import com.daekyo.question_test.util.HttpUtil;
import com.daekyo.question_test.vo.*;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.tuple.Pair;
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

    현재 진행하고 있는 mocking 에서 해설/동영상은 가정하지 않는다
    */

    private final QuestionParser questionParser;

    private final QuestionCalcDifficulty questionCalcDifficulty;

    private Text fetch() {
        String jsonStr = HttpUtil.
            httpGet("https://d-krt-api.daekyo.com/expr/krt/problem/beta?problemId=377034");
        return questionParser.parse(jsonStr);
    }

    public void saveCache() {
        CacheUtil.set(Constant.QUESTION_INFO_KEY, fetch());
    }

    public Text getBaseQuestionInfo() {
        Text currentText = (Text)CacheUtil.get(Constant.QUESTION_INFO_KEY);

        Pair<List<Question>, List<Question>> classifyQuestionPair =
            questionCalcDifficulty.classifyQuestion(currentText.getQuestionList());

        Text baseQuestionText = new Text(currentText.getTextStr(), classifyQuestionPair.getLeft());
        Text poolQuestionText = new Text(currentText.getTextStr(), classifyQuestionPair.getRight());

        CacheUtil.set(Constant.BASE_QUESTION_INFO_KEY, baseQuestionText);
        CacheUtil.set(Constant.POOL_QUESTION_INFO_KEY, poolQuestionText);

        return baseQuestionText;
    }

    public void saveQuestionTime(List<QuestionTime> timeList) {
        //todo 시간 저장
    }

    /*
        기준문제, 드릴문제를 분리하는건 간단
        어차피 전체 문제, 기준문제가 별도 cache에 저장되어 있기 때문에
     */
    public List<Score> scoring(List<UserReply> userReplyList) {
        // todo 사용자에게 출제 된 문제만 채점을 할수 있게 처리
        Text baseText = (Text)CacheUtil.get(Constant.BASE_QUESTION_INFO_KEY);

        List<QuestionReply> questionReplyList = baseText.getQuestionList().stream().map(question -> {
            // todo 출제된 문제와 다른 questionKey가 넘어올 경우 이를 처리해 주는 로직이 필요
            String userInputReply = userReplyList.stream()
                .filter(userReply -> userReply.getQuestionKey().equals(question.getQuestionKey()))
                .limit(1)
                .map(UserReply::getUserInputReply)
                .collect(Collectors.joining());

            return question.convertQuestionReply(userInputReply);
        }).collect(Collectors.toList());

        // todo QuestionReply의 정보 + 정/오답에 따른 점수를 같이 db에 저장

        CacheUtil.set(Constant.NEXT_DIFFICULTY_INFO_KEY,
            questionCalcDifficulty.getNextDifficulty(questionReplyList));

        return questionReplyList.stream()
            .map(QuestionReply::convertScore).collect(Collectors.toList());
    }
}