package com.leetcode.simon.algo.string;

import com.leetcode.simon.Solution;

/**
 * https://leetcode.com/problems/reverse-string/
 *
 * Write a function that reverses a string.
 * The input string is given as an array of characters char[].
 */
public class ReverseString implements Solution {

  public void reverseString(char[] s) {
    if (s == null || s.length <= 1) return;

    int i = 0, j = s.length - 1;
    while (i < j) {
      // swap s[i], s[j]
      s[j] = (char)(s[i] ^ s[j]);
      s[i] = (char)(s[i] ^ s[j]);
      s[j] = (char)(s[i] ^ s[j]);

      i++;
      j--;
    }
  }

  @Override
  public void launchTestCase() {
    char[] testCase = "hello".toCharArray();
    System.out.println("raw: " + new String(testCase));

    reverseString(testCase);
    System.out.println("reversed: " + new String(testCase));
  }
}
