package com.daekyo.question_test.util;


import com.daekyo.question_test.vo.Question;
import com.daekyo.question_test.vo.enum_vo.QuestionDifficulty;
import com.daekyo.question_test.vo.enum_vo.QuestionType;
import com.daekyo.question_test.vo.Text;
import java.util.ArrayList;
import java.util.List;

public class DummyUtil {
  private DummyUtil() {}

  private static List<Question> getLiteratureQList() {
    List<Question> literatureQList = new ArrayList<>();
    literatureQList.add(new Question("1", QuestionType.FACT, QuestionDifficulty.TC, "문학-사실-난이도 상", "1"));
    literatureQList.add(new Question("2", QuestionType.FACT, QuestionDifficulty.TB, "문학-사실-난이도 중", "1"));
    literatureQList.add(new Question("3", QuestionType.FACT, QuestionDifficulty.TA, "문학-사실-난이도 하", "1"));

    literatureQList.add(new Question("4", QuestionType.INFERENCE, QuestionDifficulty.TC, "문학-추론-난이도 상", "1"));
    literatureQList.add(new Question("5", QuestionType.INFERENCE, QuestionDifficulty.TB, "문학-추론-난이도 중", "1"));
    literatureQList.add(new Question("6", QuestionType.INFERENCE, QuestionDifficulty.TA, "문학-추론-난이도 하", "1"));

    literatureQList.add(new Question("7", QuestionType.CRITICISM, QuestionDifficulty.TC, "문학-비판-난이도 상", "1"));
    literatureQList.add(new Question("8", QuestionType.CRITICISM, QuestionDifficulty.TB, "문학-비판-난이도 중", "1"));
    literatureQList.add(new Question("9", QuestionType.CRITICISM, QuestionDifficulty.TA, "문학-비판-난이도 하", "1"));

    literatureQList.add(new Question("10", QuestionType.CONCEPT, QuestionDifficulty.TC, "문학-개념-난이도 상", "1"));
    literatureQList.add(new Question("11", QuestionType.CONCEPT, QuestionDifficulty.TB, "문학-개념-난이도 중", "1"));
    literatureQList.add(new Question("12", QuestionType.CONCEPT, QuestionDifficulty.TA, "문학-개념-난이도 하", "1"));

    return literatureQList;
  }

  private static List<Question> getNonLiteratureQList() {
    List<Question> nonLiteratureQList = new ArrayList<>();
    nonLiteratureQList.add(new Question("13", QuestionType.FACT, QuestionDifficulty.TC, "비문학-사실-난이도 상", "1"));
    nonLiteratureQList.add(new Question("14", QuestionType.FACT, QuestionDifficulty.TB, "비문학-사실-난이도 중", "1"));
    nonLiteratureQList.add(new Question("15", QuestionType.FACT, QuestionDifficulty.TA, "비문학-사실-난이도 하", "1"));

    nonLiteratureQList.add(new Question("16", QuestionType.INFERENCE, QuestionDifficulty.TC, "비문학-추론-난이도 상", "1"));
    nonLiteratureQList.add(new Question("17", QuestionType.INFERENCE, QuestionDifficulty.TB, "비문학-추론-난이도 중", "1"));
    nonLiteratureQList.add(new Question("18", QuestionType.INFERENCE, QuestionDifficulty.TA, "비문학-추론-난이도 하", "1"));

    nonLiteratureQList.add(new Question("19", QuestionType.CRITICISM, QuestionDifficulty.TC, "비문학-비판-난이도 상", "1"));
    nonLiteratureQList.add(new Question("20", QuestionType.CRITICISM, QuestionDifficulty.TB, "비문학-비판-난이도 중", "1"));
    nonLiteratureQList.add(new Question("21", QuestionType.CRITICISM, QuestionDifficulty.TA, "비문학-비판-난이도 하", "1"));

    nonLiteratureQList.add(new Question("22", QuestionType.CONCEPT, QuestionDifficulty.TC, "비문학-개념-난이도 상", "1"));
    nonLiteratureQList.add(new Question("23", QuestionType.CONCEPT, QuestionDifficulty.TB, "비문학-개념-난이도 중", "1"));
    nonLiteratureQList.add(new Question("24", QuestionType.CONCEPT, QuestionDifficulty.TA, "비문학-개념-난이도 하", "1"));

    return nonLiteratureQList;
  }

  public static Text getLiteratureText() {
    return new Text("문학 지문", getLiteratureQList());
  }

  public static Text getNonLiteratureText() {
    return new Text("비 문학 지문", getNonLiteratureQList());
  }
}
