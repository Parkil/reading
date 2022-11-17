package com.daekyo.question_test.util;

import java.util.HashMap;
import java.util.Map;

public class CacheUtil {
  private CacheUtil(){}

  private static final Map<String,Object> cacheMap;

  static {
    cacheMap = new HashMap<>();
  }

  public static void set(String key, Object value) {
    cacheMap.put(key, value);
  }

  public static Object get(String key) {
    return cacheMap.get(key);
  }
}