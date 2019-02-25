package com.leetcode.simon.util;

import com.leetcode.simon.model.list.SingleListNode;

/**
 * Just used for testing
 */
public class ListTestHelper {
    private ListTestHelper(){}

    /**
     * construct a list from an int array,
     * return head node
     */
    public static SingleListNode constructList(int[] values) {
        SingleListNode preHead = new SingleListNode(0);
        SingleListNode pointer = preHead;

        for (int value : values) {
            pointer.next = new SingleListNode(value);
            pointer = pointer.next;
        }
        pointer.next = null;

        return preHead.next;
    }

    /**
     * print a linked list
     */
    public static void printList(SingleListNode head) {
        if (head == null)
            System.out.println("Empty List");

        StringBuilder sb = new StringBuilder();

        boolean isFirst = true;
        SingleListNode pointer = head;
        while (pointer != null) {
            if (isFirst) {
                sb.append(pointer.val);
                isFirst = false;
            } else {
                sb.append(" -> ");
                sb.append(pointer.val);
            }
            pointer = pointer.next;
        }

        System.out.println(sb.toString());
    }
}
