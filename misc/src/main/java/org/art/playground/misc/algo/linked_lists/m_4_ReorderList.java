package org.art.playground.misc.algo.linked_lists;

import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * You are given the head of a singly linked-list. The list can be represented as:
 * L0 → L1 → … → Ln - 1 → Ln
 * Reorder the list to be on the following form:
 * L0 → Ln → L1 → Ln - 1 → L2 → Ln - 2 → …
 * You may not modify the values in the list's nodes. Only nodes themselves may be changed.
 */
public class m_4_ReorderList {

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

    public void reorderList(ListNode head) {
        if (head == null || head.next == null) {
            return;
        }

        // 1. Find the middle (slow ends at last node of the first half)
        ListNode slow = head, fast = head;
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        // 2. Reverse the second half
        ListNode secondHalf = slow.next;
        slow.next = null;
        ListNode prev = null;
        while (secondHalf != null) {
            ListNode next = secondHalf.next;
            secondHalf.next = prev;
            prev = secondHalf;
            secondHalf = next;
        }

        // 3. Merge two halves in-place: take alternately from each
        ListNode first = head;
        ListNode second = prev;
        while (second != null) {
            ListNode firstNext = first.next;
            ListNode secondNext = second.next;
            first.next = second;
            second.next = firstNext;
            first = firstNext;
            second = secondNext;
        }
    }

    @Test
    void test0() {
        ListNode root = new ListNode(1,
            new ListNode(2,
                new ListNode(3,
                    new ListNode(4))));

        ListNode expected = new ListNode(1,
            new ListNode(4,
                new ListNode(2,
                    new ListNode(3))));


        reorderList(root);

        assertThat(root).isEqualTo(expected);
    }

    @Test
    void test1() {
        ListNode root = new ListNode(1,
            new ListNode(2,
                new ListNode(3,
                    new ListNode(4,
                        new ListNode(5)))));

        ListNode expected = new ListNode(1,
            new ListNode(5,
                new ListNode(2,
                    new ListNode(4,
                        new ListNode(3)))));


        reorderList(root);

        assertThat(root).isEqualTo(expected);
    }
}
