package com.daekyo.question_test.component;

import com.daekyo.question_test.util.DummyUtil;
import com.daekyo.question_test.vo.Text;
import org.springframework.stereotype.Component;

@Component
public class QuestionDifficulty {
  public Text parse(String jsonStr) {
    // todo json 파싱 로직 필요
    return DummyUtil.getLiteratureText();
  }
}