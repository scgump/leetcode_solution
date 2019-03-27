package com.leetcode.simon.algo.dp;

import com.leetcode.simon.Solution;

/**
 * https://leetcode.com/problems/coin-change/
 *
 * You are given coins of different denominations and a total amount of money amount.
 * Write a function to compute the fewest number of coins that you need to make up that amount.
 * If that amount of money cannot be made up by any combination of the coins, return -1.
 */
public class CoinChange implements Solution {

  public int coinChange(int[] coins, int amount) {
    if (coins == null || coins.length == 0 || amount == 0) return 0;

    // min count for amount
    int[] dp = new int[amount+1];
    dp[0] = 0;

    int min, tmp;
    for (int i=1; i<= amount; i++) {
      min = -1;
      for (int coin : coins) {
        if (coin <= i && dp[i-coin] != -1) {
          tmp = dp[i-coin] + 1;
          min = min < 0 ? tmp : Integer.min(min, tmp);
        }
      }
      dp[i] = min;
    }

    return dp[amount];
  }

  @Override
  public void launchTestCase() {
    int[] coins = {1, 2, 5};
    int amount = 11;

    System.out.println(coinChange(coins, amount));
  }
}
