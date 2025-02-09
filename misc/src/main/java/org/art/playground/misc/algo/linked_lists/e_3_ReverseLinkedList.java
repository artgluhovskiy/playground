package org.art.playground.misc.algo.linked_lists;

import org.art.playground.misc.algo.utils.ListNode;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Given the head of a singly linked list, reverse the list, and return the reversed list.
 */
public class e_3_ReverseLinkedList {

    // Iterative
    public ListNode<Integer> reverseList1(ListNode<Integer> head) {
        ListNode<Integer> prev = null;

        while (head != null) {
            ListNode<Integer> nextNode = head.next;
            head.next = prev;
            prev = head;
            head = nextNode;
        }

        return prev;
    }

    // Recursive
    public ListNode<Integer> reverseList2(ListNode<Integer> head) {
        if (head == null) {
            return null;
        }

        ListNode<Integer> newHead = head;
        if (head.next != null) {
            newHead = reverseList2(head.next);
            head.next.next = head;
        }
        head.next = null;

        return newHead;
    }

    @Test
    void test0() {
        var input = ListNode.of(1, 2, 3, 4, 5);
        var expected = ListNode.of(5, 4, 3, 2, 1);

        assertThat(reverseList2(input)).isEqualTo(expected);
    }

    @Test
    void test1() {
        var input = ListNode.of(1, 2);
        var expected = ListNode.of(2, 1);

        assertThat(reverseList2(input)).isEqualTo(expected);
    }

    @Test
    void test3() {
        var input = ListNode.<Integer>of();
        var expected = ListNode.of();

        assertThat(reverseList2(input)).isEqualTo(expected);
    }
}
