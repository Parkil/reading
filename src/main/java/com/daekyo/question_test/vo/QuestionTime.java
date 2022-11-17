package com.daekyo.question_test.vo;

import com.daekyo.question_test.vo.enum_vo.QuestionDifficulty;
import com.daekyo.question_test.vo.enum_vo.QuestionType;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
@EqualsAndHashCode
public class Question {
    private final String questionKey;
    private final QuestionType questionType;
    private final QuestionDifficulty questionDifficulty;
    private final String questionStr;
    private final String correct;

    public QuestionReply convertQuestionReply(String userInputReply)  {
        return new QuestionReply(questionKey,
            questionType,
            questionDifficulty,
            questionStr,
            correct,
            userInputReply);
    }
}