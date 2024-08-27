package org.art.playground.misc.algo.misc;

import static org.assertj.core.api.Assertions.assertThat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Test;

/**
 * Given an integer numRows, return the first numRows of Pascal's triangle.
 */
public class p4_PascalTriangle {

    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> result = new ArrayList<>();
        result.add(List.of(1));

        if (numRows == 1) {
            return result;
        }

        for (int i = 1; i < numRows; i++) {
            Integer[] row = new Integer[i + 1];
            row[0] = 1;
            row[i] = 1;

            List<Integer> prevRow = result.get(i - 1);

            for (int j = 1; j < i; j++) {
                row[j] = prevRow.get(j - 1) + prevRow.get(j);
            }

            result.add(Arrays.asList(row));
        }

        return result;
    }

    @Test
    void test0() {
        assertThat(generate(5)).isEqualTo(
            List.of(
                List.of(1),
                List.of(1, 1),
                List.of(1, 2, 1),
                List.of(1, 3, 3, 1),
                List.of(1, 4, 6, 4, 1)
            )
        );
    }

    @Test
    void test1() {
        assertThat(generate(1)).isEqualTo(
            List.of(
                List.of(1)
            )
        );
    }

}
