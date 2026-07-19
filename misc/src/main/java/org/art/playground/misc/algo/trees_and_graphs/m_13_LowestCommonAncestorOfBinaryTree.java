package org.art.playground.misc.algo.trees_and_graphs;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Given a binary tree, find the lowest common ancestor (LCA) of two given nodes in the tree.
 * According to the definition of LCA on Wikipedia: “The lowest common ancestor is defined between
 * two nodes p and q as the lowest node in T that has both p and q as descendants
 * (where we allow a node to be a descendant of itself).”
 */
public class m_13_LowestCommonAncestorOfBinaryTree {

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        return helper(root, p, q);
    }

    private TreeNode helper(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) {
            return null;
        }

        if (root == p) {
            return root;
        }

        if (root == q) {
            return root;
        }

        TreeNode left = helper(root.left, p, q);
        TreeNode right = helper(root.right, p, q);

        if (left != null && right != null) {
            return root;
        } else if (left != null) {
            return left;
        } else if (right != null) {
            return right;
        } else {
            return null;
        }
    }

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    /**
     * 3
     * / \
     * 5   1
     * / \ / \
     * 6  2 0  8
     * / \
     * 7   4
     */
    private ExampleTree buildExampleTree() {
        TreeNode n7 = new TreeNode(7);
        TreeNode n4 = new TreeNode(4);
        TreeNode n2 = new TreeNode(2);
        n2.left = n7;
        n2.right = n4;

        TreeNode n6 = new TreeNode(6);
        TreeNode n5 = new TreeNode(5);
        n5.left = n6;
        n5.right = n2;

        TreeNode n0 = new TreeNode(0);
        TreeNode n8 = new TreeNode(8);
        TreeNode n1 = new TreeNode(1);
        n1.left = n0;
        n1.right = n8;

        TreeNode root = new TreeNode(3);
        root.left = n5;
        root.right = n1;

        return new ExampleTree(root, n0, n1, n2, n4, n5, n6, n7, n8);
    }

    private record ExampleTree(
        TreeNode root,
        TreeNode n0,
        TreeNode n1,
        TreeNode n2,
        TreeNode n4,
        TreeNode n5,
        TreeNode n6,
        TreeNode n7,
        TreeNode n8
    ) {
    }

    @Test
    void test0_nodesInDifferentSubtrees_lcaIsRoot() {
        ExampleTree t = buildExampleTree();
        assertThat(lowestCommonAncestor(t.root(), t.n5(), t.n1())).isSameAs(t.root());
    }

    @Test
    void test1_oneNodeIsAncestorOfTheOther_lcaIsAncestor() {
        ExampleTree t = buildExampleTree();
        assertThat(lowestCommonAncestor(t.root(), t.n5(), t.n4())).isSameAs(t.n5());
    }

    @Test
    void test2_bothNodesInSameSubtree_lcaIsDeepestSharedAncestor() {
        ExampleTree t = buildExampleTree();
        assertThat(lowestCommonAncestor(t.root(), t.n7(), t.n4())).isSameAs(t.n2());
    }

    @Test
    void test3_deepAndShallowInLeftSubtree() {
        ExampleTree t = buildExampleTree();
        assertThat(lowestCommonAncestor(t.root(), t.n6(), t.n4())).isSameAs(t.n5());
    }

    @Test
    void test4_bothNodesInRightSubtree() {
        ExampleTree t = buildExampleTree();
        assertThat(lowestCommonAncestor(t.root(), t.n0(), t.n8())).isSameAs(t.n1());
    }

    @Test
    void test5_nodeIsDescendantOfItself() {
        ExampleTree t = buildExampleTree();
        assertThat(lowestCommonAncestor(t.root(), t.n5(), t.n5())).isSameAs(t.n5());
    }

    @Test
    void test6_singleNodeTree() {
        TreeNode root = new TreeNode(1);
        assertThat(lowestCommonAncestor(root, root, root)).isSameAs(root);
    }

    @Test
    void test7_skewedTree() {
        TreeNode n4 = new TreeNode(4);
        TreeNode n3 = new TreeNode(3);
        TreeNode n2 = new TreeNode(2);
        TreeNode n1 = new TreeNode(1);
        n3.right = n4;
        n2.right = n3;
        n1.right = n2;

        assertThat(lowestCommonAncestor(n1, n3, n4)).isSameAs(n3);
    }
}
