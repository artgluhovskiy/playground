package org.art.playground.misc.algo.linked_lists;

import org.art.playground.misc.algo.utils.ListNode;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Given the head of a singly linked list, return true if it is a palindrome or false otherwise.
 */
public class e_5_PalindromeLinkedList {

    public boolean isPalindrome(ListNode<Integer> head) {
        ListNode<Integer> slow = head;
        ListNode<Integer> fast = head.next;

        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        ListNode<Integer> reversedHead = reversed(slow.next);

        while (reversedHead != null) {
            if (head.val != reversedHead.val) {
                return false;
            }
            head = head.next;
            reversedHead = reversedHead.next;
        }

        return true;
    }

    private ListNode<Integer> reversed(ListNode<Integer> head) {
        ListNode<Integer> prev = null;
        while (head != null) {
            ListNode<Integer> next = head.next;
            head.next = prev;
            prev = head;
            head = next;
        }
        return prev;
    }

    @Test
    void test0() {
        var input = ListNode.of(1, 2, 2, 1);

        assertThat(isPalindrome(input)).isTrue();
    }

    @Test
    void test1() {
        var input = ListNode.of(1, 2);

        assertThat(isPalindrome(input)).isFalse();
    }
}
