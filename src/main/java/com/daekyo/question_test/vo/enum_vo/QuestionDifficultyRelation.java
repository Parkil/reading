package com.daekyo.question_test.vo.enum_vo;

public enum QuestionDifficultyRelation {
  TA(QuestionDifficulty.TB, QuestionDifficulty.TA),
  TB(QuestionDifficulty.TC, QuestionDifficulty.TA),
  TC(QuestionDifficulty.TC, QuestionDifficulty.TB);

  private final QuestionDifficulty upDifficulty;
  private final QuestionDifficulty downDifficulty;

  QuestionDifficultyRelation(QuestionDifficulty upDifficulty, QuestionDifficulty downDifficulty) {
    this.upDifficulty = upDifficulty;
    this.downDifficulty = downDifficulty;
  }

  public QuestionDifficulty getUpDifficulty() {
    return upDifficulty;
  }

  public QuestionDifficulty getDownDifficulty() {
    return downDifficulty;
  }
}