package com.daekyo.question_test.component;

import com.daekyo.question_test.Constant;
import com.daekyo.question_test.util.CacheUtil;
import com.daekyo.question_test.vo.Question;
import com.daekyo.question_test.vo.QuestionReply;
import com.daekyo.question_test.vo.Score;
import com.daekyo.question_test.vo.UserReply;
import com.daekyo.question_test.vo.enum_vo.CorrectStatus;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class Scoring {

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

  private Score convertScore(QuestionReply questionReply) {
    return new Score(questionReply.getQuestionKey(), questionReply.getQuestionGroup(),
        questionReply.getQuestionType(), questionReply.getQuestionDifficulty(),
        questionReply.getUserInputReply(), questionReply.getCorrect(),
        questionReply.getResult(), questionReply.getCommentary(),
        questionReply.getVideoUrl(), questionReply.getChkQuestion());
  }

  @SuppressWarnings("unchecked")
  public List<Score> scoring(List<UserReply> userReplyList) {
    List<Question> currentQuestionList = (List<Question>)CacheUtil.get(Constant.CURRENT_QUESTION_INFO_KEY);

    List<QuestionReply> questionReplyList = mergeQuestionAndReply(currentQuestionList, userReplyList);

    questionReplyList.forEach(questionReply -> questionReply.setResult(chkResult(questionReply)));
    return questionReplyList.stream().map(this::convertScore).collect(Collectors.toList());
  }
}