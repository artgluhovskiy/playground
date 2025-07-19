package org.art.playground.misc.algo.trees;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Given an m x n 2D binary grid grid which represents a map of '1's (land) and '0's (water), return the number of islands.
 * <p>
 * An island is surrounded by water and is formed by connecting adjacent lands horizontally or vertically.
 * You may assume all four edges of the grid are all surrounded by water.
 */
public class m_5_NumberOfIslands {

    // Direction arrays for 4 adjacent cells (up, right, down, left)
    private static final int[] DIRECTIONS = {-1, 0, 1, 0, -1};

    public int numIslands(char[][] grid) {
        if (grid == null || grid.length == 0) {
            return 0;
        }

        int islands = 0;
        int rows = grid.length;
        int cols = grid[0].length;

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (grid[i][j] == '1') {
                    islands++;
                    dfs(i, j, grid);
                }
            }
        }
        return islands;
    }

    private void dfs(int row, int col, char[][] grid) {
        int rows = grid.length;
        int cols = grid[0].length;

        // Mark current cell as visited by setting it to water
        grid[row][col] = '0';

        // Explore all 4 directions
        for (int k = 0; k < 4; k++) {
            int newRow = row + DIRECTIONS[k];
            int newCol = col + DIRECTIONS[k + 1];

            if (isValid(newRow, newCol, rows, cols) && grid[newRow][newCol] == '1') {
                dfs(newRow, newCol, grid);
            }
        }
    }

    private boolean isValid(int row, int col, int rows, int cols) {
        return row >= 0 && row < rows && col >= 0 && col < cols;
    }

    @Test
    void test1() {
        char[][] input = new char[][]{
            {'1', '1', '1', '1', '0'},
            {'1', '1', '0', '1', '0'},
            {'1', '1', '0', '0', '0'},
            {'0', '0', '0', '0', '0'}
        };

        assertThat(numIslands(input)).isEqualTo(1);
    }

    @Test
    void test2() {
        char[][] input = new char[][]{
            {'1', '1', '0', '0', '0'},
            {'1', '1', '0', '0', '0'},
            {'0', '0', '1', '0', '0'},
            {'0', '0', '0', '1', '1'}
        };

        assertThat(numIslands(input)).isEqualTo(3);
    }
}
