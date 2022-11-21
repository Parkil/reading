package com.daekyo.question_test.component;

import com.daekyo.question_test.vo.CalcQuestionDifficulty;
import com.daekyo.question_test.vo.Question;
import com.daekyo.question_test.vo.Score;
import com.daekyo.question_test.vo.enum_vo.DrillStatus;
import com.daekyo.question_test.vo.enum_vo.QuestionGroup;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ClassifyQuestion {

  private final QuestionCalcDifficulty questionCalcDifficulty;

  private Pair<List<Question>, List<Question>> classifyQuestionBasic(
      List<Question> allQuestionList, List<CalcQuestionDifficulty> targetList) {

    List<Question> targetQuestionList = new ArrayList<>();
    List<Question> otherQuestionList = new ArrayList<>();
    for(Question question : allQuestionList) {
      CalcQuestionDifficulty target = getTarget(question, targetList);

      if(target != null) {
        question.setDrillStatus(target.getDrillStatus());
        targetQuestionList.add(question);
      }else {
        question.setDrillStatus(DrillStatus.NONE);
        otherQuestionList.add(question);
      }
    }
    return Pair.of(targetQuestionList, otherQuestionList);
  }

  private CalcQuestionDifficulty getTarget(Question question,
      List<CalcQuestionDifficulty> targetList) {
    List<CalcQuestionDifficulty> filterList =
        targetList.stream().filter(target ->
            target.getQuestionType() == question.getQuestionType() &&
            target.getQuestionDifficulty() == question.getQuestionDifficulty())
            .collect(Collectors.toList());

    return (filterList.isEmpty()) ? null : filterList.get(0);
  }

  public Pair<List<Question>, List<Question>> classify(List<Question> allQuestionList) {
    List<CalcQuestionDifficulty> targetList =
        questionCalcDifficulty.getDifficultyByPrevLessonResult();

    Pair<List<Question>, List<Question>> classifyQuestionPair
        = classifyQuestionBasic(allQuestionList, targetList);

    List<Question> baseQuestionList = classifyQuestionPair.getLeft();
    List<Question> drillQuestionList = classifyQuestionPair.getRight();

    baseQuestionList.forEach(row -> row.setQuestionGroup(QuestionGroup.BASE));
    drillQuestionList.forEach(row -> row.setQuestionGroup(QuestionGroup.DRILL));

    return classifyQuestionPair;
  }
  public Pair<List<Question>, List<Question>> classify(List<Question> allQuestionList,
      List<Score> scoreList) {
    List<CalcQuestionDifficulty> targetList =
        questionCalcDifficulty.getDifficultyByScore(scoreList);

    return classifyQuestionBasic(allQuestionList, targetList);
  }
}