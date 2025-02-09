package org.art.playground.misc.algo.trees;

import org.art.playground.misc.algo.utils.BinaryTreeNode;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Given an integer array nums where the elements are sorted in ascending order,
 * convert it to a height-balanced binary search tree.
 */
public class e_5_ConvertSortedArrayToBinarySearchTree {

    public BinaryTreeNode<Integer> sortedArrayToBST(int[] nums) {
        return buildTree(nums, 0, nums.length - 1);
    }

    private BinaryTreeNode<Integer> buildTree(int[] nums, int from, int to) {
        if (from > to) {
            return null;
        }

        int mid = (to + from) / 2;
        int midVal = nums[mid];

        return new BinaryTreeNode<>(
            midVal,
            buildTree(nums, from, mid - 1),
            buildTree(nums, mid + 1, to)
        );
    }

    @Test
    void test0() {
        var input = new int[]{-10, -3, 0, 5, 9};

        var expected = new BinaryTreeNode<>(0,
            new BinaryTreeNode<>(-10,
                null,
                new BinaryTreeNode<>(-3, null, null)
            ),
            new BinaryTreeNode<>(5,
                null,
                new BinaryTreeNode<>(9, null, null)
            )
        );

        assertThat(sortedArrayToBST(input)).isEqualTo(expected);
    }

    @Test
    void test1() {
        var input = new int[]{1, 3};

        var expected = new BinaryTreeNode<>(1,
            null,
            new BinaryTreeNode<>(3, null, null)
        );

        assertThat(sortedArrayToBST(input)).isEqualTo(expected);
    }

    @Test
    void test2() {
        var input = new int[]{1};

        var expected = new BinaryTreeNode<>(1, null, null);

        assertThat(sortedArrayToBST(input)).isEqualTo(expected);
    }
}
