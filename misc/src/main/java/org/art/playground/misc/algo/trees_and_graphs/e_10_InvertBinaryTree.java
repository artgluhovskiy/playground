package org.art.playground.misc.algo.trees_and_graphs;

import org.art.playground.misc.algo.utils.BinaryTreeNode;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Given the root of a binary tree, invert the tree, and return its root.
 */
public class e_10_InvertBinaryTree {

    public BinaryTreeNode<Integer> invertTree(BinaryTreeNode<Integer> root) {
        return buildInvertedTree(root);
    }

    private BinaryTreeNode<Integer> buildInvertedTree(BinaryTreeNode<Integer> root) {
        if (root == null) {
            return null;
        }

        BinaryTreeNode<Integer> newRoot = new BinaryTreeNode<>(root.val, null, null);
        newRoot.left = buildInvertedTree(root.right);
        newRoot.right = buildInvertedTree(root.left);

        return newRoot;
    }

    @Test
    void test0() {
        var root = new BinaryTreeNode<>(4,
            new BinaryTreeNode<>(2,
                new BinaryTreeNode<>(1, null, null),
                new BinaryTreeNode<>(3, null, null)),
            new BinaryTreeNode<>(7,
                new BinaryTreeNode<>(6, null, null),
                new BinaryTreeNode<>(9, null, null))
        );

        var expected = new BinaryTreeNode<>(4,
            new BinaryTreeNode<>(7,
                new BinaryTreeNode<>(9, null, null),
                new BinaryTreeNode<>(6, null, null)),
            new BinaryTreeNode<>(2,
                new BinaryTreeNode<>(3, null, null),
                new BinaryTreeNode<>(1, null, null))
        );

        assertThat(invertTree(root)).isEqualTo(expected);
    }

    @Test
    void test1() {
        var root = new BinaryTreeNode<>(2,
            new BinaryTreeNode<>(1, null, null),
            new BinaryTreeNode<>(3, null, null)
        );

        var expected = new BinaryTreeNode<>(2,
            new BinaryTreeNode<>(3, null, null),
            new BinaryTreeNode<>(1, null, null)
        );

        assertThat(invertTree(root)).isEqualTo(expected);
    }
}
