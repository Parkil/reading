package com.daekyo.question_test.component;

import com.daekyo.question_test.Constant;
import com.daekyo.question_test.util.CacheUtil;
import com.daekyo.question_test.vo.Question;
import com.daekyo.question_test.vo.Score;
import com.daekyo.question_test.vo.Text;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class QuestionCache {

  private final QuestionCalcDifficulty questionCalcDifficulty;

  public void initCache(Text text) {
    CacheUtil.set(Constant.TEXT_INFO_KEY, text);
    CacheUtil.set(Constant.ALL_QUESTION_INFO_KEY, text.getQuestionList());
  }

  public String getText() {
    return String.valueOf(CacheUtil.get(Constant.TEXT_INFO_KEY));
  }

  @SuppressWarnings("unchecked")
  public List<Question> getCurrentQuestionList() {
    return (List<Question>)CacheUtil.get(Constant.CURRENT_QUESTION_INFO_KEY);
  }

  @SuppressWarnings("unchecked")
  public List<Question> getBaseQuestionList() {
    List<Question> reserveQuestionList = (List<Question>)CacheUtil.get(Constant.ALL_QUESTION_INFO_KEY);
    Pair<List<Question>, List<Question>> classifyQuestionPair
        = questionCalcDifficulty.classifyQuestion(reserveQuestionList);

    CacheUtil.set(Constant.CURRENT_QUESTION_INFO_KEY, classifyQuestionPair.getLeft());
    CacheUtil.set(Constant.ALL_QUESTION_INFO_KEY, classifyQuestionPair.getRight());

    return classifyQuestionPair.getLeft();
  }

  @SuppressWarnings("unchecked")
  public void saveDrillStartQuestionList(List<Score> scoreList) {
    List<Question> reserveQuestionList = (List<Question>)CacheUtil.get(Constant.ALL_QUESTION_INFO_KEY);
    Pair<List<Question>, List<Question>> classifyQuestionPair =
        questionCalcDifficulty.classifyQuestion(reserveQuestionList, scoreList);

    CacheUtil.set(Constant.CURRENT_QUESTION_INFO_KEY, classifyQuestionPair.getLeft());
    CacheUtil.set(Constant.ALL_QUESTION_INFO_KEY, classifyQuestionPair.getRight());
  }

  @SuppressWarnings("unchecked")
  public Question getDrillNextQuestion(Score score) {
    List<Question> reserveQuestionList = (List<Question>)CacheUtil.get(Constant.ALL_QUESTION_INFO_KEY);

    List<Score> wrapperList = new ArrayList<>();
    wrapperList.add(score);

    Pair<List<Question>, List<Question>> classifyQuestionPair =
        questionCalcDifficulty.classifyQuestion(reserveQuestionList, wrapperList);

    CacheUtil.set(Constant.ALL_QUESTION_INFO_KEY, classifyQuestionPair.getRight());

    Question result = (classifyQuestionPair.getLeft() == null || classifyQuestionPair.getLeft().isEmpty()) ?
        null : classifyQuestionPair.getLeft().get(0);

    CacheUtil.set(Constant.CURRENT_QUESTION_INFO_KEY, result);

    return result;
  }
}