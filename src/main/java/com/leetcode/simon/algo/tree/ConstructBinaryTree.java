package com.leetcode.simon.algo.tree;

import com.leetcode.simon.Solution;
import com.leetcode.simon.model.tree.BinaryTreeNode;
import com.leetcode.simon.util.TreeTestHelper;

/**
 * https://leetcode.com/problems/construct-binary-tree-from-inorder-and-postorder-traversal/
 *
 * Given inorder and postorder traversal of a tree, construct the binary tree.
 */
public class ConstructBinaryTree implements Solution {

  public BinaryTreeNode<Integer> buildTree(int[] inorder, int[] postorder) {
    if (inorder.length == 0 || postorder.length == 0)
      return null;

    return buildTreeInternal(inorder, 0, inorder.length - 1, postorder, 0, postorder.length - 1);
  }

  private BinaryTreeNode<Integer> buildTreeInternal(int[] inorder, int inStart, int inEnd,
      int[] postorder, int postStart, int postEnd) {

    if (inStart == inEnd) return new BinaryTreeNode<Integer>(inorder[inStart]);

    int rootVal = postorder[postEnd];
    BinaryTreeNode<Integer> rootNode = new BinaryTreeNode<Integer>(rootVal);

    int rootInorderIndex = indexOf(inorder, rootVal);

    // left child
    int leftInLen = rootInorderIndex - inStart;
    if (leftInLen <= 0) {
      rootNode.left = null;
    } else {
      int leftPostEnd = postStart + leftInLen - 1;
      rootNode.left = buildTreeInternal(inorder, inStart, rootInorderIndex - 1, postorder, postStart, leftPostEnd);
    }

    // right child
    int rightInLen = inEnd - rootInorderIndex;
    if (rightInLen <= 0) {
      rootNode.right = null;
    } else {
      int rightPostStart = postEnd - rightInLen;
      rootNode.right = buildTreeInternal(inorder, rootInorderIndex + 1, inEnd, postorder, rightPostStart, postEnd - 1);
    }

    return rootNode;
  }

  /**
   * index of a num in th array
   */
  private int indexOf(int[] array, int num) {
    for (int i = 0; i < array.length; i++) {
      if (num == array[i]) {
        return i;
      }
    }
    return -1;
  }

  public void launchTestCase() {
    int[] inorder = {9, 3, 15, 20, 7};
    int[] postorder = {9, 15, 7, 20, 3};

    BinaryTreeNode root = buildTree(inorder, postorder);

    TreeTestHelper.printTree(root);
  }
}
