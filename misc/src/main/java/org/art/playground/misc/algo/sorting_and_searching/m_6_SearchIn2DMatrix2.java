package org.art.playground.misc.algo.sorting_and_searching;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Write an efficient algorithm that searches for a value target in an m x n integer matrix matrix.
 * This matrix has the following properties:
 * <p>
 * Integers in each row are sorted in ascending from left to right.
 * Integers in each column are sorted in ascending from top to bottom.
 */
public class m_6_SearchIn2DMatrix2 {

    public boolean searchMatrix(int[][] matrix, int target) {
        int row = matrix.length - 1;
        int col = 0;

        while (row >= 0 && col < matrix[0].length) {
            if (matrix[row][col] == target) {
                return true;
            }

            if (matrix[row][col] < target) {
                col++;
            } else {
                row--;
            }
        }

        return false;
    }

    @Test
    void test1() {
        int[][] input = {
            new int[]{1, 4, 7, 11, 15},
            new int[]{2, 5, 8, 12, 19},
            new int[]{3, 6, 9, 16, 22},
            new int[]{10, 13, 14, 17, 24},
            new int[]{18, 21, 23, 26, 30},
        };

        assertThat(searchMatrix(input, 5)).isTrue();
    }

    @Test
    void test2() {
        int[][] input = {
            new int[]{1, 4, 7, 11, 15},
            new int[]{2, 5, 8, 12, 19},
            new int[]{3, 6, 9, 16, 22},
            new int[]{10, 13, 14, 17, 24},
            new int[]{18, 21, 23, 26, 30},
        };

        assertThat(searchMatrix(input, 20)).isFalse();
    }
}
