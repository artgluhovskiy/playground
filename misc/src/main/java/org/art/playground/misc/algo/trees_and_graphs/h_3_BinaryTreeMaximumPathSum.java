package org.art.playground.misc.algo.trees_and_graphs;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class h_3_BinaryTreeMaximumPathSum {

    public int maxPathSum(TreeNode root) {
        int[] maxSeen = new int[1];
        maxSeen[0] = Integer.MIN_VALUE;

        maxPathSumHelper(root, maxSeen);

        return maxSeen[0];
    }

    private int maxPathSumHelper(TreeNode root, int[] maxSeen) {
        if (root == null) {
            return 0;
        }

        int currentVal = root.val;
        int leftGain = Math.max(0, maxPathSumHelper(root.left, maxSeen));
        int rightGain = Math.max(0, maxPathSumHelper(root.right, maxSeen));

        if (currentVal + leftGain + rightGain > maxSeen[0]) {
            maxSeen[0] = currentVal + leftGain + rightGain;
        }

        return currentVal + Math.max(leftGain, rightGain);
    }

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    /**
     * -10
     * /  \
     * 9    20
     * /  \
     * 15   7
     */
    @Test
    void test0_classicPathThroughInternalNode() {
        TreeNode root = new TreeNode(-10);
        root.left = new TreeNode(9);
        root.right = new TreeNode(20);
        root.right.left = new TreeNode(15);
        root.right.right = new TreeNode(7);

        assertThat(maxPathSum(root)).isEqualTo(42);
    }

    @Test
    void test1_singleNodeAllNegative() {
        assertThat(maxPathSum(new TreeNode(-3))).isEqualTo(-3);
    }

    /**
     * 1
     * / \
     * -2  -3
     */
    @Test
    void test2_bothChildrenNegative_bestIsRootAlone() {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(-2);
        root.right = new TreeNode(-3);

        assertThat(maxPathSum(root)).isEqualTo(1);
    }

    /**
     * 10
     * /  \
     * -20  -20
     */
    @Test
    void test3_largeRootBothChildrenVeryNegative() {
        TreeNode root = new TreeNode(10);
        root.left = new TreeNode(-20);
        root.right = new TreeNode(-20);

        assertThat(maxPathSum(root)).isEqualTo(10);
    }

    /**
     * 2
     * /
     * -1
     */
    @Test
    void test4_oneNegativeChild_skipChild() {
        TreeNode root = new TreeNode(2);
        root.left = new TreeNode(-1);

        assertThat(maxPathSum(root)).isEqualTo(2);
    }

    /**
     * -2
     * /
     * -1
     */
    @Test
    void test5_allNegativeChain() {
        TreeNode root = new TreeNode(-2);
        root.left = new TreeNode(-1);

        assertThat(maxPathSum(root)).isEqualTo(-1);
    }
}
