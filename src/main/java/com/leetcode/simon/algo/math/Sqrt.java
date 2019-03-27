package com.leetcode.simon.algo.math;

import com.leetcode.simon.Solution;

/**
 * https://leetcode.com/problems/sqrtx/
 *
 * Compute and return the square root of x, where x is guaranteed to be a non-negative integer.
 * Since the return type is an integer, the decimal digits are truncated and
 * only the integer part of the result is returned.
 */
public class Sqrt implements Solution {

  public int mySqrt(int x) {
    if (x == 0) return 0;

    int left = 1, right = Integer.MAX_VALUE;
    while (true) {
      int mid = left + (right - left)/2;
      if (mid > x/mid) {
        right = mid - 1;
      } else {
        if (mid + 1 > x/(mid + 1))
          return mid;
        left = mid + 1;
      }
    }
  }

  @Override
  public void launchTestCase() {

    System.out.println(mySqrt(8));
  }
}
