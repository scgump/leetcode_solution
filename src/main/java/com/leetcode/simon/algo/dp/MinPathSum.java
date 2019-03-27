package com.leetcode.simon.algo.dp;

import com.leetcode.simon.Solution;

/**
 * https://leetcode.com/problems/minimum-path-sum/
 *
 * Given a m x n grid filled with non-negative numbers,
 * find a path from top left to bottom right which minimizes the sum of all numbers along its path.
 */
public class MinPathSum implements Solution {

  public int minPathSum(int[][] grid) {
    if (grid == null || grid.length == 0) return 0;
    if (grid[0].length == 0) return 0;

    final int m = grid.length;
    final int n = grid[0].length;

    int[][] dp = new int[m][n];
    // init
    dp[0][0] = grid[0][0];
    for (int i = 1; i < m; i++) {
      dp[i][0] = dp[i-1][0] + grid[i][0];
    }
    for (int i = 1; i < n; i++) {
      dp[0][i] = dp[0][i-1] + grid[0][i];
    }

    // dp
    for (int i = 1; i < m; i++) {
      for (int j = 1; j < n; j++) {
        dp[i][j] = Integer.min(dp[i-1][j], dp[i][j-1]) + grid[i][j];
      }
    }

    return dp[m-1][n-1];
  }

  @Override
  public void launchTestCase() {
    int[][] grid = new int[3][3];
    grid[0] = new int[]{1, 3, 1};
    grid[1] = new int[]{1, 5, 1};
    grid[2] = new int[]{4, 2, 1};

    System.out.println(minPathSum(grid));
  }
}
