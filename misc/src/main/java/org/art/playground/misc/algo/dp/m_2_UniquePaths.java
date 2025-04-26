package org.art.playground.misc.algo.dp;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * There is a robot on an m x n grid. The robot is initially located at the top-left corner (i.e., grid[0][0]).
 * The robot tries to move to the bottom-right corner (i.e., grid[m - 1][n - 1]).
 * The robot can only move either down or right at any point in time.
 * <p>
 * Given the two integers m and n, return the number of possible unique paths
 * that the robot can take to reach the bottom-right corner.
 */
public class m_2_UniquePaths {

    public int uniquePaths(int m, int n) {
        int[][] grid = new int[m][n];

        for (int i = m - 1; i >= 0; i--) {
            for (int j = n - 1; j >= 0; j--) {
                if (i == m - 1 || j == n - 1) {
                    grid[i][j] = 1;
                } else {
                    grid[i][j] = grid[i + 1][j] + grid[i][j + 1];
                }
            }
        }

        return grid[0][0];
    }

    @Test
    void test0() {
        assertThat(uniquePaths(3, 7)).isEqualTo(28);
    }

    @Test
    void test1() {
        assertThat(uniquePaths(3, 2)).isEqualTo(3);
    }
}
