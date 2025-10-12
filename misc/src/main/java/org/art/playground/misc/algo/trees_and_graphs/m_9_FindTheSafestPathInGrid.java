package org.art.playground.misc.algo.trees_and_graphs;

import org.junit.jupiter.api.Test;

import java.util.ArrayDeque;
import java.util.Comparator;
import java.util.Deque;
import java.util.List;
import java.util.Objects;
import java.util.PriorityQueue;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * You are given a 0-indexed 2D matrix grid of size n x n, where (r, c) represents:
 * <p>
 * A cell containing a thief if grid[r][c] = 1
 * An empty cell if grid[r][c] = 0
 * You are initially positioned at cell (0, 0). In one move, you can move to any adjacent cell in the grid,
 * including cells containing thieves.
 */
public class m_9_FindTheSafestPathInGrid {

    private static final int[][] DIRECTIONS = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    /**
     * Finds the maximum safeness factor for a path from (0,0) to (n-1, n-1).
     * The safeness factor of a path is the minimum Manhattan distance from any cell on the path to any thief.
     * Uses multi-source BFS to compute distances from thieves, then modified Dijkstra to find optimal path.
     *
     * @param grid n x n grid where 1 represents a thief and 0 represents an empty cell
     * @return maximum safeness factor of any path from top-left to bottom-right
     */
    public int maximumSafenessFactor(List<List<Integer>> grid) {
        if (grid.getFirst().getFirst() == 1
            || grid.getLast().getLast() == 1) {
            return 0;
        }

        int rows = grid.size();
        int cols = grid.getFirst().size();

        int[][] distances = multiSourceBfs(grid, rows, cols);

        PriorityQueue<Cell> maxQueue = new PriorityQueue<>(Comparator.comparingInt(Cell::safeness).reversed());
        boolean[][] visited = new boolean[rows][cols];

        Cell start = new Cell(0, 0, distances[0][0]);
        maxQueue.add(start);
        visited[0][0] = true;

        while (!maxQueue.isEmpty()) {
            Cell cell = maxQueue.poll();

            if (isDestination(cell, rows, cols)) {
                return cell.safeness;
            }

            for (int[] dir : DIRECTIONS) {
                addToPriorityQueue(cell.x + dir[0], cell.y + dir[1], cell.safeness, maxQueue, visited, distances);
            }
        }

        return 0;
    }

    /**
     * Computes Manhattan distance from each cell to nearest thief using multi-source BFS.
     * All thieves are added to the queue initially (distance 0), then BFS propagates outward.
     */
    private int[][] multiSourceBfs(List<List<Integer>> grid, int rows, int cols) {
        int[][] distances = new int[rows][cols];

        Deque<int[]> deque = new ArrayDeque<>();
        boolean[][] visited = new boolean[rows][cols];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (grid.get(i).get(j) == 1) {
                    deque.addLast(new int[]{i, j});
                    visited[i][j] = true;
                    distances[i][j] = 0;
                }
            }
        }

        int dist = 1;
        while (!deque.isEmpty()) {
            int size = deque.size();

            for (int i = 0; i < size; i++) {
                int[] cell = deque.pollFirst();

                for (int[] dir : DIRECTIONS) {
                    addToDistQueue(cell[0] + dir[0], cell[1] + dir[1], dist, distances, deque, visited);
                }
            }

            dist++;
        }

        return distances;
    }

    private void addToDistQueue(int x, int y, int dist, int[][] distances, Deque<int[]> queue, boolean[][] visited) {
        int rows = distances.length;
        int cols = distances[0].length;

        if (isValid(x, y, rows, cols) && !visited[x][y]) {
            queue.addLast(new int[]{x, y});
            visited[x][y] = true;
            distances[x][y] = dist;
        }
    }

    private boolean isDestination(Cell cell, int rows, int cols) {
        return cell.x == rows - 1 && cell.y == cols - 1;
    }

    private void addToPriorityQueue(int x, int y, int dist, PriorityQueue<Cell> maxQueue, boolean[][] visited, int[][] distances) {
        int rows = distances.length;
        int cols = distances[0].length;

        if (isValid(x, y, rows, cols) && !visited[x][y]) {
            Cell cell = new Cell(x, y, Math.min(dist, distances[x][y]));
            maxQueue.add(cell);
            visited[x][y] = true;
        }
    }

    private boolean isValid(int x, int y, int rows, int cols) {
        return x >= 0 && x < rows && y >= 0 && y < cols;
    }

    record Cell(int x, int y, int safeness) {

        @Override
        public boolean equals(Object o) {
            if (o == null || getClass() != o.getClass()) return false;
            Cell cell = (Cell) o;
            return x == cell.x && y == cell.y;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }
    }

    @Test
    void test0() {
        var grid = List.of(
            List.of(0, 0, 1),
            List.of(0, 0, 0),
            List.of(0, 0, 0)
        );

        assertThat(maximumSafenessFactor(grid))
            .isEqualTo(2);
    }

    @Test
    void test1() {
        var grid = List.of(
            List.of(0, 0, 0, 1),
            List.of(0, 0, 0, 0),
            List.of(0, 0, 0, 0),
            List.of(1, 0, 0, 0)
        );

        assertThat(maximumSafenessFactor(grid))
            .isEqualTo(2);
    }
}
