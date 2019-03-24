package com.leetcode.simon.util;

import java.util.Map;
import java.util.Map.Entry;

/**
 * Created by csun1 on 24/03/2019.
 */
public class PrintUtil {
  private PrintUtil() {}

  /**
   * print map
   */
  public static <K, V> void printMap(Map<K, V> map) {
    System.out.println("----map: " + map.toString());
    for (Entry<K, V> entry: map.entrySet()) {
      System.out.println(entry.getKey() + " -> " + entry.getValue());
    }
  }
}
