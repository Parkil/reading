package com.daekyo.question_test.vo.enum_vo;

public enum CorrectStatus {
  CORRECT("정답"),
  INCORRECT("오답");

  private final String desc;

  CorrectStatus(String desc) { this.desc = desc; }
}