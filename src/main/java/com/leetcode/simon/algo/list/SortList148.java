package com.leetcode.simon.algo.list;

import com.leetcode.simon.Solution;
import com.leetcode.simon.model.ListNode;
import com.leetcode.simon.util.ListTestHelper;

/**
 * https://leetcode.com/problems/sort-list/description/
 */
public class SortList148 implements Solution {

    public ListNode sortList(ListNode head) {

        // quick sort
//        quickSort(head, null);

        // merge sort
        ListNode newHead = mergeSort(head);

        return newHead;
    }

    /*
     * ===============================================
     * quick sort
     * ===============================================
     */
    /**
     * all swap only involve the val, no list node chang
     */
    private void quickSort(ListNode head, ListNode end) {

        // part
        ListNode indNode = partition(head, end);

        // sort 2 sides
        if (indNode != null ) {
            quickSort(head, indNode);

            if (indNode.next != null)  {
                quickSort(indNode.next, end);
            }
        }
    }

    /**
     * return the pivot node
     * all swap only involve the val, no list node change
     */
    private ListNode partition(ListNode head, ListNode end) {
        if (head == null || head == end) return null;

        int pivot = head.val;
        ListNode p1 = head, p2 = head;

        int nexVal;
        while (p1.next != null && p1 != end) {
            nexVal = p1.next.val;
            if (nexVal < pivot) {
                // swap p1.next & p2.next
                // easy way: just swap the val
                p1.next.val = p2.next.val;
                p2.next.val = nexVal;

                p2 = p2.next;
            }
            p1 = p1.next;
        }

        // swap pivot
        head.val = p2.val;
        p2.val = pivot;

        return p2;
    }

    /*
     * ===============================================
     * merge sort
     * ===============================================
     */
    private ListNode mergeSort(ListNode head) {
        if (head == null || head.next == null)
            return head;

        // step 1. cut the list to two halves
        ListNode prev = null, slow = head, fast = head;

        while (fast != null && fast.next != null) {
            prev = slow;
            slow = slow.next;
            fast = fast.next.next;
        }

        prev.next = null;

        // step 2. sort each half
        ListNode l1 = sortList(head);
        ListNode l2 = sortList(slow);

        // step 3. merge l1 and l2
        return merge(l1, l2);
    }


    /**
     * merge 2 list, and return head
     */
    private ListNode merge(ListNode l1, ListNode l2) {
        if (l1 == null) return l2;
        if (l2 == null) return l1;

        ListNode preHead = new ListNode(0);
        ListNode p = preHead;
        while (l1 != null && l2 != null) {

            if (l1.val <= l2.val) {
                p.next = l1;
                l1 = l1.next;
            } else {
                p.next = l2;
                l2 = l2.next;
            }

            p = p.next;
        }

        if (l1 != null) p.next = l1;
        if (l2 != null) p.next = l2;


        return preHead.next;
    }

    public void launchTestCase() {
        System.out.print("raw: ");
        int[] values = {3, -2, -1, 4, 0};
        ListNode head = ListTestHelper.constructList(values);
        ListTestHelper.printList(head);

        // sort
        System.out.print("sorted: ");
        ListNode sorted = sortList(head);
        ListTestHelper.printList(sorted);
    }
}
