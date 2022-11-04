package com.daekyo.question_test.vo;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.List;

@RequiredArgsConstructor
@Getter
@EqualsAndHashCode
public class Lesson {
    private final List<SubLesson> subLessonList;
}
