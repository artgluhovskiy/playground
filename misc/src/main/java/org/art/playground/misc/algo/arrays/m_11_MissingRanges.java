package org.art.playground.misc.algo.arrays;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class m_11_MissingRanges {

    public List<String> findMissingRanges(int[] nums, int lower, int upper) {
        if (nums.length == 0) {
            return List.of(formatRange(lower, upper));
        }

        List<String> result = new ArrayList<>();

        if (lower < nums[0]) {
            result.add(formatRange(lower, nums[0] - 1));
        }

        for (int i = 1; i < nums.length; i++) {
            if (nums[i] - nums[i - 1] > 1) {
                result.add(formatRange(nums[i - 1] + 1, nums[i] - 1));
            }
        }

        if (nums[nums.length - 1] < upper) {
            result.add(formatRange(nums[nums.length - 1] + 1, upper));
        }

        return result;
    }

    private String formatRange(int from, int to) {
        if (from == to) {
            return "" + from;
        } else {
            return from + "->" + to;
        }
    }

    @Test
    void test0() {
        int[] nums = new int[]{0, 1, 3, 50, 75};

        assertThat(findMissingRanges(nums, 0, 99))
            .isEqualTo(List.of("2", "4->49", "51->74", "76->99"));
    }
}
