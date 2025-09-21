package org.art.playground.misc.algo.trees_and_graphs;

import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Given two integer arrays preorder and inorder where preorder is the preorder traversal of a binary tree
 * and inorder is the inorder traversal of the same tree, construct and return the binary tree.
 */
public class m_1_ConstructBinaryTreeFromPreorderAndInorderTraversal {

    @ToString
    @EqualsAndHashCode
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        if (preorder.length == 0 || inorder.length == 0) {
            return null;
        }

        TreeNode root = new TreeNode(preorder[0]);

        // Define the mid point: right part - right sub tree, left part - left sub tree
        int mid = findIdx(root.val, inorder);

        root.left = buildTree(subArray(preorder, 1, mid + 1), subArray(inorder, 0, mid));
        root.right = buildTree(subArray(preorder, mid + 1, preorder.length), subArray(inorder, mid + 1, inorder.length));
        return root;
    }

    private int findIdx(Integer val, int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == val) {
                return i;
            }
        }
        return -1;
    }

    private int[] subArray(int[] arr, int from, int to) {
        int length = to - from;
        int[] newArr = new int[length];
        System.arraycopy(arr, from, newArr, 0, length);
        return newArr;
    }

    @Test
    void test0() {
        var preorder = new int[]{3, 9, 20, 15, 7};
        var inorder = new int[]{9, 3, 15, 20, 7};

        var expected = new TreeNode(3,
            new TreeNode(9, null, null),
            new TreeNode(20,
                new TreeNode(15, null, null),
                new TreeNode(7, null, null))
        );

        assertThat(buildTree(preorder, inorder)).isEqualTo(expected);
    }

    @Test
    void test1() {
        var preorder = new int[]{-1};
        var inorder = new int[]{-1};

        var expected = new TreeNode(-1, null, null);

        assertThat(buildTree(preorder, inorder)).isEqualTo(expected);
    }
}
