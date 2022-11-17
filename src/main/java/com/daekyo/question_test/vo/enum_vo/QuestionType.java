package com.daekyo.question_test.vo.enum_vo;

// 문항 유형 사실, 추론, 비판, 개념
public enum QuestionType {
  FACT("사실"),
  INFERENCE("추론"),
  CRITICISM("비판"),
  CONCEPT("개념");

  private final String desc;

  QuestionType(String desc) { this.desc = desc; }
}
