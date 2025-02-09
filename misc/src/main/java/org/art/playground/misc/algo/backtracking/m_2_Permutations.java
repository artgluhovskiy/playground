package org.art.playground.misc.algo.backtracking;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Given an array nums of distinct integers,
 * return all the possible permutations.
 * You can return the answer in any order.
 */
public class m_2_Permutations {

    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        permute(nums, new boolean[nums.length], new LinkedList<>(), result);
        return result;
    }

    private void permute(int[] nums, boolean[] seen, List<Integer> singlePermutation, List<List<Integer>> result) {
        if (singlePermutation.size() == nums.length) {
            result.add(new ArrayList<>(singlePermutation));
            return;
        }

        for (int i = 0; i < nums.length; i++) {
            if (!seen[i]) {
                singlePermutation.add(nums[i]);

                seen[i] = true;
                permute(nums, seen, singlePermutation, result);
                seen[i] = false;

                singlePermutation.removeLast();
            }
        }
    }

    @Test
    void test0() {
        var input = new int[]{1, 2, 3};

        assertThat(permute(input))
            .containsExactlyInAnyOrderElementsOf(List.of(
                List.of(1, 2, 3),
                List.of(1, 3, 2),
                List.of(2, 1, 3),
                List.of(2, 3, 1),
                List.of(3, 1, 2),
                List.of(3, 2, 1)
            ));
    }

    @Test
    void test1() {
        var input = new int[]{0, 1};

        assertThat(permute(input))
            .containsExactlyInAnyOrderElementsOf(List.of(
                List.of(0, 1),
                List.of(1, 0)
            ));
    }

    @Test
    void test2() {
        var input = new int[]{1};

        assertThat(permute(input))
            .containsExactlyInAnyOrderElementsOf(List.of(
                List.of(1)
            ));
    }
}
