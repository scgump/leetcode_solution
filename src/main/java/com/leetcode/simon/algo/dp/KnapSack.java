package com.leetcode.simon.algo.dp;

import com.leetcode.simon.Solution;

/**
 * https://www.geeksforgeeks.org/0-1-knapsack-problem-dp-10/
 */
public class KnapSack implements Solution {

  public int knapSack(int w, int[] wt, int[] val) {

    int len = wt.length;

    int[][] dp = new int[len+1][w+1];

    for (int i=0; i < len+1; i++) {
      for (int j=0; j < w+1; j++) {
        if (i == 0 || j == 0) {
          dp[i][j] = 0;
        } else if (wt[i-1] <= j) {
          dp[i][j] = Math.max(val[i-1] + dp[i-1][j-wt[i-1]], dp[i-1][j]);
        } else {
          dp[i][j] = dp[i-1][j];
        }
      }
    }

    return dp[len][w];
  }


  @Override
  public void launchTestCase() {
    int[] val = {60, 100, 120};
    int[] wt = {10, 20, 30};
    int w = 50;

    System.out.println(knapSack(w, wt, val));
  }
}
