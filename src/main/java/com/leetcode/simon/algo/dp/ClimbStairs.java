package com.leetcode.simon.algo.dp;

import com.leetcode.simon.Solution;

/**
 * https://leetcode.com/problems/climbing-stairs
 *
 * Each time you can either climb 1 or 2 steps. In how many distinct ways can you climb to the top?
 */
public class ClimbStairs implements Solution {

  public int climbStairs(int n) {
    if (n == 1) return 1;
    if (n == 2) return 2;

    int[] dp = new int[n];

    // init
    dp[0] = 1;
    dp[1] = 2;

    for (int i=2; i<n; i++) {
      dp[i] = dp[i-1] + dp[i-2];
    }

    return dp[n-1];
  }


  @Override
  public void launchTestCase() {
    int n = 3;

    System.out.println(climbStairs(n));
  }
}
