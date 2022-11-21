package com.daekyo.question_test.vo.enum_vo;

public enum DrillStatus {
  DRILL_UP("드릴업", 1, 0),
  DRILL_DOWN("드릴다운" ,0, -1),
  NONE("해당사항없음", 0, 0);

  private final String desc;
  private final int correctScore;
  private final int inCorrectScore;

  DrillStatus(String desc, int correctScore, int inCorrectScore) {
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