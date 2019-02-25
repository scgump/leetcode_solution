package com.leetcode.simon;

import com.leetcode.simon.model.tree.BinaryTreeNode;
import com.leetcode.simon.util.TreeTestHelper;

/**
 * Created by csun1 on 24/02/2019.
 */
public class WildTest {

  public static void main(String[] args) {

    String[] values = {"0", "1", "2", "null", "3", "4", "null", "null", "null", "5"};

    BinaryTreeNode root = TreeTestHelper.constructBinaryTree(values);

    TreeTestHelper.printTree(root);
  }
}
