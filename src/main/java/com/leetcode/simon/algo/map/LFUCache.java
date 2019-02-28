package com.leetcode.simon.algo.map;

import com.leetcode.simon.Solution;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

/**
 * https://leetcode.com/problems/lfu-cache
 *
 * Design and implement a data structure for Least Frequently Used (LFU) cache.
 * It should support the following operations: get and put.
 */
public class LFUCache implements Solution {

  private int capacity;
  private int min;

  private Map<Integer, Integer> keyValue;
  private Map<Integer, Integer> keyFqcy;
  private Map<Integer, Set<Integer>> fqcySet;

  public LFUCache() { this(0); }

  public LFUCache(int capacity) {
    this.capacity = capacity;
    min = Integer.MIN_VALUE;

    keyValue = new HashMap<Integer, Integer>(capacity + 1);
    keyFqcy = new HashMap<Integer, Integer>(capacity + 1);
    fqcySet = new HashMap<Integer, Set<Integer>>(capacity + 1);

    fqcySet.put(1, new LinkedHashSet<Integer>());
  }

  public int get(int key) {
    if (!keyValue.containsKey(key)) return -1;

    // update key frequency
    final int fqcy = keyFqcy.get(key);
    keyFqcy.put(key, fqcy + 1);

    // update frequency
    Set<Integer> set = fqcySet.get(fqcy);
    set.remove(key);

    if (fqcySet.containsKey(fqcy + 1)) {
      set = fqcySet.get(fqcy + 1);
    } else {
      set = new LinkedHashSet<Integer>();
    }

    set.add(key);
    fqcySet.put(fqcy + 1, set);

    // update min
    if (fqcy == min && fqcySet.get(fqcy).size() == 0) min++;

    return keyValue.get(key);
  }

  public void put(int key, int value) {
    if (capacity <= 0) return;

    if (keyValue.containsKey(key)) {
      keyValue.put(key, value);
      get(key);
      return;
    }

    if (keyValue.size() >= capacity) {
      int victim = fqcySet.get(min).iterator().next();
      fqcySet.get(min).remove(victim);
      keyValue.remove(victim);
      keyFqcy.remove(victim);
    }

    keyValue.put(key, value);
    keyFqcy.put(key, 1);
    fqcySet.get(1).add(key);
    min = 1;
  }

  public void launchTestCase() {

    String[] ops = {"LFUCache","put","put","get","put","get","get","put","get","get","get"};
    String[] args = {"2","1,1","2,2","1","3,3","2","3","4,4","1","3","4"};

    // this case checks capacity with 0
//    String[] ops = {"LFUCache", "put", "get"};
//    String[] args = {"0", "0,0", "0"};

    if (ops.length != args.length) {
      throw new IllegalArgumentException();
    }

    if (!"LFUCache".equals(ops[0]))
      throw new IllegalArgumentException();

    int cap = Integer.valueOf(args[0]);
    LFUCache cache = new LFUCache(cap);

    String[] res = new String[ops.length];
    res[0] = "null";

    int key, val;
    String[] segs;
    for (int i = 1; i<ops.length; i++) {

      if ("put".equals(ops[i])) {
        segs = args[i].split(",");
        key = Integer.valueOf(segs[0]);
        val = Integer.valueOf(segs[1]);

        cache.put(key, val);
        res[i] = "null";
      } else {
        key = Integer.valueOf(args[i]);
        val = cache.get(key);
        res[i] = String.valueOf(val);
      }
    }

    StringBuilder sb = new StringBuilder();
    sb.append("[");
    boolean isFirst = true;
    for (String s : res) {
      if (isFirst) {
        sb.append(s);
        isFirst = false;
      } else {
        sb.append(",");
        sb.append(s);
      }
    }
    sb.append("]");

    System.out.println(sb);
  }
}
