package com.daekyo.question_test.vo;

import com.daekyo.question_test.vo.enum_vo.CorrectStatus;
import com.daekyo.question_test.vo.enum_vo.DrillStatus;
import com.daekyo.question_test.vo.enum_vo.QuestionDifficulty;
import com.daekyo.question_test.vo.enum_vo.QuestionGroup;
import com.daekyo.question_test.vo.enum_vo.QuestionType;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RequiredArgsConstructor
@Getter
@EqualsAndHashCode
public class QuestionReply {
    private final String questionKey;
    private final QuestionGroup questionGroup;
    private final QuestionType questionType;
    private final QuestionDifficulty questionDifficulty;

    private final String questionStr;
    private final String correct;
    private final String userInputReply;

    private final String commentary;
    private final String videoUrl;
    private final Question chkQuestion;

    private final DrillStatus drillStatus;

    @Setter
    private CorrectStatus result;
}