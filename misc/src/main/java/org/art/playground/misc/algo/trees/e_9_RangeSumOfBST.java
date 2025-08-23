package org.art.playground.misc.algo.trees;

import org.art.playground.misc.algo.utils.BinaryTreeNode;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Given the root node of a binary search tree and two integers low and high,
 * return the sum of values of all nodes with a value in the inclusive range [low, high].
 */
public class e_9_RangeSumOfBST {

    public int rangeSumBST(BinaryTreeNode<Integer> root, int low, int high) {
        if (root == null) {
            return 0;
        }

        if (root.val < low) {
            return rangeSumBST(root.right, low, high);
        } else if (root.val > high) {
            return rangeSumBST(root.left, low, high);
        } else {
            return root.val 
                + rangeSumBST(root.right, low, high)
                + rangeSumBST(root.left, low, high);
        }
    }

    @Test
    void test0() {
        var root = new BinaryTreeNode<>(10,
            new BinaryTreeNode<>(5,
                new BinaryTreeNode<>(3, null, null),
                new BinaryTreeNode<>(7, null, null)
            ),
            new BinaryTreeNode<>(15, null,
                new BinaryTreeNode<>(18, null, null)
            )
        );

        assertThat(rangeSumBST(root, 7, 15)).isEqualTo(32);
    }

    @Test
    void test1() {
        var root = new BinaryTreeNode<>(10,
            new BinaryTreeNode<>(5,
                new BinaryTreeNode<>(3,
                    new BinaryTreeNode<>(1, null, null),
                    null
                ),
                new BinaryTreeNode<>(7,
                    new BinaryTreeNode<>(6, null, null),
                    null
                )
            ),
            new BinaryTreeNode<>(15,
                new BinaryTreeNode<>(13, null, null),
                new BinaryTreeNode<>(18, null, null)
            )
        );

        assertThat(rangeSumBST(root, 6, 10)).isEqualTo(23);
    }
}
