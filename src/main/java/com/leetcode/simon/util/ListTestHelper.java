package com.leetcode.simon.util;

import com.leetcode.simon.model.ListNode;

/**
 * Just used for testing
 */
public class ListTestHelper {
    private ListTestHelper(){}

    /**
     * construct a list from an int array,
     * return head node
     */
    public static ListNode constructList(int[] values) {
        ListNode preHead = new ListNode(0);
        ListNode pointer = preHead;

        for (int value : values) {
            pointer.next = new ListNode(value);
            pointer = pointer.next;
        }
        pointer.next = null;

        return preHead.next;
    }

    /**
     * print a linked list
     */
    public static void printList(ListNode head) {
        if (head == null)
            System.out.println("Empty List");

        StringBuilder sb = new StringBuilder();

        boolean isFirst = true;
        ListNode pointer = head;
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
