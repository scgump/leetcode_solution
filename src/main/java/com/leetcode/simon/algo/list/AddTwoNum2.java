package com.leetcode.simon.algo.list;

import com.leetcode.simon.Solution;
import com.leetcode.simon.model.SingleListNode;
import com.leetcode.simon.util.ListTestHelper;

/**
 * https://leetcode.com/problems/add-two-numbers/description/
 */
public class AddTwoNum2 implements Solution {

    public SingleListNode addTwoNumbers(SingleListNode l1, SingleListNode l2) {
        SingleListNode preHead = new SingleListNode(0);
        SingleListNode pointer = preHead;
        int sum = 0;
        while (l1 != null || l2 != null || sum != 0) {
            if (l1 != null) {
                sum += l1.val;
                l1 = l1.next;
            }

            if (l2 != null) {
                sum += l2.val;
                l2 = l2.next;
            }

            pointer.next = new SingleListNode(sum % 10);
            pointer = pointer.next;

            sum /= 10;
        }

        return preHead.next;
    }

    public void launchTestCase() {

        // l1: 243
        System.out.print("left: ");
        int[] values = {2, 4, 3};
        SingleListNode l1 = ListTestHelper.constructList(values);
        ListTestHelper.printList(l1);

        // l2: 564
        System.out.print("right: ");
        values = new int[] {5, 6, 4};
        SingleListNode l2 = ListTestHelper.constructList(values);
        ListTestHelper.printList(l2);

        // add
        System.out.print("sum: ");
        SingleListNode sum = addTwoNumbers(l1, l2);
        ListTestHelper.printList(sum);
    }
}
