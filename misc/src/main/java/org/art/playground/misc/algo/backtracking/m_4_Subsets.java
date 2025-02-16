package org.art.playground.misc.algo.backtracking;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Given an integer array nums of unique elements, return all possible subsets (the power set).
 * The solution set must not contain duplicate subsets. Return the solution in any order.
 */
public class m_4_Subsets {

    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        bfs(0, nums, new ArrayList<>(), res);
        return res;
    }

    // [1, 2, 3]
    // 1 Level:     [1]  []
    // 2 Level:     [1][2]  [1][]  [][2]  [][]
    // 3 Level:     [1][2][3]  [1][][3]  [][2][3]  [][][3]  [1][2][]  [1][][]  [][2][]  [][][]
    private void bfs(int i, int[] nums, List<Integer> subset, List<List<Integer>> res) {
        if (i >= nums.length) {
            res.add(new ArrayList<>(subset));
            return;
        }

        subset.addLast(nums[i]);
        bfs(i + 1, nums, subset, res);

        subset.removeLast();
        bfs(i + 1, nums, subset, res);
    }

    @Test
    void test1() {
        var input = new int[]{1, 2, 3};

        assertThat(subsets(input))
            .containsExactlyInAnyOrderElementsOf(List.of(
                List.of(),
                List.of(1),
                List.of(2),
                List.of(1, 2),
                List.of(3),
                List.of(1, 3),
                List.of(2, 3),
                List.of(1, 2, 3)
            ));
    }

    @Test
    void test2() {
        var input = new int[]{0};

        assertThat(subsets(input))
            .containsExactlyInAnyOrderElementsOf(List.of(
                List.of(),
                List.of(0)
            ));
    }
}
