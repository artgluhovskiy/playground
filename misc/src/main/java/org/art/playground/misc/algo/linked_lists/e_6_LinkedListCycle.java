package org.art.playground.misc.algo.linked_lists;

import org.art.playground.misc.algo.utils.ListNode;
import org.art.playground.misc.algo.utils.ListNodeUtils;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Given head, the head of a linked list, determine if the linked list has a cycle in it.
 */
public class e_6_LinkedListCycle {

    public boolean hasCycle(ListNode<Integer> head) {
        if (head == null) {
            return false;
        }

        ListNode<Integer> slow = head;
        ListNode<Integer> fast = head.next;

        while (fast != null && fast.next != null) {
            if (slow == fast) {
                return true;
            }
            slow = slow.next;
            fast = fast.next.next;
        }
        return false;
    }

    @Test
    void test0() {
        var input = ListNode.of(3, 2, 0, -4);
        var kNode = ListNodeUtils.getKthElement(2, input);
        var lastNode = ListNodeUtils.getKthElement(4, input);
        lastNode.next = kNode;

        assertThat(hasCycle(input)).isTrue();
    }

    @Test
    void test1() {
        var input = ListNode.of(1, 2);
        var kNode = ListNodeUtils.getKthElement(1, input);
        var lastNode = ListNodeUtils.getKthElement(2, input);
        lastNode.next = kNode;

        assertThat(hasCycle(input)).isTrue();
    }

    @Test
    void test2() {
        var input = ListNode.of(1);

        assertThat(hasCycle(input)).isFalse();
    }
}
