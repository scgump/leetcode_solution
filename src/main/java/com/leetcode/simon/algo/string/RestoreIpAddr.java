package com.leetcode.simon.algo.string;

import com.leetcode.simon.Solution;
import java.util.LinkedList;
import java.util.List;

/**
 * https://leetcode.com/problems/restore-ip-addresses
 *
 * Given a string containing only digits,
 * restore it by returning all possible valid IP address combinations.
 */
public class RestoreIpAddr implements Solution {

  public List<String> restoreIpAddresses(String s) {
    return restoreIpInternal(s, 4);
  }

  /**
   * restore string to specific segments
   */
  private List<String> restoreIpInternal(String s, int size) {
    List<String> resList = new LinkedList<>();

    if (size < 1 || s.length() < size || s.length() > 3 * size) return resList;

    List<String> tmpList;
    String first, remain;

    if (size == 1) {
      if (isValid(s)) {
        resList.add(s);
      }
    } else {
      for (int i = 1; i <= 3; i++) {
        if (i <= s.length()) {
          first = s.substring(0, i);

          if (isValid(first)) {
            remain = s.substring(i);
            tmpList = restoreIpInternal(remain, size - 1);
            if (tmpList != null) {
              for (String suffix : tmpList) {
                resList.add(first + "." + suffix);
              }
            }
          }
        }
      }
    }

    return resList;
  }

  /**
   * valid segment
   */
  private boolean isValid(String s) {
    if (s == null || s.length() < 1 || s.length() > 3) return false;
    if (!"0".equals(s) && s.startsWith("0")) return false;
    int seg = Integer.parseInt(s);
    return seg <= 255 && seg >= 0;
  }

  @Override
  public void launchTestCase() {
    String input = "";

    List<String> out = restoreIpAddresses(input);

    for (String ip : out) {
      System.out.println(ip);
    }
  }
}
