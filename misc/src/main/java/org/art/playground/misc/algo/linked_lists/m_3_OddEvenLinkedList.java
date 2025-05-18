package org.art.playground.misc.algo.linked_lists;

import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Given the head of a singly linked list, group all the nodes with odd indices together followed by the nodes with even indices,
 * and return the reordered list.
 * <p>
 * The first node is considered odd, and the second node is even, and so on.
 * <p>
 * Note that the relative order inside both the even and odd groups should remain as it was in the input.
 * <p>
 * You must solve the problem in O(1) extra space complexity and O(n) time complexity.
 */
public class m_3_OddEvenLinkedList {

    @ToString
    @EqualsAndHashCode
    static class ListNode {
        int val;
        ListNode next;

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

    public ListNode oddEvenList(ListNode head) {
        if (head == null) {
            return head;
        }

        ListNode odd = head;
        ListNode even = head.next;
        ListNode evenHead = even;

        while (even != null && even.next != null) {
            odd.next = even.next;
            odd = odd.next;
            even.next = odd.next;
            even = even.next;
        }

        odd.next = evenHead;

        return head;
    }

    @Test
    void test2() {
        ListNode root = new ListNode(1,
            new ListNode(2,
                new ListNode(3,
                    new ListNode(4,
                        new ListNode(5)))));

        ListNode expected = new ListNode(1,
            new ListNode(3,
                new ListNode(5,
                    new ListNode(2,
                        new ListNode(4)))));


        assertThat(oddEvenList(root)).isEqualTo(expected);
    }
}
