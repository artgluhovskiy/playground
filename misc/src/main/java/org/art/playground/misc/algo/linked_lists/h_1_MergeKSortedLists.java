package org.art.playground.misc.algo.linked_lists;

/**
 * You are given an array of k linked-lists lists, each linked-list is sorted in ascending order.
 * Merge all the linked-lists into one sorted linked-list and return it.
 */
public class h_1_MergeKSortedLists {

    public ListNode mergeKLists(ListNode[] lists) {
        if (lists.length == 1) {
            return lists[0];
        } else if (lists.length == 0) {
            return null;
        }
        return mergeKLists0(lists, 0, lists.length - 1);
    }

    private ListNode mergeKLists0(ListNode[] lists, int start, int end) {
        if (start == end) {
            return lists[start];
        } else if (end - start == 1) {
            return merge2Lists(lists[start], lists[end]);
        } else {
            int mid = (end + start) / 2;
            ListNode l1 = mergeKLists0(lists, start, mid);
            ListNode l2 = mergeKLists0(lists, mid + 1, end);
            return merge2Lists(l1, l2);
        }
    }

    private ListNode merge2Lists(ListNode l1, ListNode l2) {
        if (l1 == null) {
            return l2;
        }
        if (l2 == null) {
            return l1;
        }
        ListNode dummy = new ListNode(0);
        ListNode current;
        if (l1.val <= l2.val) {
            dummy.next = l1;
            current = l1;
            l1 = l1.next;
        } else {
            dummy.next = l2;
            current = l2;
            l2 = l2.next;
        }
        while (l1 != null && l2 != null) {
            if (l1.val <= l2.val) {
                current.next = l1;
                l1 = l1.next;
            } else {
                current.next = l2;
                l2 = l2.next;
            }
            current = current.next;
        }
        if (l1 == null) {
            current.next = l2;
        } else {
            current.next = l1;
        }
        return dummy.next;
    }

    public class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }
}
