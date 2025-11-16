package org.art.playground.misc.algo.trees_and_graphs;

import org.junit.jupiter.api.Test;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * 3286. Find a Safe Walk Through a Grid
 * <p>
 * You are given an m x n binary matrix grid and an integer health.
 * <p>
 * You start on the upper-left corner (0, 0) and would like to get to the lower-right corner (m - 1, n - 1).
 * <p>
 * You can move up, down, left, or right from one cell to another adjacent cell as long as your health remains positive.
 * <p>
 * Cells (i, j) with grid[i][j] = 1 are considered unsafe and reduce your health by 1.
 * <p>
 * Return true if you can reach the final cell with a health value of 1 or more, and false otherwise.
 */
public class m_10_FindSafeWalkThroughGrid {

    private static final int[][] DIRECTIONS = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    public boolean findSafeWalk(List<List<Integer>> grid, int health) {
        int rows = grid.size();
        int cols = grid.getFirst().size();

        // Initializing health grid, containing the max seen health of the cell
        int[][] healthGrid = new int[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                healthGrid[i][j] = -1;
            }
        }

        // Initial position
        int[] initPos = new int[3];
        initPos[0] = 0;
        initPos[1] = 0;

        if (grid.getFirst().getFirst() == 1) {
            initPos[2] = health - 1;
        } else {
            initPos[2] = health;
        }

        // BFS
        Deque<int[]> queue = new ArrayDeque<>();
        queue.addFirst(initPos);

        while (!queue.isEmpty()) {
            int[] cell = queue.pollLast();

            int curRow = cell[0];
            int curCol = cell[1];
            int curHealth = cell[2];

            if (curHealth < 1) {
                continue;
            }

            if (isDestination(cell, rows, cols)) {
                return true;
            }

            int currentMaxSeenHealth = healthGrid[curRow][curCol];

            if (currentMaxSeenHealth >= curHealth) {
                continue;
            }

            healthGrid[curRow][curCol] = curHealth;

            for (int[] direction : DIRECTIONS) {
                int nextRow = curRow + direction[0];
                int nextCol = curCol + direction[1];

                if (!isValid(nextRow, nextCol, rows, cols)) {
                    continue;
                }

                int nextHealth;
                if (grid.get(nextRow).get(nextCol) == 1) {
                    nextHealth = curHealth - 1;
                } else {
                    nextHealth = curHealth;
                }

                if (nextHealth > healthGrid[nextRow][nextCol]) {
                    queue.addFirst(new int[]{nextRow, nextCol, nextHealth});
                }
            }
        }

        return false;
    }

    private boolean isDestination(int[] cell, int rows, int cols) {
        return cell[0] == rows - 1 && cell[1] == cols - 1;
    }

    private boolean isValid(int row, int col, int rows, int cols) {
        return row >= 0 && row < rows
            && col >= 0 && col < cols;
    }

    @Test
    void test0() {
        var grid = List.of(
            List.of(0, 1, 0, 0, 0),
            List.of(0, 1, 0, 1, 0),
            List.of(0, 0, 0, 1, 0)
        );

        assertThat(findSafeWalk(grid, 1)).isTrue();
    }

    @Test
    void test1() {
        var grid = List.of(
            List.of(0, 1, 1, 0, 0, 0),
            List.of(1, 0, 1, 0, 0, 0),
            List.of(0, 1, 1, 1, 0, 1),
            List.of(0, 0, 1, 0, 1, 0)
        );

        assertThat(findSafeWalk(grid, 3)).isFalse();
    }

    @Test
    void test2() {
        var grid = List.of(
            List.of(1, 1, 1),
            List.of(1, 0, 1),
            List.of(1, 1, 1)
        );

        assertThat(findSafeWalk(grid, 5)).isTrue();
    }
}
