package com.leetcode.simon.algo.string;

import com.leetcode.simon.Solution;

/**
 * https://leetcode.com/problems/implement-trie-prefix-tree/
 *
 * Implement a trie with insert, search, and startsWith methods.
 */
public class Trie implements Solution {

  private TrieNode root;

  public Trie() {
    root = new TrieNode('/');
  }

  /**
   * insert a word into the trie
   */
  public void insert(String word) {
    if (word != null) {
      TrieNode node = root;
      int index;
      for (int i=0; i < word.length(); i++) {
        index = word.charAt(i) - 'a';
        if (index < 0 || index > 25) {
          throw new IllegalArgumentException("Invalid word: " + word);
        }

        if (node.children[index] == null) {
          node.children[index] = new TrieNode(word.charAt(i));
        }

        node = node.children[index];
      }

      node.isWord = true;
    }
  }

  /**
   * search if the word is in the trie
   */
  public boolean search(String word) {
    if (word != null) {
      TrieNode node = root;
      int index;
      for (int i=0; i < word.length(); i++) {
        index = word.charAt(i) - 'a';
        if (index < 0 || index > 25) {
          throw new IllegalArgumentException("Invalid word: " + word);
        }

        if (node.children[index] == null) return false;

        node = node.children[index];
      }

      return node.isWord;
    }
    return false;
  }

  /**
   * check if there is any word staring with the prefix
   */
  public boolean startsWith(String prefix) {
    if (prefix != null) {
      TrieNode node = root;
      int index;
      for (int i = 0; i < prefix.length(); i++) {
        index = prefix.charAt(i) - 'a';
        if (index < 0 || index > 25) {
          throw new IllegalArgumentException("Invalid prefix: " + prefix);
        }

        if (node.children[index] == null) return false;

        node = node.children[index];
      }
      return true;
    }
    return false;
  }

  /**
   * Trie node
   */
  private class TrieNode {
    char data;
    boolean isWord;
    TrieNode[] children;

    public TrieNode(char data) {
      this.data = data;
      isWord = false;
      children = new TrieNode[26];
    }
  }


  public void launchTestCase() {

    System.out.println("insert apple");
    insert("apple");

    System.out.println("exist apple? " + search("apple"));
    System.out.println("exist app? " + search("app"));
    System.out.println("any word starts with app? " + startsWith("app"));

    System.out.println("insert app");
    insert("app");
    System.out.println("exist app? " + search("app"));
  }
}
