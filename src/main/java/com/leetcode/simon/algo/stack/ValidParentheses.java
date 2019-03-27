package com.leetcode.simon.algo.stack;

import com.leetcode.simon.Solution;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

/**
 * https://leetcode.com/problems/valid-parentheses/
 *
 * Given a string containing just the characters '(', ')', '{', '}', '[' and ']',
 * determine if the input string is valid.
 */
public class ValidParentheses implements Solution {

  public boolean isValid(String s) {
    if (s == null || s.length() == 0) return true;

    Stack<Character> stack = new Stack<>();

    char curr;
    for (int i = 0; i < s.length(); i++) {
      curr = s.charAt(i);
      switch (curr) {
        case ')':
          if (stack.isEmpty() || stack.pop() != '(') return false;
          break;
        case ']':
          if (stack.isEmpty() || stack.pop() != '[') return false;
          break;
        case '}':
          if (stack.isEmpty() || stack.pop() != '{') return false;
          break;
        default:
          stack.push(curr);
      }
    }

    return stack.isEmpty();
  }

  @Override
  public void launchTestCase() {
    List<String> testCases = new LinkedList<>();

    testCases.add("()[]{}");
    testCases.add("{[]}");
    testCases.add("([)]");
    testCases.add("[");

    for (String s : testCases) {
      System.out.println("\"" + s + "\"" + " is valid? => " + isValid(s));
    }
  }
}
