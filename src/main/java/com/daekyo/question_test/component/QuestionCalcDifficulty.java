package com.daekyo.question_test.component;

import com.daekyo.question_test.vo.CalcQuestionDifficulty;
import com.daekyo.question_test.vo.Score;
import com.daekyo.question_test.vo.enum_vo.CorrectStatus;
import com.daekyo.question_test.vo.enum_vo.DrillStatus;
import com.daekyo.question_test.vo.enum_vo.QuestionDifficulty;
import com.daekyo.question_test.vo.enum_vo.QuestionDifficultyRelation;
import com.daekyo.question_test.vo.enum_vo.QuestionType;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;

@Component
public class QuestionCalcDifficulty {
  private CalcQuestionDifficulty getDifficulty(Score score) {
    QuestionDifficultyRelation questionDifficultyRelation =
        QuestionDifficultyRelation.valueOf(score.getQuestionDifficulty().name());

    DrillStatus drillStatus;
    QuestionDifficulty nextDifficulty;

    if(score.getCorrectStatus() == CorrectStatus.CORRECT) {
      drillStatus = DrillStatus.DRILL_UP;
      nextDifficulty = questionDifficultyRelation.getUpDifficulty();
    }else {
      drillStatus = DrillStatus.DRILL_DOWN;
      nextDifficulty = questionDifficultyRelation.getDownDifficulty();
    }

    return new CalcQuestionDifficulty(score.getQuestionType(), nextDifficulty, drillStatus);
  }

  public List<CalcQuestionDifficulty> getDifficultyByPrevLessonResult() {
    // todo 이전 레슨의 데이터를 가져와서 유형별 문제 난이도를 계산 하는 기능 추가
    return List.of(
        new CalcQuestionDifficulty(QuestionType.FACT, QuestionDifficulty.TA, DrillStatus.NONE),
        new CalcQuestionDifficulty(QuestionType.INFERENCE, QuestionDifficulty.TB, DrillStatus.NONE),
        new CalcQuestionDifficulty(QuestionType.CRITICISM, QuestionDifficulty.TC, DrillStatus.NONE),
        new CalcQuestionDifficulty(QuestionType.CONCEPT, QuestionDifficulty.TA, DrillStatus.NONE));
  }

  public List<CalcQuestionDifficulty> getDifficultyByScore(List<Score> scoreList) {
    return scoreList.stream().map(this::getDifficulty).collect(Collectors.toList());
  }
}