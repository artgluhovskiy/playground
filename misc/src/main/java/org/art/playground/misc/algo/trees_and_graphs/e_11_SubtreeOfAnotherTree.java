package org.art.playground.misc.algo.trees_and_graphs;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Given the roots of two binary trees root and subRoot, return true if there is a subtree of root with
 * the same structure and node values of subRoot and false otherwise.
 * <p>
 * A subtree of a binary tree tree is a tree that consists of a node in tree and all of this node's descendants.
 * The tree tree could also be considered as a subtree of itself.
 */
public class e_11_SubtreeOfAnotherTree {

    public boolean isSubtree(TreeNode root, TreeNode subRoot) {
        if (root == null) {
            return false;
        }
        if (compare(root, subRoot)) {
            return true;
        }
        return isSubtree(root.left, subRoot) || isSubtree(root.right, subRoot);
    }

    private boolean compare(TreeNode commonRoot, TreeNode subRoot) {
        if (commonRoot == null && subRoot == null) {
            return true;
        }

        if (commonRoot == null || subRoot == null || (commonRoot.val != subRoot.val)) {
            return false;
        }

        return compare(commonRoot.left, subRoot.left) && compare(commonRoot.right, subRoot.right);
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
     * root:     3
     *          / \
     *         4   5
     *        / \
     *       1   2
     */
    private TreeNode buildLeetCodeExampleRoot() {
        TreeNode n1 = new TreeNode(1);
        TreeNode n2 = new TreeNode(2);
        TreeNode n4 = new TreeNode(4);
        n4.left = n1;
        n4.right = n2;
        TreeNode n5 = new TreeNode(5);
        TreeNode root = new TreeNode(3);
        root.left = n4;
        root.right = n5;
        return root;
    }

    private TreeNode buildLeetCodeExampleSubRoot() {
        TreeNode n1 = new TreeNode(1);
        TreeNode n2 = new TreeNode(2);
        TreeNode sub = new TreeNode(4);
        sub.left = n1;
        sub.right = n2;
        return sub;
    }

    @Test
    void test0_leetcodeExample_subtreePresent() {
        assertThat(isSubtree(buildLeetCodeExampleRoot(), buildLeetCodeExampleSubRoot())).isTrue();
    }

    @Test
    void test1_leetcodeExample_similarValuesButNotSubtree() {
        TreeNode root = buildLeetCodeExampleRoot();
        TreeNode n0 = new TreeNode(0);
        root.left.left.right = n0;

        assertThat(isSubtree(root, buildLeetCodeExampleSubRoot())).isFalse();
    }

    @Test
    void test2_rootEqualsSubRoot() {
        TreeNode sub = buildLeetCodeExampleSubRoot();
        assertThat(isSubtree(sub, sub)).isTrue();
    }

    @Test
    void test3_singleNodeMatch() {
        TreeNode root = new TreeNode(7);
        TreeNode sub = new TreeNode(7);
        assertThat(isSubtree(root, sub)).isTrue();
    }

    @Test
    void test4_singleNodeMismatch() {
        TreeNode root = new TreeNode(7);
        TreeNode sub = new TreeNode(8);
        assertThat(isSubtree(root, sub)).isFalse();
    }

    @Test
    void test5_sameValueAtMultipleNodes_mustMatchStructure() {
        TreeNode inner = new TreeNode(1);
        TreeNode left = new TreeNode(1);
        left.left = inner;
        TreeNode root = new TreeNode(1);
        root.left = left;
        root.right = new TreeNode(1);

        TreeNode sub = new TreeNode(1);
        sub.left = new TreeNode(1);

        assertThat(isSubtree(root, sub)).isTrue();
    }

    @Test
    void test6_subRootNotInRoot() {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        TreeNode sub = new TreeNode(3);
        assertThat(isSubtree(root, sub)).isFalse();
    }

    @Test
    void test7_partialStructuralMatchIsNotEnough() {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);

        TreeNode sub = new TreeNode(2);
        sub.left = new TreeNode(4);

        assertThat(isSubtree(root, sub)).isFalse();
    }
}
