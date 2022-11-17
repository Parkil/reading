package com.daekyo.question_test.vo;

// 문항 난이도
public enum QuestionDifficulty {
  TA("하", 4, 0),
  TB("중", 6, 2),
  TC("상", 8, 4);
  private final String desc;
  private final int correctScore;
  private final int inCorrectScore;

  QuestionDifficulty(String desc, int correctScore, int inCorrectScore) {
    this.desc = desc;
    this.correctScore = correctScore;
    this.inCorrectScore = inCorrectScore;
  }

  public String getDesc() {
    return desc;
  }

  public int getCorrectScore() {
    return correctScore;
  }

  public int getInCorrectScore() {
    return inCorrectScore;
  }
}