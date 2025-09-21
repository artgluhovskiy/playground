package org.art.playground.misc.algo.trees_and_graphs;

import org.art.playground.misc.algo.utils.BinaryTreeNode;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Given the root of a binary tree, check whether it is a mirror of itself (i.e., symmetric around its center).
 */
public class e_3_SymmetricTree {

    public boolean isSymmetric(BinaryTreeNode root) {
        if (root == null) {
            return true;
        }
        return isSymmetric(root.left, root.right);
    }

    private boolean isSymmetric(BinaryTreeNode leftRoot, BinaryTreeNode rightRoot) {
        if (leftRoot == null && rightRoot == null) {
            return true;
        }

        if (leftRoot != null && rightRoot == null) {
            return false;
        }

        if (rightRoot != null && leftRoot == null) {
            return false;
        }

        if (rightRoot.val != leftRoot.val) {
            return false;
        }

        return isSymmetric(leftRoot.left, rightRoot.right) && isSymmetric(leftRoot.right, rightRoot.left);
    }

    @Test
    void test0() {
        var root = new BinaryTreeNode<>(1,
            new BinaryTreeNode<>(2,
                new BinaryTreeNode<>(3, null, null),
                new BinaryTreeNode<>(4, null, null)
            ),
            new BinaryTreeNode<>(2,
                new BinaryTreeNode<>(4, null, null),
                new BinaryTreeNode<>(3, null, null)
            )
        );

        assertThat(isSymmetric(root)).isTrue();
    }

    @Test
    void test1() {
        var root = new BinaryTreeNode<>(1,
            new BinaryTreeNode<>(2,
                null,
                new BinaryTreeNode<>(3, null, null)
            ),
            new BinaryTreeNode<>(2,
                null,
                new BinaryTreeNode<>(3, null, null)
            )
        );

        assertThat(isSymmetric(root)).isFalse();
    }
}
