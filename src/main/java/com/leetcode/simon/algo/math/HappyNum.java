package com.leetcode.simon.algo.math;

import com.leetcode.simon.Solution;
import java.util.HashSet;
import java.util.Set;

/**
 * https://leetcode.com/problems/happy-number/
 *
 * A happy number is a number defined by the following process: Starting with any positive integer,
 * replace the number by the sum of the squares of its digits,
 * and repeat the process until the number equals 1 (where it will stay),
 * or it loops endlessly in a cycle which does not include 1.
 * Those numbers for which this process ends in 1 are happy numbers.
 */
public class HappyNum implements Solution {

  public boolean isHappy(int n) {
    Set<Integer> set = new HashSet<>();

    while (set.add(n)) {
      n = digitSquareSum(n);
      if (n == 1) return true;
    }

    return false;
  }

  private int digitSquareSum(int n) {
    int sum = 0;
    int tmp;

    while (n > 0) {
      tmp = n % 10;
      sum += tmp * tmp;
      n = n/10;
    }

    return sum;
  }

  @Override
  public void launchTestCase() {
    System.out.println(isHappy(19));
  }
}
