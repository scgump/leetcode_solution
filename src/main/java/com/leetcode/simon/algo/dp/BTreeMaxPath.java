package com.leetcode.simon.algo.dp;

import com.leetcode.simon.Solution;
import com.leetcode.simon.model.tree.BinaryTreeNode;
import com.leetcode.simon.util.TreeTestHelper;

/**
 * https://leetcode.com/problems/binary-tree-maximum-path-sum/
 *
 * For this problem, a path is defined as any sequence of nodes from some starting node to any node
 * in the tree along the parent-child connections.
 * The path must contain at least one node and does not need to go through the root.
 */
public class BTreeMaxPath implements Solution {

  private int maxSum;

  public int maxPathSum(BinaryTreeNode<Integer> root) {
    maxSum = Integer.MIN_VALUE;
    maxPathSumInternal(root);
    return maxSum;
  }

  @SuppressWarnings("unchecked")
  private int maxPathSumInternal(BinaryTreeNode<Integer> node) {
    if (node == null) return 0;

    int left = Math.max(0, maxPathSumInternal(node.left));
    int right = Math.max(0, maxPathSumInternal(node.right));

    maxSum = Math.max(node.val + left + right, maxSum);
    return Math.max(left, right) + node.val;
  }

  @Override
  public void launchTestCase() {
    String[] testTree = {"-10", "9", "20", "null", "null", "15", "7"};

    BinaryTreeNode<Integer> root = TreeTestHelper.constructBinaryTree(testTree);

    int maxLen = maxPathSum(root);

    System.out.println(maxLen);
  }
}
