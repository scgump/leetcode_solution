package com.leetcode.simon.util;

import com.leetcode.simon.model.tree.BinaryTreeNode;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by csun1 on 24/02/2019.
 */
public class TreeTestHelper {
  private TreeTestHelper(){}

  /**
   * construct a binary tree from a string array
   * `null` means empty node
   */
  @SuppressWarnings("unchecked")
  public static BinaryTreeNode constructBinaryTree(String[] values) {
    if (values.length <= 0) return null;

    BinaryTreeNode[] nodes = new BinaryTreeNode[values.length];

    BinaryTreeNode node, parent;
    int parentIndex;
    for (int i = 0; i < values.length; i++) {
      node = null;
      if (!"null".equals(values[i])) {
        node = new BinaryTreeNode(Integer.valueOf(values[i]));
        node.left = node.right = null;
      }

      nodes[i] = node;

      if (i != 0 && node != null) {
        parentIndex = (i - 1) / 2;
        parent = nodes[parentIndex];
        if (parent != null) {
          if (i % 2 == 1) {
            parent.left = node;
          } else {
            parent.right = node;
          }
        }
      }
    }

    return nodes[0];
  }

  /**
   * print the binary tree
   */
  public static void printTree(BinaryTreeNode root) {
    if (root == null) {
      System.out.println("Empty Tree!");
    } else {
      printNodesInternal(Collections.singletonList(root), 1, height(root));
    }
  }

  /**
   * Height of a tree
   */
  public static int height(BinaryTreeNode root) {
    if (root == null) return 0;

    int left = height(root.left);
    int right = height(root.right);
    return Math.max(left, right) + 1;
  }

  /**
   * print node internal
   */
  private static void printNodesInternal(List<BinaryTreeNode> nodes, int level, int maxLevel) {
    if (nodes.isEmpty() || level > maxLevel) return;

    int floor = maxLevel - level;
    int edgeLines = (int) Math.pow(2, (Math.max(floor - 1, 0)));
    int firstSpaces = (int) Math.pow(2, (floor)) - 1;
    int betweenSpaces = (int) Math.pow(2, (floor + 1)) - 1;

    printWhiteSpaces(firstSpaces);

    List<BinaryTreeNode> children = new ArrayList<BinaryTreeNode>();
    for (BinaryTreeNode node : nodes) {
      if (node != null) {
        System.out.print(node.val);
        children.add(node.left);
        children.add(node.right);
      } else {
        children.add(null);
        children.add(null);
        System.out.print(" ");
      }

      printWhiteSpaces(betweenSpaces);
    }
    System.out.println();

    BinaryTreeNode node;
    for (int i = 1; i <= edgeLines; i++) {
      for (int j = 0; j < nodes.size(); j++) {
        node = nodes.get(j);

        printWhiteSpaces(firstSpaces - i);

        if (node == null) {
          printWhiteSpaces(edgeLines + edgeLines + i + 1);
          continue;
        }

        if (node.left != null) {
          System.out.print("/");
        } else {
          System.out.print(" ");
        }

        printWhiteSpaces(i + i - 1);

        if (node.right != null) {
          System.out.print("\\");
        } else {
          System.out.print(" ");
        }

        printWhiteSpaces(edgeLines + edgeLines - i);
      }
      System.out.println();
    }

    printNodesInternal(children, level + 1, maxLevel);
  }

  /**
   * print white spaces
   */
  private static void printWhiteSpaces(int count) {
    for (int i = 0; i < count; i++) {
      System.out.print(" ");
    }
  }
}
