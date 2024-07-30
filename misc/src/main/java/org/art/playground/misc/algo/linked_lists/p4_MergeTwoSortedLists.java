package org.art.playground.misc.algo.linked_lists;

import org.art.playground.misc.algo.utils.ListNode;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * You are given the heads of two sorted linked lists list1 and list2.
 * Merge the two lists into one sorted list. The list should be made by splicing
 * together the nodes of the first two lists.
 * Return the head of the merged linked list.
 */
public class p4_MergeTwoSortedLists {

    public ListNode<Integer> mergeTwoLists(ListNode<Integer> list1, ListNode<Integer> list2) {
        ListNode<Integer> dummy = new ListNode<>(0);
        ListNode<Integer> current = dummy;

        while (list1 != null && list2 != null) {
            if (list1.val < list2.val) {
                current.next = list1;
                list1 = list1.next;
            } else {
                current.next = list2;
                list2 = list2.next;
            }

            current = current.next;
        }

        if (list1 == null) {
            current.next = list2;
        } else {
            current.next = list1;
        }

        return dummy.next;
    }

    @Test
    void test0() {
        var l1 = ListNode.of(1, 2, 4);
        var l2 = ListNode.of(1, 3, 4);

        var expected = ListNode.of(1, 1, 2, 3, 4, 4);

        assertThat(mergeTwoLists(l1, l2)).isEqualTo(expected);
    }

    @Test
    void test1() {
        var l1 = ListNode.<Integer>of();
        var l2 = ListNode.<Integer>of();

        var expected = ListNode.<Integer>of();

        assertThat(mergeTwoLists(l1, l2)).isEqualTo(expected);
    }

    @Test
    void test2() {
        var l1 = ListNode.<Integer>of();
        var l2 = ListNode.of(0);

        var expected = ListNode.of(0);

        assertThat(mergeTwoLists(l1, l2)).isEqualTo(expected);
    }
}
