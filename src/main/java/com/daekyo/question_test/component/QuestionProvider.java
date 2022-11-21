package com.daekyo.question_test.component;

import com.daekyo.question_test.vo.Question;
import com.daekyo.question_test.vo.Score;
import java.util.ArrayList;
import java.util.List;
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

  public void saveDrillStartQuestionList() {
    Pair<List<Question>, List<Question>> classifyQuestionPair =
        classifyQuestion.classify(cache.getReserveQuestionList(), cache.getScoreList());

    cache.setDrillStartQuestionList(classifyQuestionPair.getLeft());
    cache.setReserveQuestionList(classifyQuestionPair.getRight());
  }

  public Question getDrillStartQuestion() {
    return cache.getDrillStartQuestionList().remove(0);
  }

  public Question getDrillNextQuestion(Score score) {
    List<Score> wrapperList = new ArrayList<>();
    wrapperList.add(score);

    Pair<List<Question>, List<Question>> classifyQuestionPair =
        classifyQuestion.classify(cache.getReserveQuestionList(), wrapperList);

    cache.setReserveQuestionList(classifyQuestionPair.getRight());
    cache.setCurrentQuestionList(classifyQuestionPair.getLeft());

    return (classifyQuestionPair.getLeft() == null || classifyQuestionPair.getLeft().isEmpty()) ?
        null : classifyQuestionPair.getLeft().get(0);
  }
}