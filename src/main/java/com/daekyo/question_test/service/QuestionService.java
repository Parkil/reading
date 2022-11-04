package com.daekyo.question_test.service;

import com.daekyo.question_test.Constant;
import com.daekyo.question_test.vo.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class QuestionService {
    public Lesson genTestLesson() {

        List<Question> questionList = new ArrayList<>();
        questionList.add(new Question("key1", "문학 질문1", "1"));
        questionList.add(new Question("key2", "문학 질문2", "2"));
        questionList.add(new Question("key3", "문학 질문3", "3"));
        questionList.add(new Question("key4", "문학 질문4", "4"));

        Text text = new Text("문학 지문", questionList);

        List<Question> questionList2 = new ArrayList<>();
        questionList2.add(new Question("key5", "비문학 질문1", "1"));
        questionList2.add(new Question("key6", "비문학 질문2", "2"));
        questionList2.add(new Question("key7", "비문학 질문3", "3"));
        questionList2.add(new Question("key8", "비문학 질문4", "4"));

        Text text1 = new Text("비 문학 지문", questionList2);

        List<Text> textList = new ArrayList<>();
        textList.add(text);

        List<Text> textList1 = new ArrayList<>();
        textList1.add(text1);

        SubLesson subLesson1 = new SubLesson(textList);
        SubLesson subLesson2 = new SubLesson(textList1);

        List<SubLesson> subLessonList = new ArrayList<>();
        subLessonList.add(subLesson1);
        subLessonList.add(subLesson2);

        return new Lesson(subLessonList);
    }

    public ReplyResponse replyAndNextQuestion(HttpSession httpSession, String questionKey, String reply) {
        Text text = (Text)httpSession.getAttribute("questionInfo");

        if(text == null) {
            throw new RuntimeException("session data null");
        }

        List<Reply> replyList = (List<Reply>)httpSession.getAttribute(Constant.HTTP_SESSION_REPLY_INFO_KEY);
        Reply currentReply = new Reply(questionKey, reply);

        replyList.remove(currentReply);
        replyList.add(currentReply);

        if(!text.getQuestionListIterator().hasNext()) {
            throw new RuntimeException("last question"); //후처리 (채점, 다음지문 표시, 채점후 별도 로직....)
        }

        return new ReplyResponse("next question", text.getQuestionListIterator().next());
    }

    public List<Reply> findReplyList(HttpSession httpSession) {
        return (List<Reply>)httpSession.getAttribute(Constant.HTTP_SESSION_REPLY_INFO_KEY);
    }
}