package com.daekyo.question_test.vo;

import com.daekyo.question_test.vo.enum_vo.CorrectStatus;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@EqualsAndHashCode
@RequiredArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Score {
    private final String questionKey;
    private final String userInputReply;
    private final String correct;
    private final CorrectStatus correctStatus;

    private final String commentary;
    private final String videoUrl;
    private final Question chkQuestion;
}