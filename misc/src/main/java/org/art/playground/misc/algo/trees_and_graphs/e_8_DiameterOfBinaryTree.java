package org.art.playground.misc.algo.trees_and_graphs;

import org.art.playground.misc.algo.utils.BinaryTreeNode;
import org.junit.jupiter.api.Test;

import java.util.concurrent.atomic.AtomicInteger;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Given the root of a binary tree, return the length of the diameter of the tree.
 * The diameter of a binary tree is the length of the longest path between any two nodes in a tree.
 * This path may or may not pass through the root.
 * The length of a path between two nodes is represented by the number of edges between them.
 * <p>
 * Algorithm:
 * - For each node, calculate the maximum depth of left and right subtrees
 * - The diameter through current node = leftDepth + rightDepth
 * - Update global maximum if this diameter is larger
 * - Return the maximum depth from current node (for parent calculations)
 * <p>
 * Time Complexity: O(n) where n is the number of nodes
 * Space Complexity: O(h) where h is the height of the tree (recursion stack)
 */
public class e_8_DiameterOfBinaryTree {

    public int diameterOfBinaryTree(BinaryTreeNode<Integer> root) {
        if (root == null) {
            return 0;
        }

        AtomicInteger maxDiameter = new AtomicInteger(0);
        calculateDiameterHelper(root, maxDiameter);
        return maxDiameter.get();
    }

    private int calculateDiameterHelper(BinaryTreeNode<Integer> node, AtomicInteger maxDiameter) {
        if (node == null) {
            return 0;
        }

        int leftDepth = calculateDiameterHelper(node.left, maxDiameter);
        int rightDepth = calculateDiameterHelper(node.right, maxDiameter);

        maxDiameter.updateAndGet(current -> Math.max(current, leftDepth + rightDepth));

        return Math.max(leftDepth, rightDepth) + 1;
    }

    @Test
    void test0() {
        var root = new BinaryTreeNode<>(1,
            new BinaryTreeNode<>(2,
                new BinaryTreeNode<>(4, null, null),
                new BinaryTreeNode<>(5, null, null)),
            new BinaryTreeNode<>(3, null, null)
        );

        assertThat(diameterOfBinaryTree(root)).isEqualTo(3);
    }

    @Test
    void test1() {
        var root = new BinaryTreeNode<>(1,
            new BinaryTreeNode<>(2, null, null),
            null);

        assertThat(diameterOfBinaryTree(root)).isEqualTo(1);
    }

    @Test
    void test2() {
        // Test with null root
        assertThat(diameterOfBinaryTree(null)).isEqualTo(0);
    }

    @Test
    void test3() {
        // Test with single node
        var root = new BinaryTreeNode<>(1, null, null);
        assertThat(diameterOfBinaryTree(root)).isEqualTo(0);
    }

    @Test
    void test4() {
        // Test with more complex tree
        var root = new BinaryTreeNode<>(1,
            new BinaryTreeNode<>(2,
                new BinaryTreeNode<>(4, null, null),
                new BinaryTreeNode<>(5, null, null)),
            new BinaryTreeNode<>(3,
                new BinaryTreeNode<>(6, null, null),
                new BinaryTreeNode<>(7, null, null))
        );

        assertThat(diameterOfBinaryTree(root)).isEqualTo(4);
    }

    @Test
    void test5() {
        // Test with skewed tree (left-heavy)
        var root = new BinaryTreeNode<>(1,
            new BinaryTreeNode<>(2,
                new BinaryTreeNode<>(3,
                    new BinaryTreeNode<>(4, null, null),
                    null),
                null),
            null);

        assertThat(diameterOfBinaryTree(root)).isEqualTo(3);
    }

    @Test
    void test6() {
        // Test with skewed tree (right-heavy)
        var root = new BinaryTreeNode<>(1,
            null,
            new BinaryTreeNode<>(2,
                null,
                new BinaryTreeNode<>(3,
                    null,
                    new BinaryTreeNode<>(4, null, null)))
        );

        assertThat(diameterOfBinaryTree(root)).isEqualTo(3);
    }
}
