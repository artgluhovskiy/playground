package org.art.playground.misc.algo.arrays;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Given an m x n integer matrix matrix, if an element is 0, set its entire row and column to 0's.
 * You must do it in place.
 */
public class m_7_SetMatrixZeros {

    /**
     * Sets entire rows and columns to zero if any element in that row or column is zero.
     * Uses the first row and column as markers to avoid using extra space.
     */
    public void setZeroes(int[][] matrix) {
        int rows = matrix.length;
        int cols = matrix[0].length;

        // Mark rows and columns that need to be zeroed
        boolean firstRowHasZero = markRowsAndColumns(matrix, rows, cols);

        // Zero out marked rows and columns
        zeroOutMarkedRowsAndColumns(matrix, rows, cols, firstRowHasZero);
    }

    private boolean markRowsAndColumns(int[][] matrix, int rows, int cols) {
        boolean firstRowHasZero = false;
        
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (matrix[i][j] == 0) {
                    // Mark column in first row
                    matrix[0][j] = 0;
                    
                    // Mark row in first column (except for first row)
                    if (i > 0) {
                        matrix[i][0] = 0;
                    } else {
                        firstRowHasZero = true;
                    }
                }
            }
        }
        return firstRowHasZero;
    }

    private void zeroOutMarkedRowsAndColumns(int[][] matrix, int rows, int cols, boolean firstRowHasZero) {
        // Zero out marked rows (except first row)
        for (int i = 1; i < rows; i++) {
            if (matrix[i][0] == 0) {
                for (int j = 0; j < cols; j++) {
                    matrix[i][j] = 0;
                }
            }
        }

        // Zero out marked columns
        for (int i = 0; i < cols; i++) {
            if (matrix[0][i] == 0) {
                for (int j = 0; j < rows; j++) {
                    matrix[j][i] = 0;
                }
            }
        }

        // Zero out first row if needed
        if (firstRowHasZero) {
            for (int j = 0; j < cols; j++) {
                matrix[0][j] = 0;
            }
        }
    }

    @Test
    void test1() {
        var input = new int[][]{
            new int[]{1, 1, 1},
            new int[]{1, 0, 1},
            new int[]{1, 1, 1}
        };

        setZeroes(input);

        var expected = new int[][]{
            new int[]{1, 0, 1},
            new int[]{0, 0, 0},
            new int[]{1, 0, 1}
        };

        assertThat(input).isEqualTo(expected);
    }

    @Test
    void test2() {
        var input = new int[][]{
            new int[]{0, 1, 2, 0},
            new int[]{3, 4, 5, 2},
            new int[]{1, 3, 1, 5}
        };

        setZeroes(input);

        var expected = new int[][]{
            new int[]{0, 0, 0, 0},
            new int[]{0, 4, 5, 0},
            new int[]{0, 3, 1, 0}
        };

        assertThat(input).isEqualTo(expected);
    }

    @Test
    void test3() {
        var input = new int[][]{
            new int[]{1, 0, 3}
        };

        setZeroes(input);

        var expected = new int[][]{
            new int[]{0, 0, 0}
        };

        assertThat(input).isEqualTo(expected);
    }

    @Test
    void test4() {
        var input = new int[][]{
            new int[]{1},
            new int[]{0},
            new int[]{1}
        };

        setZeroes(input);

        var expected = new int[][]{
            new int[]{0},
            new int[]{0},
            new int[]{0}
        };

        assertThat(input).isEqualTo(expected);
    }
}
