package com.leetcode.simon.algo.dp;

import com.leetcode.simon.Solution;
import com.leetcode.simon.util.PrintUtil;
import java.util.HashMap;
import java.util.Map;

/**
 * https://leetcode.com/problems/longest-consecutive-sequence/
 *
 * Given an unsorted array of integers, find the length of the longest consecutive elements sequence.
 * Your algorithm should run in O(n) complexity.
 */
public class SingleLCS implements Solution {

  public int longestConsecutive(int[] nums) {
    // recording the length of lcs in the head & tail elements
    Map<Integer, Integer> map = new HashMap<>(nums.length);

    int res = 0;
    int left, right, sum;
    for (int num : nums) {
      if (!map.containsKey(num)) {
        left = map.containsKey(num - 1) ? map.get(num - 1) : 0;
        right = map.containsKey(num + 1) ? map.get(num + 1) : 0;
        sum = left + right + 1;

        // update head & tail lcs
        map.put(num, sum);
        if (left != 0) map.put(num - left, sum);
        if (right != 0) map.put(num + right, sum);

        res = Integer.max(res, sum);
      }
    }

    return res;
  }


  @Override
  public void launchTestCase() {
    int[] testNums = {4,0,-4,-2,2,5,2,0,-8,-8,-8,-8,-1,7,4,5,5,-4,6,6,-3};

    int lcs = longestConsecutive(testNums);

    System.out.println(lcs);
  }
}
