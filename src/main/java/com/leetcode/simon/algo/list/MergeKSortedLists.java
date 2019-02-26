package com.leetcode.simon.algo.list;

import com.leetcode.simon.Solution;
import com.leetcode.simon.model.list.SingleListNode;
import com.leetcode.simon.util.ListTestHelper;

/**
 * https://leetcode.com/problems/merge-k-sorted-lists/
 *
 * Merge k sorted linked lists and return it as one sorted list.
 * Analyze and describe its complexity.
 */
public class MergeKSortedLists implements Solution {

  public SingleListNode mergeKLists(SingleListNode[] lists) {
    if (lists == null || lists.length == 0 ) return null;

    SingleListNode pHead = new SingleListNode(0);
    SingleListNode node, curr = pHead, pre = pHead;

    SingleListNode[] pointers = new SingleListNode[lists.length];
    System.arraycopy(lists, 0, pointers, 0, lists.length);

    int smallVal, index = 0;
    while (!isAllNull(pointers)) {
      smallVal = Integer.MAX_VALUE;
      for (int i = 0; i < pointers.length; i++) {
        node = pointers[i];
        if (node != null && node.val < smallVal) {
          smallVal = node.val;
          curr = node;
          index = i;
        }
      }


      pre.next = curr;
      pre = curr;

      pointers[index] = curr.next;
    }


    return pHead.next;
  }


  /**
   * whether all node is null
   */
  private boolean isAllNull(SingleListNode[] nodes) {
    for (SingleListNode node : nodes) {
      if (node != null) return false;
    }
    return true;
  }

  public void launchTestCase() {
    int[] list1 = {1, 4, 5};
    int[] list2 = {1, 3, 4};
    int[] list3 = {2, 6};

    SingleListNode[] lists = new SingleListNode[3];

    lists[0] = ListTestHelper.constructList(list1);
    lists[1] = ListTestHelper.constructList(list2);
    lists[2] = ListTestHelper.constructList(list3);

    for (SingleListNode node : lists) {
      ListTestHelper.printList(node);
    }


    SingleListNode newHead = mergeKLists(lists);
    ListTestHelper.printList(newHead);
  }
}
