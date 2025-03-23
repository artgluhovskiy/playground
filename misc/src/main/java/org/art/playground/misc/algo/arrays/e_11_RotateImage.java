package org.art.playground.misc.algo.arrays;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * You are given an n x n 2D matrix representing an image, rotate the image by 90 degrees (clockwise).
 * You have to rotate the image in-place, which means you have to modify the input 2D matrix directly.
 * DO NOT allocate another 2D matrix and do the rotation.
 */
public class e_11_RotateImage {

    public void rotate(int[][] matrix) {
        int len = matrix.length;
        int mid = len / 2;
        int pivotR = len % 2 == 0 ? mid : mid + 1;
        int pivotC = mid;

        for (int r = 0; r < pivotR; r++) {
            for (int c = 0; c < pivotC; c++) {
                int temp = matrix[r][c];
                matrix[r][c] = matrix[len - c - 1][r];
                matrix[len - c - 1][r] = matrix[len - r - 1][len - c - 1];
                matrix[len - r - 1][len - c - 1] = matrix[c][len - r - 1];
                matrix[c][len - r - 1] = temp;
            }
        }
    }

    @Test
    void test0() {
        int[][] input = {
            {1, 2, 3},
            {4, 5, 6},
            {7, 8, 9}
        };

        int[][] expected = {
            {7, 4, 1},
            {8, 5, 2},
            {9, 6, 3}
        };

        rotate(input);

        assertThat(input).isEqualTo(expected);
    }

    @Test
    void test1() {
        int[][] input = {
            {5, 1, 9, 11},
            {2, 4, 8, 10},
            {13, 3, 6, 7},
            {15, 14, 12, 16}
        };

        int[][] expected = {
            {15, 13, 2, 5},
            {14, 3, 4, 1},
            {12, 6, 8, 9},
            {16, 7, 10, 11}
        };

        rotate(input);

        assertThat(input).isEqualTo(expected);
    }
}
