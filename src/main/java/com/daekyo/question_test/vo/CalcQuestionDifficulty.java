package com.daekyo.question_test.vo;

import com.daekyo.question_test.vo.enum_vo.DrillStatus;
import com.daekyo.question_test.vo.enum_vo.QuestionDifficulty;
import com.daekyo.question_test.vo.enum_vo.QuestionType;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
@EqualsAndHashCode
public class CalcQuestionDifficulty {
  private final QuestionType questionType;
  private final QuestionDifficulty questionDifficulty;
  private final DrillStatus drillStatus;
}
