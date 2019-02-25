package com.leetcode.simon.model.list;

/**
 * Created by Simon Suen on 2018/9/12.
 */
public class SingleListNode {
    public int val;
    public SingleListNode next;

    public SingleListNode(int val) {
        this.val = val;
    }

    @Override
    public String toString() {
        return "SingleListNode{" +
                "val=" + val +
                ", next=" + next +
                '}';
    }
}
