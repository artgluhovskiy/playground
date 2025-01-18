package org.art.playground.misc.algo.trees;

import org.art.playground.misc.algo.utils.BinaryTreeNode;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Given the root of a binary tree, return the zigzag level order traversal of its nodes' values.
 * (i.e., from left to right, then right to left for the next level and alternate between).
 */
public class p7_BinaryTreeZigzagLevelOrderTraversal {

    public List<List<Integer>> zigzagLevelOrder(BinaryTreeNode<Integer> root) {
        if (root == null) {
            return List.of();
        }

        List<List<Integer>> result = new ArrayList<>();

        Deque<BinaryTreeNode<Integer>> queue = new LinkedList<>();
        queue.addFirst(root);

        boolean isRightDirection = true;

        while (!queue.isEmpty()) {

            List<Integer> values = new LinkedList<>();

            int size = queue.size();

            for (int i = 0; i < size; i++) {
                BinaryTreeNode<Integer> node = queue.pollFirst();

                if (isRightDirection) {
                    values.addLast(node.val);
                } else {
                    values.addFirst(node.val);
                }

                if (node.left != null) {
                    queue.addLast(node.left);
                }

                if (node.right != null) {
                    queue.addLast(node.right);
                }
            }

            result.add(values);

            isRightDirection = !isRightDirection;
        }

        return result;
    }

    @Test
    void test0() {
        var root = new BinaryTreeNode<>(3,
            new BinaryTreeNode<>(9,
                null,
                null
            ),
            new BinaryTreeNode<>(20,
                new BinaryTreeNode<>(15, null, null),
                new BinaryTreeNode<>(17, null, null)
            )
        );

        assertThat(zigzagLevelOrder(root)).isEqualTo(List.of(
            List.of(3),
            List.of(20, 9),
            List.of(15, 17)
        ));
    }
}
