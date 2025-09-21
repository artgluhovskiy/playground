package org.art.playground.misc.algo.trees_and_graphs;

import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class m_4_InOrderSuccessorInBST {

    @ToString
    @EqualsAndHashCode
    class TreeNode {

        public int val;

        @EqualsAndHashCode.Exclude
        public TreeNode left;

        @EqualsAndHashCode.Exclude
        public TreeNode right;

        public TreeNode(int _val) {
            val = _val;
        }
    }

    public TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
        TreeNode successor = null;

        while (root != null) {
            if (p.val >= root.val) {
                root = root.right;
            } else {
                successor = root;
                root = root.left;
            }
        }

        return successor;
    }

    // Legacy method - kept for reference but not recommended
    public TreeNode inorderSuccessorLegacy(TreeNode root, TreeNode p) {
        TreeNode target = findNode(root, p);
        if (target == null) {
            return null;
        }

        // Case 1. Right subtree is defined
        if (target.right != null) {
            return findNextInOrder(target);
        }

        // Case 2. Right subtree is not defined
        // Trying to find the deepest ancestor
        TreeNode successor = null;
        TreeNode current = root;
        while (current != target) {
            if (p.val < current.val) {
                successor = current;
                current = current.left;
            } else {
                current = current.right;
            }
        }

        return successor;
    }

    private TreeNode findNode(TreeNode root, TreeNode p) {
        while (root != null) {
            if (root.equals(p)) {
                return root;
            }

            if (p.val < root.val) {
                root = root.left;
            } else {
                root = root.right;
            }
        }
        return null;
    }

    private TreeNode findNextInOrder(TreeNode root) {
        // Go to the right child first
        if (root.right != null) {
            root = root.right;
        } else {
            return null; // No right child means no successor
        }
        
        // Then go as far left as possible
        while (root.left != null) {
            root = root.left;
        }
        
        return root;
    }

    @Test
    void test0() {
        TreeNode root = new TreeNode(2);
        TreeNode n1 = new TreeNode(1);
        TreeNode n2 = new TreeNode(3);
        root.left = n1;
        root.right = n2;

        assertThat(inorderSuccessor(root, n1))
            .isEqualTo(root);
    }

    @Test
    void test1() {
        TreeNode root = new TreeNode(5);

        TreeNode n1 = new TreeNode(3);
        TreeNode n2 = new TreeNode(6);

        root.left = n1;
        root.right = n2;

        TreeNode n3 = new TreeNode(2);
        TreeNode n4 = new TreeNode(4);

        n1.left = n3;
        n1.right = n4;

        TreeNode n5 = new TreeNode(1);

        n3.left = n5;

        assertThat(inorderSuccessor(root, n4))
            .isEqualTo(root);
    }

    @Test
    void test2() {
        TreeNode root = new TreeNode(5);

        TreeNode n1 = new TreeNode(3);
        TreeNode n2 = new TreeNode(6);

        root.left = n1;
        root.right = n2;

        TreeNode n3 = new TreeNode(2);
        TreeNode n4 = new TreeNode(4);

        n1.left = n3;
        n1.right = n4;

        TreeNode n5 = new TreeNode(1);

        n3.left = n5;

        assertThat(inorderSuccessor(root, n2))
            .isEqualTo(null);
    }

    @Test
    void test3() {
        TreeNode root = new TreeNode(5);

        TreeNode n1 = new TreeNode(3);
        TreeNode n2 = new TreeNode(6);

        root.left = n1;
        root.right = n2;

        TreeNode n3 = new TreeNode(2);
        TreeNode n4 = new TreeNode(4);

        n1.left = n3;
        n1.right = n4;

        TreeNode n5 = new TreeNode(1);

        n3.left = n5;

        assertThat(inorderSuccessor(root, root))
            .isEqualTo(n2);
    }

}
