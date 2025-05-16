package org.art.playground.misc.algo.trees;

import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.junit.jupiter.api.Test;

import java.util.Deque;
import java.util.LinkedList;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Given the root of a binary search tree, and an integer k,
 * return the kth smallest value (1-indexed) of all the values of the nodes in the tree.
 */
public class m_3_KthSmallestElementInBST {

    @ToString
    @EqualsAndHashCode
    class TreeNode {

        public int val;
        public TreeNode left;
        public TreeNode right;

        public TreeNode(int _val) {
            val = _val;
        }
    }

    public int kthSmallest(TreeNode root, int k) {
        int visited = 0;
        Deque<TreeNode> stack = new LinkedList<>();

        TreeNode cur = root;

        while (!stack.isEmpty() || cur != null) {
            if (cur != null) {
                stack.push(cur);
                cur = cur.left;
            } else {
                TreeNode poped = stack.pop();
                visited++;
                if (visited == k) {
                    return poped.val;
                }
                cur = poped.right;
            }
        }

        // Is not reachable
        return -1;
    }

    @Test
    void test0() {
        TreeNode root = new TreeNode(3);
        TreeNode n1 = new TreeNode(1);
        TreeNode n2 = new TreeNode(4);
        root.left = n1;
        root.right = n2;
        TreeNode n3 = new TreeNode(2);
        n1.right = n3;

        assertThat(kthSmallest(root, 1)).isEqualTo(1);
    }
}
