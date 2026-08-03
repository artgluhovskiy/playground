package org.art.playground.misc.algo.trees_and_graphs;

import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Given two integer arrays inorder and postorder where inorder is the inorder traversal of a binary tree
 * and postorder is the postorder traversal of the same tree, construct and return the binary tree.
 */
public class m_14_ConstructBinaryTreeFromInorderAndPostorderTraversal {

    public TreeNode buildTree(int[] inorder, int[] postorder) {
        if (inorder.length == 0 || postorder.length == 0) {
            return null;
        }

        int rootVal = postorder[postorder.length - 1];
        int pivotIdx = findPivotIdx(inorder, rootVal);

        TreeNode root = new TreeNode(rootVal);
        root.left = buildTree(subArray(inorder, 0, pivotIdx), subArray(postorder, 0, pivotIdx));
        root.right = buildTree(subArray(inorder, pivotIdx + 1, inorder.length), subArray(postorder, pivotIdx, postorder.length - 1));

        return root;
    }

    private int[] subArray(int[] arr, int from, int to) {
        int[] newArr = new int[to - from];
        for (int i = from; i < to; i++) {
            newArr[i - from] = arr[i];
        }
        return newArr;
    }

    private int findPivotIdx(int[] arr, int val) {
        for (int i = 0; i < arr.length; i++) {
            if (val == arr[i]) {
                return i;
            }
        }
        return -1;
    }

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

    @Test
    void test0() {
        var inorder = new int[]{9, 3, 15, 20, 7};
        var postorder = new int[]{9, 15, 7, 20, 3};

        var expected = new TreeNode(3,
            new TreeNode(9, null, null),
            new TreeNode(20,
                new TreeNode(15, null, null),
                new TreeNode(7, null, null))
        );

        assertThat(buildTree(inorder, postorder)).isEqualTo(expected);
    }

    @Test
    void test1() {
        var inorder = new int[]{-1};
        var postorder = new int[]{-1};

        var expected = new TreeNode(-1, null, null);

        assertThat(buildTree(inorder, postorder)).isEqualTo(expected);
    }

}
