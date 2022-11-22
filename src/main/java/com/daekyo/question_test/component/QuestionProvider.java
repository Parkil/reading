package com.daekyo.question_test.component;

import com.daekyo.question_test.vo.Question;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class QuestionProvider {

  private final Cache cache;

  private final ClassifyQuestion classifyQuestion;

  public List<Question> getBaseQuestionList() {
    Pair<List<Question>, List<Question>> classifyQuestionPair
        = classifyQuestion.classify(cache.getReserveQuestionList());

    cache.setCurrentQuestionList(classifyQuestionPair.getLeft());
    cache.setReserveQuestionList(classifyQuestionPair.getRight());

    return classifyQuestionPair.getLeft();
  }

  public Question getDrillQuestion() {
    cacheDrillStartQuestionList();
    return isDrillOngoing() ? getDrillOnGoingQuestion() : getDrillStartQuestion();
  }

  private void cacheDrillStartQuestionList() {
    if(cache.isQuestionGroupIsBaseInScoreList()) {
      Pair<List<Question>, List<Question>> classifyQuestionPair =
          classifyQuestion.classify(cache.getReserveQuestionList(), cache.getScoreList());

      cache.setDrillStartQuestionList(classifyQuestionPair.getLeft());
      cache.setReserveQuestionList(classifyQuestionPair.getRight());
    }
  }

  private Question getDrillStartQuestion() {
    List<Question> questionList = cache.getDrillStartQuestionList();

    if(questionList.isEmpty()) {
      return null;
    }

    Question startQuestion = questionList.remove(0);
    cache.setCurrentDrillQuestionType(startQuestion.getQuestionType());

    return startQuestion;
  }

  private Question getDrillOnGoingQuestion() {
    Pair<List<Question>, List<Question>> classifyQuestionPair =
        classifyQuestion.classify(cache.getReserveQuestionList(), cache.getScoreList());

    cache.setReserveQuestionList(classifyQuestionPair.getRight());
    cache.setCurrentQuestionList(classifyQuestionPair.getLeft());

    Question drillOnGoingQuestion = (classifyQuestionPair.getLeft() == null
        || classifyQuestionPair.getLeft().isEmpty()) ?
        null : classifyQuestionPair.getLeft().get(0);

    // 드릴 연관문제가 다 떨어진 경우 드릴 시작문제를 반환하도록 설정
    return (drillOnGoingQuestion == null) ? getDrillStartQuestion() : drillOnGoingQuestion;
  }

  private boolean isDrillOngoing() {
    return cache.getCurrentDrillQuestionType() != null && cache.getCurrentQuestionList().stream()
        .allMatch(row -> row.getQuestionType() == cache.getCurrentDrillQuestionType());
  }
}