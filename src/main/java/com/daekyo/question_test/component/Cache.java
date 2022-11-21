package com.daekyo.question_test.component;

import com.daekyo.question_test.Constant;
import com.daekyo.question_test.util.CacheUtil;
import com.daekyo.question_test.vo.Question;
import com.daekyo.question_test.vo.Score;
import com.daekyo.question_test.vo.Text;
import java.util.Collections;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class Cache {

  public void setScoreList(List<Score> scoreList) {
    CacheUtil.set(Constant.PREV_SCORE_INFO_KEY, scoreList);
  }

  @SuppressWarnings("unchecked")
  public List<Score> getScoreList() {
    List<Score> scoreList =  (List<Score>)CacheUtil.get(Constant.PREV_SCORE_INFO_KEY);
    return (scoreList == null) ? Collections.emptyList() : scoreList;
  }

  public void initCache(Text text) {
    CacheUtil.set(Constant.TEXT_INFO_KEY, text);
    CacheUtil.set(Constant.ALL_QUESTION_INFO_KEY, text.getQuestionList());
  }

  public String getText() {
    return String.valueOf(CacheUtil.get(Constant.TEXT_INFO_KEY));
  }

  public void setReserveQuestionList(List<Question> questionList) {
    CacheUtil.set(Constant.ALL_QUESTION_INFO_KEY, questionList);
  }
  @SuppressWarnings("unchecked")
  public List<Question> getReserveQuestionList() {
    return (List<Question>)CacheUtil.get(Constant.ALL_QUESTION_INFO_KEY);
  }
  public void setCurrentQuestionList(List<Question> questionList) {
    CacheUtil.set(Constant.CURRENT_QUESTION_INFO_KEY, questionList);
  }

  @SuppressWarnings("unchecked")
  public List<Question> getCurrentQuestionList() {
    return (List<Question>)CacheUtil.get(Constant.CURRENT_QUESTION_INFO_KEY);
  }
  public void setDrillStartQuestionList(List<Question> questionList) {
    CacheUtil.set(Constant.DRILL_START_QUESTION_INFO_KEY, questionList);
  }

  @SuppressWarnings("unchecked")
  public List<Question> getDrillStartQuestionList() {
    return (List<Question>)CacheUtil.get(Constant.DRILL_START_QUESTION_INFO_KEY);
  }
}