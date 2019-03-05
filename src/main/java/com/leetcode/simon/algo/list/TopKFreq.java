package com.leetcode.simon.algo.list;

import com.leetcode.simon.Solution;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * https://leetcode.com/problems/top-k-frequent-elements/
 *
 * Given a non-empty array of integers, return the k most frequent elements.
 */
public class TopKFreq implements Solution {

  public List<Integer> topKFrequent(int[] nums, int k) {
    return usingPQ(nums, k);
  }

  /**
   * using priority queue
   */
  private List<Integer> usingPQ(int[] nums, int k) {
    Map<Integer, Integer> map = new HashMap<>(nums.length);
    for (int key : nums) {
      map.put(key, map.getOrDefault(key, 0) + 1);
    }

    Queue<Entry<Integer, Integer>> queue = new PriorityQueue<>(map.size(),
        (a, b) -> (b.getValue() - a.getValue()));

    for (Entry<Integer, Integer> entry : map.entrySet()) {
      queue.add(entry);
    }

    List<Integer> list = new LinkedList<>();
    Entry<Integer, Integer> entry;
    while (list.size() < k) {
      entry = queue.poll();
      list.add(entry.getKey());
    }

    return list;
  }

  @Override
  public void launchTestCase() {
    int[] nums = {1, 1, 1, 2, 2, 3};
    int k = 2;

    for (int key : topKFrequent(nums, k)) {
      System.out.println(key);
    }
  }
}
