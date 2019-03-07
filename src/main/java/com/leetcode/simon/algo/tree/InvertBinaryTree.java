package com.leetcode.simon.algo.tree;

import com.leetcode.simon.Solution;
import com.leetcode.simon.model.tree.BinaryTreeNode;
import com.leetcode.simon.util.TreeTestHelper;

/**
 * https://leetcode.com/problems/invert-binary-tree/
 *
 * Invert a binary tree.
 */
public class InvertBinaryTree implements Solution {

  public BinaryTreeNode invertTree(BinaryTreeNode root) {
    if (root == null) return null;

    BinaryTreeNode left = invertTree(root.left);
    BinaryTreeNode right = invertTree(root.right);

    root.left = right;
    root.right = left;


    return root;
  }

  @Override
  public void launchTestCase() {
    String[] nodes = {"4", "2", "7", "1", "3" ,"6", "9"};

    System.out.println("Original tree: ");
    BinaryTreeNode root = TreeTestHelper.constructBinaryTree(nodes);
    TreeTestHelper.printTree(root);

    System.out.println("Inverted tree: ");
    root = invertTree(root);
    TreeTestHelper.printTree(root);
  }
}
