package com.daekyo.question_test.vo;

import com.daekyo.question_test.vo.enum_vo.CorrectStatus;
import com.daekyo.question_test.vo.enum_vo.QuestionDifficulty;
import com.daekyo.question_test.vo.enum_vo.QuestionDifficultyRelation;
import com.daekyo.question_test.vo.enum_vo.QuestionType;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.tuple.Pair;

@RequiredArgsConstructor
@Getter
@EqualsAndHashCode
public class QuestionReply {
    private final String questionKey;
    private final QuestionType questionType;
    private final QuestionDifficulty questionDifficulty;

    private final String questionStr;
    private final String correct;
    private final String userInputReply;

    private final String commentary;
    private final String videoUrl;
    private final Question chkQuestion;

    public CorrectStatus score() {
        return scoring() ? CorrectStatus.CORRECT : CorrectStatus.INCORRECT;
    }

    private boolean scoring() {
        return correct.equals(userInputReply);
    }

    public Pair<QuestionType, QuestionDifficulty> getNextDifficulty() {
        QuestionDifficultyRelation questionDifficultyRelation =
            QuestionDifficultyRelation.valueOf(questionDifficulty.name());

        QuestionDifficulty nextDifficulty = scoring() ?
            questionDifficultyRelation.getUpDifficulty() :
            questionDifficultyRelation.getDownDifficulty();
        
        return Pair.of(questionType, nextDifficulty);
    }

    public Score convertScore() {
        return new Score(questionKey, userInputReply, correct, score(),
            commentary, videoUrl, chkQuestion);
    }
}