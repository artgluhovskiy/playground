package org.art.playground.misc.algo.trees_and_graphs;

import java.util.Stack;

import org.art.playground.misc.algo.utils.BinaryTreeNode;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Given the root of a binary tree, determine if it is a valid binary search tree (BST).
 * A valid BST is defined as follows:
 * The left subtree of a node contains only nodes with keys less than the node's key.
 * The right subtree of a node contains only nodes with keys greater than the node's key.
 * Both the left and right subtrees must also be binary search trees.
 */
public class e_2_ValidBinarySearchTree {

    public boolean isValidBST(BinaryTreeNode<Integer> root) {
        return isValidBST(root, null, null);
    }

    private boolean isValidBST(BinaryTreeNode<Integer> root, Integer min, Integer max) {
        if (root == null) {
            return true;
        }

        if (min != null && root.val <= min) {
            return false;
        }

        if (max != null && root.val >= max) {
            return false;
        }

        return isValidBST(root.left, min, root.val) && isValidBST(root.right, root.val, max);
    }

    public boolean isValidBSTV2(BinaryTreeNode<Integer> root) {
        Stack<BinaryTreeNode<Integer>> stack = new Stack<>();
        Integer leftMinValue = Integer.MIN_VALUE;

        while (!stack.isEmpty() || root != null) {
            while (root != null) {
                stack.push(root);
                root = root.left;
            }

            BinaryTreeNode<Integer> current = stack.pop();
            if (current.val <= leftMinValue) {
                return false;
            }
            leftMinValue = current.val;
            root = current.right;
        }

        return true;
    }

    @Test
    void test0() {
        var root = new BinaryTreeNode<>(2,
            new BinaryTreeNode<>(1, null, null),
            new BinaryTreeNode<>(3, null, null)
        );

        assertThat(isValidBST(root)).isTrue();
    }

    @Test
    void test1() {
        var root = new BinaryTreeNode<>(5,
            new BinaryTreeNode<>(1, null, null),
            new BinaryTreeNode<>(4,
                new BinaryTreeNode<>(3, null, null),
                new BinaryTreeNode<>(6, null, null)
            )
        );

        assertThat(isValidBST(root)).isFalse();
    }

    @Test
    void test2() {
        var root = new BinaryTreeNode<>(5,
            new BinaryTreeNode<>(4, null, null),
            new BinaryTreeNode<>(6,
                new BinaryTreeNode<>(3, null, null),
                new BinaryTreeNode<>(7, null, null)
            )
        );

        assertThat(isValidBST(root)).isFalse();
    }

    @Test
    void test3() {
        var root = new BinaryTreeNode<>(5,
            new BinaryTreeNode<>(14,
                new BinaryTreeNode<>(1, null, null),
                null),
            null
        );

        assertThat(isValidBST(root)).isFalse();
    }

    @Test
    void test4() {
        var root = new BinaryTreeNode<>(5,
            new BinaryTreeNode<>(4, null, null),
            new BinaryTreeNode<>(8,
                new BinaryTreeNode<>(6, null, null),
                new BinaryTreeNode<>(9, null, null)
            )
        );

        assertThat(isValidBSTV2(root)).isTrue();
    }
}
