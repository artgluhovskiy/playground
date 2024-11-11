package org.art.playground.misc.algo.linked_lists;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;

/**
 * Given the heads of two singly linked-lists headA and headB,
 * return the node at which the two lists intersect.
 * If the two linked lists have no intersection at all, return null.
 */
public class p21_IntersectionOfTwoLinkedLists {

    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        int aLength = 1;
        int bLength = 1;

        ListNode tailA = headA;
        ListNode tailB = headB;

        while (tailA.next != null) {
            tailA = tailA.next;
            aLength++;
        }

        while (tailB.next != null) {
            tailB = tailB.next;
            bLength++;
        }

        if (tailA != tailB) {
            return null;
        }

        int delta = Math.abs(aLength - bLength);

        ListNode shorter;
        ListNode longer;
        if (aLength > bLength) {
            longer = headA;
            shorter = headB;
        } else {
            longer = headB;
            shorter = headA;
        }

        for (int i = 0; i < delta; i++) {
            longer = longer.next;
        }

        while (longer != shorter) {
            longer = longer.next;
            shorter = shorter.next;
        }

        return longer;
    }

    static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
            next = null;
        }
    }

    @Test
    void test0() {
        var node1 = new ListNode(1);
        var node2 = new ListNode(9);
        var node3 = new ListNode(1);

        var node4 = new ListNode(4);

        var node5 = new ListNode(2);

        var node6 = new ListNode(4);

        node1.next = node2;
        node2.next = node3;
        node3.next = node5;
        node5.next = node6;
        node4.next = node5;

        assertThat(getIntersectionNode(node1, node4)).isSameAs(node5);

    }

}
