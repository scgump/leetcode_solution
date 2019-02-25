package com.leetcode.simon.algo.list;

import com.leetcode.simon.Solution;
import java.util.HashMap;
import java.util.Map;

/**
 * https://leetcode.com/problems/lru-cache/
 *
 * Design and implement a data structure for Least Recently Used (LRU) cache.
 * It should support the following operations: get and put.
 *
 * get(key) - Get the value (will always be positive) of the key if the key exists in the cache,
 *     otherwise return -1.
 * put(key, value) - Set or insert the value if the key is not already present.
 *     When the cache reached its capacity, it should invalidate the least recently used item before inserting a new item.
 *
 * Follow up:
 * Could you do both operations in O(1) time complexity?
 */
public class LRUCache implements Solution {

  private final int capacity;
  private Map<Integer, Node> map;
  private Node head, tail;

  private int currSize;

  public LRUCache() { this(0); }

  public LRUCache(int capacity) {
    this.capacity = capacity;
    currSize = 0;
    map = new HashMap<Integer, Node>(capacity + 1);

    head = new Node(0, 0);
    tail = new Node(0, 0);

    head.next = tail;
    tail.prev = head;
    head.prev = tail.next = null;

    printList();
  }

  public int get(int key) {
    System.out.println("getting " + key);

    if (map.containsKey(key)) {
      Node node = map.get(key);
      moveNodeToHead(node);

      printList();

      return node.val;
    } else {
      return -1;
    }
  }

  public void put(int key, int value) {

    System.out.println("putting " + key + ", " + value);

    Node node;
    if (map.containsKey(key)) {
      node = map.get(key);
      node.val = value;
      moveNodeToHead(node);
    } else {
      node = new Node(key, value);
      insertNodeToHead(node);
      map.put(key, node);

      if (currSize < capacity) {
        currSize++;
      } else {
        Node victim = tail.prev;
        deleteNode(victim);
        map.remove(victim.key);

        System.out.println("map size: " + map.size());
      }
    }

    printList();
  }

  /*
   * ==========================================
   * inner double lined list
   * ==========================================
   */

  private class Node {
    int key, val;
    Node prev, next;

    Node(int key, int val) {
      this.key = key;
      this.val = val;
    }
  }

  /**
   * delete node from list,
   */
  private void deleteNode(Node node) {
    if (node != null) {
      node.prev.next = node.next;
      node.next.prev = node.prev;
      node.next = node.prev = null;
    }
  }

  /**
   * insert a node in the head
   */
  private void insertNodeToHead(Node node) {
    node.prev = head;
    node.next = head.next;
    head.next.prev = node;
    head.next = node;
  }

  /**
   * move a node into head
   */
  private void moveNodeToHead(Node node) {
    deleteNode(node);
    insertNodeToHead(node);
  }

  /**
   * print the list, for debug
   */
  private void printList() {
    StringBuilder sb = new StringBuilder();
    sb.append("HEAD");

    Node node = head.next;
    while (node != null) {
      sb.append(" -> ");

      if (node.next == null) {
        sb.append("TAIL");
      } else {
        sb.append("(")
        .append(node.key)
        .append(",")
        .append(node.val)
        .append(")");
      }

      node = node.next;
    }
    System.out.println(sb);
  }


  public void launchTestCase() {
    LRUCache cache = new LRUCache(10);

    String[] ops = {"put","put","put","put","put","get","put","get","get","put","get","put","put","put","get","put","get","get","get","get","put","put","get","get","get","put","put","get","put","get","put","get","get","get","put","put","put","get","put","get","get","put","put","get","put","put","put","put","get","put","put","get","put","put","get","put","put","put","put","put","get","put","put","get","put","get","get","get","put","get","get","put","put","put","put","get","put","put","put","put","get","get","get","put","put","put","get","put","put","put","get","put","put","put","get","get","get","put","put","put","put","get","put","put","put","put","put","put","put"};
    String[] args = {"10,13","3,17","6,11","10,5","9,10","13","2,19","2","3","5,25","8","9,22","5,5","1,30","11","9,12","7","5","8","9","4,30","9,3","9","10","10","6,14","3,1","3","10,11","8","2,14","1","5","4","11,4","12,24","5,18","13","7,23","8","12","3,27","2,12","5","2,9","13,4","8,18","1,7","6","9,29","8,21","5","6,30","1,12","10","4,15","7,22","11,26","8,17","9,29","5","3,4","11,30","12","4,29","3","9","6","3,4","1","10","3,29","10,28","1,20","11,13","3","3,12","3,8","10,9","3,26","8","7","5","13,17","2,27","11,15","12","9,19","2,15","3,16","1","12,17","9,1","6,19","4","5","5","8,1","11,7","5,2","9,28","1","2,2","7,4","4,22","7,24","9,26","13,28","11,26"};

    if (ops.length != args.length) {
      throw new IllegalArgumentException();
    }

    String[] res = new String[ops.length];

    int key, val;
    String[] segs;
    for (int i =0; i<ops.length; i++) {

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
