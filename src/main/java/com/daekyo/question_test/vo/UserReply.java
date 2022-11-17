package com.daekyo.question_test.vo;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString.Exclude;

@RequiredArgsConstructor
@Getter
@EqualsAndHashCode
public class Reply {
    private final String questionKey;
    private final String userInputReply;

    @Setter
    private String correct;
}