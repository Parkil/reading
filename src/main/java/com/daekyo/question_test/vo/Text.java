package com.daekyo.question_test.vo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.util.List;
import java.util.ListIterator;

@Getter
@EqualsAndHashCode
public class Text {
    private final String textStr;

    private final List<Question> questionList;

    @JsonIgnore
    private ListIterator<Question> questionListIterator;

    public Text(String textStr, List<Question> questionList) {
        this.textStr = textStr;
        this.questionList = questionList;

        if(questionList != null && !questionList.isEmpty()) {
            this.questionListIterator = questionList.listIterator();
        }
    }
}