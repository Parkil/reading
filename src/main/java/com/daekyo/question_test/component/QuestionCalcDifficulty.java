package com.daekyo.question_test.component;

import com.daekyo.question_test.vo.Question;
import com.daekyo.question_test.vo.enum_vo.QuestionDifficulty;
import com.daekyo.question_test.vo.QuestionReply;
import com.daekyo.question_test.vo.enum_vo.QuestionType;
import java.util.List;
import java.util.stream.Collectors;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.stereotype.Component;

@Component
public class QuestionCalcDifficulty {

  // 기준문제 난이도 계산
  public List<Pair<QuestionType, QuestionDifficulty>> getBaseDifficulty() {
    // todo 이전 레슨의 데이터를 가져와서 유형별 문제 난이도를 계산 하는 기능 추가
    return List.of(Pair.of(QuestionType.FACT, QuestionDifficulty.TA),
        Pair.of(QuestionType.INFERENCE, QuestionDifficulty.TB),
        Pair.of(QuestionType.CRITICISM, QuestionDifficulty.TC),
        Pair.of(QuestionType.CONCEPT, QuestionDifficulty.TA));
  }

  // 드릴문제 시작 난이도 계산 ( 기준문제 풀이 결과에 따른 난이도 계산 )
  public List<Pair<QuestionType, QuestionDifficulty>> getNextDifficulty(
      List<QuestionReply> questionReplyList) {
    return questionReplyList.stream()
        .map(QuestionReply::getNextDifficulty).collect(Collectors.toList());
  }

  private boolean isBaseQuestion(Question question,
      List<Pair<QuestionType, QuestionDifficulty>> standardDifficultyList) {
    return standardDifficultyList.stream().anyMatch(diff->
         question.getQuestionType() == diff.getLeft()
      && question.getQuestionDifficulty() == diff.getRight());
  }

  private boolean isPoolQuestion(Question question,
      List<Pair<QuestionType, QuestionDifficulty>> standardDifficultyList) {
    return standardDifficultyList.stream().anyMatch(diff->
         question.getQuestionType() != diff.getLeft()
      && question.getQuestionDifficulty() != diff.getRight());
  }

  // 전체 문제를 기준문제 + 나머지 문제로 분류
  public Pair<List<Question>, List<Question>> classifyQuestion(List<Question> allQuestionList) {
    List<Pair<QuestionType, QuestionDifficulty>> standardDifficultyList = getBaseDifficulty();
    List<Question> baseQuestionList = allQuestionList.stream()
        .filter(question -> isBaseQuestion(question, standardDifficultyList))
        .collect(Collectors.toList());

    List<Question> poolQuestionList = allQuestionList.stream()
        .filter(question -> isPoolQuestion(question, standardDifficultyList))
        .collect(Collectors.toList());

    return Pair.of(baseQuestionList, poolQuestionList);
  }
}