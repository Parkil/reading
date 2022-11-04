package com.daekyo.question_test.vo;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
@EqualsAndHashCode
public class Reply {
    private final String questionKey;
    private final String replyContents;
}