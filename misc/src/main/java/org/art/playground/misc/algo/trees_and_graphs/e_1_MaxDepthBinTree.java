package org.art.playground.misc.algo.trees_and_graphs;

import org.art.playground.misc.algo.utils.BinaryTreeNode;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Given the root of a binary tree, return its maximum depth.
 */
public class e_1_MaxDepthBinTree {

    public int maxDepth(BinaryTreeNode<Integer> root) {
        if (root == null) {
            return 0;
        }

        int maxLeftDepth = maxDepth(root.left);
        int maxRightDepth = maxDepth(root.right);

        return Math.max(maxLeftDepth, maxRightDepth) + 1;
    }

    @Test
    void test0() {
        var root = new BinaryTreeNode<>(3,
            new BinaryTreeNode<>(9, null, null),
            new BinaryTreeNode<>(20,
                new BinaryTreeNode<>(15, null, null), new BinaryTreeNode<>(7, null, null)
            )
        );

        assertThat(maxDepth(root)).isEqualTo(3);
    }

    @Test
    void test1() {
        var root = new BinaryTreeNode<>(1,
            null,
            new BinaryTreeNode<>(2, null, null)
        );

        assertThat(maxDepth(root)).isEqualTo(2);
    }
}
