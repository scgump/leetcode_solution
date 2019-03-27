package com.leetcode.simon.algo.dp;

import com.leetcode.simon.Solution;
import java.util.LinkedList;
import java.util.List;

/**
 * https://leetcode.com/problems/longest-valid-parentheses/
 *
 * Given a string containing just the characters '(' and ')',
 * find the length of the longest valid (well-formed) parentheses substring.
 */
public class LongestValidParentheses implements Solution {

  public int longestValidParentheses(String s) {
    if (s == null || s.length() <= 1) return 0;

    int n = s.length();
    int[] dp = new int[n];
    dp[0] = 0;

    int max = 0;

    char curr, prev = s.charAt(0);
    int backIdx;
    for (int i=1; i < n; i++) {
      curr = s.charAt(i);

      if (curr == '(') {
        dp[i] = 0;
      } else {
        if (prev == '(') {
          dp[i] = i >= 2 ? dp[i-2] + 2 : 2;
          max = Math.max(max, dp[i]);
        } else {
          backIdx = i - 1 - dp[i-1];
          if (backIdx >= 0 && s.charAt(backIdx) == '(') {
            dp[i] = dp[i-1] + 2 + (backIdx >= 1 ? dp[backIdx-1] : 0);
            max = Math.max(max, dp[i]);
          }
        }
      }

      prev = curr;
    }

    return max;
  }


  @Override
  public void launchTestCase() {
    List<String> testCases = new LinkedList<>();

    testCases.add("("); // expected 0
    testCases.add("(()"); // expected 2
    testCases.add(")()())"); // expected 4
    testCases.add("()(())"); // expected 6
    testCases.add("()(()"); // expected 2

    for (String s : testCases) {
      System.out.println("\"" + s + "\"" + " longest valid parentheses: " + longestValidParentheses(s));
    }
  }
}
