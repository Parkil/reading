package com.daekyo.question_test.component;

import com.daekyo.question_test.vo.Question;
import com.daekyo.question_test.vo.Score;
import com.daekyo.question_test.vo.enum_vo.CorrectStatus;
import com.daekyo.question_test.vo.enum_vo.QuestionDifficulty;
import com.daekyo.question_test.vo.enum_vo.QuestionDifficultyRelation;
import com.daekyo.question_test.vo.enum_vo.QuestionType;
import java.util.List;
import java.util.stream.Collectors;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.stereotype.Component;

@Component
public class QuestionCalcDifficulty {

  private boolean isBaseQuestion(Question question,
      List<Pair<QuestionType, QuestionDifficulty>> diffTargetList) {
    return diffTargetList.stream().anyMatch(diff->
         question.getQuestionType() == diff.getLeft()
      && question.getQuestionDifficulty() == diff.getRight());
  }

  private boolean isPoolQuestion(Question question,
      List<Question> baseQuestionList) {
    return baseQuestionList.stream().noneMatch(compare ->
        compare.getQuestionKey().equals(question.getQuestionKey()));
  }

  private Pair<List<Question>, List<Question>> classifyQuestionBasic(
      List<Question> allQuestionList,
      List<Pair<QuestionType, QuestionDifficulty>> targetList) {
    List<Question> baseQuestionList = allQuestionList.stream()
        .filter(question -> isBaseQuestion(question, targetList))
        .collect(Collectors.toList());

    List<Question> poolQuestionList = allQuestionList.stream()
        .filter(question -> isPoolQuestion(question, baseQuestionList))
        .collect(Collectors.toList());

    return Pair.of(baseQuestionList, poolQuestionList);
  }

  private List<Pair<QuestionType, QuestionDifficulty>> getDifficultyByPrevLessonResult() {
    // todo 이전 레슨의 데이터를 가져와서 유형별 문제 난이도를 계산 하는 기능 추가
    return List.of(Pair.of(QuestionType.FACT, QuestionDifficulty.TA),
        Pair.of(QuestionType.INFERENCE, QuestionDifficulty.TB),
        Pair.of(QuestionType.CRITICISM, QuestionDifficulty.TC),
        Pair.of(QuestionType.CONCEPT, QuestionDifficulty.TA));
  }

  private List<Pair<QuestionType, QuestionDifficulty>> getDifficultyByScore(
      List<Score> scoreList) {

    return scoreList.stream().map(this::getDifficulty).collect(Collectors.toList());
  }

  private Pair<QuestionType, QuestionDifficulty> getDifficulty(Score score) {
    QuestionDifficultyRelation questionDifficultyRelation =
        QuestionDifficultyRelation.valueOf(score.getQuestionDifficulty().name());

    QuestionDifficulty nextDifficulty = score.getCorrectStatus() == CorrectStatus.CORRECT ?
        questionDifficultyRelation.getUpDifficulty() :
        questionDifficultyRelation.getDownDifficulty();

    return Pair.of(score.getQuestionType(), nextDifficulty);
  }

  public Pair<List<Question>, List<Question>> classifyQuestion(List<Question> allQuestionList) {
    List<Pair<QuestionType, QuestionDifficulty>> targetList = getDifficultyByPrevLessonResult();
    return classifyQuestionBasic(allQuestionList, targetList);
  }

  public Pair<List<Question>, List<Question>> classifyQuestion(List<Question> allQuestionList,
      List<Score> scoreList) {
    List<Pair<QuestionType, QuestionDifficulty>> targetList = getDifficultyByScore(scoreList);
    return classifyQuestionBasic(allQuestionList, targetList);
  }
}