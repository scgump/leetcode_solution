package com.leetcode.simon.model.tree;

/**
 * Created by csun1 on 24/02/2019.
 */
public class BinaryTreeNode<T extends Comparable<?>> {
  public T val;
  public BinaryTreeNode left;
  public BinaryTreeNode right;

  public BinaryTreeNode(T val) { this.val = val; }
}
