package org.art.playground.misc.algo.trees_and_graphs;

import org.junit.jupiter.api.Test;

import java.util.Comparator;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * You are given an n x n integer matrix grid where each value
 * grid[i][j] represents the elevation at that point (i, j).
 * It starts raining, and water gradually rises over time.
 * At time t, the water level is t, meaning any cell with elevation less than equal to t is submerged or reachable.
 * You can swim from a square to another 4-directionally adjacent square
 * if and only if the elevation of both squares individually are at most t.
 * You can swim infinite distances in zero time. Of course, you must stay within the boundaries of the grid during your swim.
 * Return the minimum time until you can reach the bottom right square (n - 1, n - 1)
 * if you start at the top left square (0, 0).
 */
public class h_1_SwimInRisingWater {

    /**
     * Uses Dijkstra's algorithm to find the minimum time required to reach the destination.
     * Priority queue ensures we always process cells with the minimum time first (greedy).
     */
    public int swimInWater(int[][] grid) {
        Set<Cell> visited = new HashSet<>();
        PriorityQueue<Cell> queue = new PriorityQueue<>(Comparator.comparingInt(cell -> cell.time));

        queue.add(new Cell(0, 0, grid[0][0]));
        visited.add(new Cell(0, 0, grid[0][0]));

        int targetX = grid.length - 1;
        int targetY = grid[0].length - 1;

        while (!queue.isEmpty()) {
            Cell cell = queue.poll();

            if (isDestination(cell, targetX, targetY)) {
                return cell.time;
            }

            addToQueue(cell.x + 1, cell.y, cell.time, grid, queue, visited);
            addToQueue(cell.x - 1, cell.y, cell.time, grid, queue, visited);
            addToQueue(cell.x, cell.y + 1, cell.time, grid, queue, visited);
            addToQueue(cell.x, cell.y - 1, cell.time, grid, queue, visited);
        }

        return -1;
    }

    private boolean isDestination(Cell cell, int targetX, int targetY) {
        return cell.x == targetX && cell.y == targetY;
    }

    private void addToQueue(int x, int y, int currentTime, int[][] grid, PriorityQueue<Cell> queue, Set<Cell> visited) {
        if (x < 0 || x >= grid.length || y < 0 || y >= grid[0].length) {
            return;
        }

        Cell cell = new Cell(x, y, Math.max(currentTime, grid[x][y]));
        if (visited.contains(cell)) {
            return;
        }

        queue.add(cell);
        visited.add(cell);
    }

    record Cell(int x, int y, int time) {

        @Override
        public boolean equals(Object o) {
            if (o == null || getClass() != o.getClass()) return false;
            Cell cell = (Cell) o;
            return x == cell.x && y == cell.y;
        }

        @Override
        public int hashCode() {
            return x * 31 + y;
        }
    }

    @Test
    void test0() {
        int[][] input = new int[][]{
            new int[]{0, 2},
            new int[]{1, 3}
        };

        assertThat(swimInWater(input))
            .isEqualTo(3);
    }

    @Test
    void test1() {
        int[][] input = new int[][]{
            new int[]{0, 1, 2, 3, 4},
            new int[]{24, 23, 22, 21, 5},
            new int[]{12, 13, 14, 15, 16},
            new int[]{11, 17, 18, 19, 20},
            new int[]{10, 9, 8, 7, 6}
        };

        assertThat(swimInWater(input))
            .isEqualTo(16);
    }
}
