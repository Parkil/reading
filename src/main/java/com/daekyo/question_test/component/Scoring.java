package com.daekyo.question_test.component;

import com.daekyo.question_test.vo.Question;
import com.daekyo.question_test.vo.QuestionReply;
import com.daekyo.question_test.vo.Score;
import com.daekyo.question_test.vo.UserReply;
import com.daekyo.question_test.vo.enum_vo.CorrectStatus;
import com.daekyo.question_test.vo.enum_vo.DrillStatus;
import com.daekyo.question_test.vo.enum_vo.QuestionGroup;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class Scoring {

  private final Cache cache;

  private List<QuestionReply> mergeQuestionAndReply(List<Question> currentQuestionList,
      List<UserReply> userReplyList) {
    // todo 출제된 문제와 다른 questionKey가 넘어올 경우 이를 처리해 주는 로직이 필요
    return currentQuestionList.stream().map(question -> {
      String userInputReply = userReplyList.stream()
          .filter(userReply -> userReply.getQuestionKey().equals(question.getQuestionKey()))
          .limit(1)
          .map(UserReply::getUserInputReply)
          .collect(Collectors.joining());

      return question.convertQuestionReply(userInputReply);
    }).collect(Collectors.toList());
  }

  private CorrectStatus chkResult(QuestionReply questionReply) {
    return (questionReply.getCorrect().equals(questionReply.getUserInputReply()))
        ? CorrectStatus.CORRECT : CorrectStatus.INCORRECT;
  }

  private int calcScore(QuestionReply questionReply) {
    CorrectStatus result = chkResult(questionReply);

    if(questionReply.getQuestionGroup() == QuestionGroup.BASE) {
      return result == CorrectStatus.CORRECT ?
        questionReply.getQuestionDifficulty().getCorrectScore()
        : questionReply.getQuestionDifficulty().getInCorrectScore();
    }else {
      DrillStatus drillStatus = questionReply.getDrillStatus();
      return result == CorrectStatus.CORRECT ?
          drillStatus.getCorrectScore() : drillStatus.getInCorrectScore();
    }
  }

  private Score convertScore(QuestionReply questionReply) {
    return new Score(questionReply.getQuestionKey(), questionReply.getQuestionGroup(),
        questionReply.getQuestionType(), questionReply.getQuestionDifficulty(),
        calcScore(questionReply), questionReply.getUserInputReply(),
        questionReply.getCorrect(), chkResult(questionReply), questionReply.getCommentary(),
        questionReply.getVideoUrl(), questionReply.getChkQuestion());
  }

  public List<Score> scoring(List<UserReply> userReplyList) {
    List<QuestionReply> questionReplyList =
        mergeQuestionAndReply(cache.getCurrentQuestionList(), userReplyList);

    List<Score> scoreList = questionReplyList.stream().map(this::convertScore).collect(Collectors.toList());
    cache.setScoreList(scoreList);

    // todo DB에 채점이력을 저장하는 기능 추가
    return scoreList;
  }

  // ===============================================================================================

  public List<Score> getPrevScore() {
    return cache.getScoreList();
  }

  private boolean isPrevScoreEmpty() {
    return cache.getScoreList() == null || cache.getScoreList().isEmpty();
  }

  public boolean isPrevScoreTypeBase() {
    if(isPrevScoreEmpty()) {
      return false;
    }

    return cache.getScoreList().stream()
        .allMatch(row -> row.getQuestionGroup() == QuestionGroup.BASE);
  }
}