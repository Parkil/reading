package com.daekyo.question_test.util;


import com.daekyo.question_test.vo.Question;
import com.daekyo.question_test.vo.enum_vo.QuestionDifficulty;
import com.daekyo.question_test.vo.enum_vo.QuestionType;
import com.daekyo.question_test.vo.Text;
import java.util.ArrayList;
import java.util.List;

public class DummyUtil {
  private DummyUtil() {}

  private static Question getChkQuestion(String questionStr) {
    return new Question("22", QuestionType.FACT, QuestionDifficulty.TC, questionStr, "ㅅㅂ", null, null, null);
  }

  private static List<Question> getLiteratureQList() {
    List<Question> literatureQList = new ArrayList<>();
    literatureQList.add(new Question("1", QuestionType.FACT, QuestionDifficulty.TC, "문학-사실-난이도 상", "1", "해설1", "videourl1", getChkQuestion("확인문제1")));
    literatureQList.add(new Question("2", QuestionType.FACT, QuestionDifficulty.TB, "문학-사실-난이도 중", "1", "해설2", "videourl2", getChkQuestion("확인문제2")));
    literatureQList.add(new Question("3", QuestionType.FACT, QuestionDifficulty.TA, "문학-사실-난이도 하", "1", "해설3", "videourl3", getChkQuestion("확인문제3")));

    literatureQList.add(new Question("4", QuestionType.INFERENCE, QuestionDifficulty.TC, "문학-추론-난이도 상", "1", "해설4", "videourl4", getChkQuestion("확인문제4")));
    literatureQList.add(new Question("5", QuestionType.INFERENCE, QuestionDifficulty.TB, "문학-추론-난이도 중", "1", "해설5", "videourl5", getChkQuestion("확인문제5")));
    literatureQList.add(new Question("6", QuestionType.INFERENCE, QuestionDifficulty.TA, "문학-추론-난이도 하", "1", "해설6", "videourl6", getChkQuestion("확인문제6")));

    literatureQList.add(new Question("7", QuestionType.CRITICISM, QuestionDifficulty.TC, "문학-비판-난이도 상", "1", "해설7", "videourl7", getChkQuestion("확인문제7")));
    literatureQList.add(new Question("8", QuestionType.CRITICISM, QuestionDifficulty.TB, "문학-비판-난이도 중", "1", "해설8", "videourl8", getChkQuestion("확인문제8")));
    literatureQList.add(new Question("9", QuestionType.CRITICISM, QuestionDifficulty.TA, "문학-비판-난이도 하", "1", "해설9", "videourl9", getChkQuestion("확인문제9")));

    literatureQList.add(new Question("10", QuestionType.CONCEPT, QuestionDifficulty.TC, "문학-개념-난이도 상", "1", "해설10", "videourl10", getChkQuestion("확인문제10")));
    literatureQList.add(new Question("11", QuestionType.CONCEPT, QuestionDifficulty.TB, "문학-개념-난이도 중", "1", "해설11", "videourl11", getChkQuestion("확인문제11")));
    literatureQList.add(new Question("12", QuestionType.CONCEPT, QuestionDifficulty.TA, "문학-개념-난이도 하", "1", "해설12", "videourl12", getChkQuestion("확인문제12")));

    return literatureQList;
  }

  private static List<Question> getNonLiteratureQList() {
    List<Question> nonLiteratureQList = new ArrayList<>();
    nonLiteratureQList.add(new Question("13", QuestionType.FACT, QuestionDifficulty.TC, "비문학-사실-난이도 상", "1", "해설1", "videourl13", getChkQuestion("확인문제13")));
    nonLiteratureQList.add(new Question("14", QuestionType.FACT, QuestionDifficulty.TB, "비문학-사실-난이도 중", "1", "해설1", "videourl14", getChkQuestion("확인문제14")));
    nonLiteratureQList.add(new Question("15", QuestionType.FACT, QuestionDifficulty.TA, "비문학-사실-난이도 하", "1", "해설1", "videourl15", getChkQuestion("확인문제15")));

    nonLiteratureQList.add(new Question("16", QuestionType.INFERENCE, QuestionDifficulty.TC, "비문학-추론-난이도 상", "1", "해설1", "videourl16", getChkQuestion("확인문제16")));
    nonLiteratureQList.add(new Question("17", QuestionType.INFERENCE, QuestionDifficulty.TB, "비문학-추론-난이도 중", "1", "해설1", "videourl17", getChkQuestion("확인문제17")));
    nonLiteratureQList.add(new Question("18", QuestionType.INFERENCE, QuestionDifficulty.TA, "비문학-추론-난이도 하", "1", "해설1", "videourl18", getChkQuestion("확인문제18")));

    nonLiteratureQList.add(new Question("19", QuestionType.CRITICISM, QuestionDifficulty.TC, "비문학-비판-난이도 상", "1", "해설1", "videourl19", getChkQuestion("확인문제19")));
    nonLiteratureQList.add(new Question("20", QuestionType.CRITICISM, QuestionDifficulty.TB, "비문학-비판-난이도 중", "1", "해설1", "videourl20", getChkQuestion("확인문제20")));
    nonLiteratureQList.add(new Question("21", QuestionType.CRITICISM, QuestionDifficulty.TA, "비문학-비판-난이도 하", "1", "해설1", "videourl21", getChkQuestion("확인문제21")));

    nonLiteratureQList.add(new Question("22", QuestionType.CONCEPT, QuestionDifficulty.TC, "비문학-개념-난이도 상", "1", "해설1", "videourl22", getChkQuestion("확인문제22")));
    nonLiteratureQList.add(new Question("23", QuestionType.CONCEPT, QuestionDifficulty.TB, "비문학-개념-난이도 중", "1", "해설1", "videourl23", getChkQuestion("확인문제23")));
    nonLiteratureQList.add(new Question("24", QuestionType.CONCEPT, QuestionDifficulty.TA, "비문학-개념-난이도 하", "1", "해설1", "videourl24", getChkQuestion("확인문제24")));

    return nonLiteratureQList;
  }

  public static Text getLiteratureText() {
    return new Text("문학 지문", getLiteratureQList());
  }

  public static Text getNonLiteratureText() {
    return new Text("비 문학 지문", getNonLiteratureQList());
  }
}
