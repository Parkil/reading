package com.daekyo.question_test.vo.enum_vo;

public enum QuestionGroup {
  BASE("기준문제"),
  DRILL("드릴문제");

  private final String desc;

  QuestionGroup(String desc) { this.desc = desc; }
}