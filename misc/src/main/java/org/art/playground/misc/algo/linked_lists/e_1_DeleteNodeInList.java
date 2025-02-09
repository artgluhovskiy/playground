package org.art.playground.misc.algo.linked_lists;

import org.art.playground.misc.algo.utils.ListNode;
import org.art.playground.misc.algo.utils.ListNodeUtils;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * There is a singly-linked list head and we want to delete a node in it.
 * You are given the node to be deleted node.
 * You will not be given access to the first node of head.
 * All the values of the linked list are unique, and it is guaranteed that
 * the given node is not the last node in the linked list.
 */
public class e_1_DeleteNodeInList {

    public void deleteNode(ListNode<Integer> node) {
        node.val = node.next.val;
        node.next = node.next.next;
    }

    @Test
    void test0() {
        var input = ListNode.of(4, 5, 1, 9);
        var expected = ListNode.of(4, 1, 9);
        var node = ListNodeUtils.getKthElement(2, input);

        deleteNode(node);

        assertThat(input).isEqualTo(expected);
    }

    @Test
    void test1() {
        var input = ListNode.of(4, 5, 1, 9);
        var expected = ListNode.of(4, 5, 9);
        var node = ListNodeUtils.getKthElement(3, input);

        deleteNode(node);

        assertThat(input).isEqualTo(expected);
    }
}
