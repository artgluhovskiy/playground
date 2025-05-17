package org.art.playground.misc.algo.linked_lists;

import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * You are given two non-empty linked lists representing two non-negative integers.
 * The digits are stored in reverse order, and each of their nodes contains a single digit.
 * Add the two numbers and return the sum as a linked list.
 * <p>
 * You may assume the two numbers do not contain any leading zero, except the number 0 itself.
 */
public class m_2_AddTwoNumbers {

    @ToString
    @EqualsAndHashCode
    public static class ListNode {
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

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(-1);
        ListNode cur = dummy;

        int carry = 0;

        while (l1 != null || l2 != null || carry > 0) {
            int v1 = l1 != null ? l1.val : 0;
            int v2 = l2 != null ? l2.val : 0;

            int sum = v1 + v2 + carry;

            int effectiveSum = sum % 10;
            carry = sum / 10;

            cur.next = new ListNode(effectiveSum);
            cur = cur.next;

            l1 = l1 != null ? l1.next : null;
            l2 = l2 != null ? l2.next : null;
        }

        return dummy.next;
    }

    @Test
    void test0() {
        ListNode l1 = new ListNode(2);

        ListNode l2 = new ListNode(5);

        ListNode expected = new ListNode(7);

        assertThat(addTwoNumbers(l1, l2)).isEqualTo(expected);
    }

    @Test
    void test1() {
        ListNode l1 = new ListNode(9);

        ListNode l2 = new ListNode(9);

        ListNode expected = new ListNode(8,
            new ListNode(1));

        assertThat(addTwoNumbers(l1, l2)).isEqualTo(expected);
    }

    @Test
    void test2() {
        ListNode l1 = new ListNode(2,
            new ListNode(4,
                new ListNode(3, null)));

        ListNode l2 = new ListNode(5,
            new ListNode(6,
                new ListNode(4, null)));

        ListNode expected = new ListNode(7,
            new ListNode(0,
                new ListNode(8, null)));

        assertThat(addTwoNumbers(l1, l2)).isEqualTo(expected);
    }
}
