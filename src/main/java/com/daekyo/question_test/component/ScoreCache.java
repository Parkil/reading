package com.daekyo.question_test.component;

import com.daekyo.question_test.Constant;
import com.daekyo.question_test.util.CacheUtil;
import com.daekyo.question_test.vo.Score;
import java.util.Collections;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ScoreCache {

  public void setScoreList(List<Score> scoreList) {
    CacheUtil.set(Constant.PREV_SCORE_INFO_KEY, scoreList);
  }

  @SuppressWarnings("unchecked")
  public List<Score> getScoreList() {
    List<Score> scoreList =  (List<Score>)CacheUtil.get(Constant.PREV_SCORE_INFO_KEY);
    return (scoreList == null) ? Collections.emptyList() : scoreList;
  }

  public void setLatestScoreList(List<Score> scoreList) {
    CacheUtil.set(Constant.LATEST_SCORE_INFO_KEY, scoreList);
  }

  @SuppressWarnings("unchecked")
  public List<Score> getLatestScoreList() {
    List<Score> scoreList =  (List<Score>)CacheUtil.get(Constant.LATEST_SCORE_INFO_KEY);
    return (scoreList == null) ? Collections.emptyList() : scoreList;
  }
}