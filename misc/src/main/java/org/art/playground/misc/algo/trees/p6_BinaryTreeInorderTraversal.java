package org.art.playground.misc.algo.trees;

import org.art.playground.misc.algo.utils.BinaryTreeNode;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Given the root of a binary tree, return the inorder traversal of its nodes' values.
 */
public class p6_BinaryTreeInorderTraversal {

    public List<Integer> inorderTraversal(BinaryTreeNode<Integer> root) {
        List<Integer> result = new ArrayList<>();
        doInorderTraversal(root, result);
        return result;
    }

    private void doInorderTraversal(BinaryTreeNode<Integer> root, List<Integer> result) {
        if (root == null) {
            return;
        }

        doInorderTraversal(root.left, result);
        result.add(root.val);
        doInorderTraversal(root.right, result);
    }

    @Test
    void test0() {
        var root = new BinaryTreeNode<>(1,
            null,
            new BinaryTreeNode<>(2,
                new BinaryTreeNode<>(3, null, null),
                null
            )
        );

        assertThat(inorderTraversal(root)).isEqualTo(List.of(1, 3, 2));
    }

}
