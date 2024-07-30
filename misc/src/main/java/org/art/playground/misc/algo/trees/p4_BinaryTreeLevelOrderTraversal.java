package org.art.playground.misc.algo.trees;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

import org.art.playground.misc.algo.utils.BinaryTreeNode;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Given the root of a binary tree, return the level order traversal of its nodes' values.
 * (i.e., from left to right, level by level).
 */
public class p4_BinaryTreeLevelOrderTraversal {

    public List<List<Integer>> levelOrder(BinaryTreeNode<Integer> root) {
        if (root == null) {
            return List.of();
        }

        List<List<Integer>> result = new ArrayList<>();

        Deque<BinaryTreeNode<Integer>> queue = new ArrayDeque<>();
        queue.addLast(root);

        while (!queue.isEmpty()) {
            List<Integer> levelVals = new ArrayList<>();
            int size = queue.size();

            for (int i = 0; i < size; i++) {
                BinaryTreeNode<Integer> node = queue.pollFirst();
                levelVals.add(node.val);
                if (node.left != null) {
                    queue.addLast(node.left);
                }
                if (node.right != null) {
                    queue.addLast(node.right);
                }
            }

            result.add(levelVals);
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
                new BinaryTreeNode<>(7, null, null)
            )
        );

        assertThat(levelOrder(root)).isEqualTo(List.of(
            List.of(3),
            List.of(9, 20),
            List.of(15, 7)
        ));
    }

    @Test
    void test1() {
        var root = new BinaryTreeNode<>(1, null, null);

        assertThat(levelOrder(root)).isEqualTo(List.of(
            List.of(1)
        ));
    }

    @Test
    void test2() {
        var root = new BinaryTreeNode<>(1,
            new BinaryTreeNode<>(2, null, null),
            null
        );

        assertThat(levelOrder(root)).isEqualTo(List.of(
            List.of(1),
            List.of(2)
        ));
    }

    @Test
    void test3() {
        assertThat(levelOrder(null)).isEqualTo(List.of());
    }
}
