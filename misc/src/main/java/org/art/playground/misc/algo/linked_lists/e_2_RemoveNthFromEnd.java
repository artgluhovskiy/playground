package org.art.playground.misc.algo.linked_lists;

import org.art.playground.misc.algo.utils.ListNode;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Given the head of a linked list, remove the nth node from the end of the list and return its head.
 */
public class e_2_RemoveNthFromEnd {

    public ListNode<Integer> removeNthFromEnd(ListNode<Integer> head, int n) {
        ListNode<Integer> dummy = ListNode.of(0);
        dummy.next = head;

        ListNode<Integer> slow = dummy;
        ListNode<Integer> fast = dummy;

        for (int i = 1; i <= n + 1; i++) {
            fast = fast.next;
        }

        while (fast != null) {
            slow = slow.next;
            fast = fast.next;
        }

        slow.next = slow.next.next;

        return dummy.next;
    }

    @Test
    void test0() {
        var input = ListNode.of(1, 2, 3, 4, 5);
        var expected = ListNode.of(1, 2, 3, 5);

        assertThat(removeNthFromEnd(input, 2)).isEqualTo(expected);
    }

    @Test
    void test1() {
        var input = ListNode.of(1);
        var expected = ListNode.of();

        assertThat(removeNthFromEnd(input, 1)).isEqualTo(expected);
    }

    @Test
    void test2() {
        var input = ListNode.of(1, 2);
        var expected = ListNode.of(1);

        assertThat(removeNthFromEnd(input, 1)).isEqualTo(expected);
    }

    @Test
    void test3() {
        var input = ListNode.of(1, 2, 3, 4, 5);
        var expected = ListNode.of(2, 3, 4, 5);

        assertThat(removeNthFromEnd(input, 5)).isEqualTo(expected);
    }
}
