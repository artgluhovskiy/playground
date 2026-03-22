package org.art.playground.misc.algo.trees_and_graphs;

import org.junit.jupiter.api.Test;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * There is an m x n rectangular island that borders both the Pacific Ocean and Atlantic Ocean.
 * The Pacific Ocean touches the island's left and top edges, and the Atlantic Ocean touches the island's right and bottom edges.
 * <p>
 * The island is partitioned into a grid of square cells.
 * You are given an m x n integer matrix heights where heights[r][c] represents the height above sea level of the cell at coordinate (r, c).
 * <p>
 * The island receives a lot of rain, and the rain water can flow to neighboring cells directly north,
 * south, east, and west if the neighboring cell's height is less than or equal to the current cell's height.
 * Water can flow from any cell adjacent to an ocean into the ocean.
 * <p>
 * Return a 2D list of grid coordinates result where result[i] = [ri, ci] denotes that rain water can flow from cell (ri, ci)
 * to both the Pacific and Atlantic oceans.
 */
public class m_11_PacificAtlanticWaterFlow {

    private static final byte PACIFIC = 1;
    private static final byte ATLANTIC = 2;
    private static final byte BOTH = 3;

    private static final List<int[]> DIRECTIONS = List.of(
        new int[]{0, 1}, new int[]{0, -1},
        new int[]{1, 0}, new int[]{-1, 0}
    );

    public List<List<Integer>> pacificAtlantic(int[][] heights) {
        int rows = heights.length;
        int cols = heights[0].length;

        byte[][] visited = new byte[rows][cols];

        // Row 0 (all Pacific)
        for (int i = 0; i < cols; i++) {
            bfs(0, i, heights, visited, PACIFIC);
        }

        // Col 0 (all Pacific)
        for (int i = 0; i < rows; i++) {
            bfs(i, 0, heights, visited, PACIFIC);
        }

        // Row last (all Atlantic)
        for (int i = 0; i < cols; i++) {
            bfs(rows - 1, i, heights, visited, ATLANTIC);
        }

        // Col last (all Atlantic)
        for (int i = 0; i < rows; i++) {
            bfs(i, cols - 1, heights, visited, ATLANTIC);
        }

        List<List<Integer>> result = new ArrayList<>();

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (visited[i][j] == BOTH) {
                    result.add(List.of(i, j));
                }
            }
        }

        return result;
    }

    private void bfs(int r, int c, int[][] heights, byte[][] visited, byte type) {
        // Check if the cell is already visited
        byte visitedCell = visited[r][c];
        if (visitedCell == type || visitedCell == BOTH) {
            return;
        }

        Deque<int[]> queue = new ArrayDeque<>();
        queue.addLast(new int[]{r, c});

        while (!queue.isEmpty()) {

            int[] current = queue.pollFirst();
            int currentR = current[0];
            int currentC = current[1];

            // Visit cell
            if (visited[currentR][currentC] != 0) {
                if ((type == PACIFIC && visited[currentR][currentC] == ATLANTIC)
                    || (type == ATLANTIC && visited[currentR][currentC] == PACIFIC)) {

                    visited[currentR][currentC] = BOTH;
                }
            } else {
                visited[currentR][currentC] = type;
            }

            // Explore adjacent cells
            for (int[] direction : DIRECTIONS) {
                int newR = currentR + direction[0];
                int newC = currentC + direction[1];

                if (newR < 0 || newR >= heights.length || newC < 0 || newC >= heights[0].length) {
                    continue;
                }

                if ((heights[currentR][currentC] <= heights[newR][newC])
                    && visited[newR][newC] != type
                    && visited[newR][newC] != BOTH) {

                    queue.addLast(new int[]{newR, newC});
                }
            }
        }
    }

    @Test
    void test0() {
        int[][] input = {
            {1, 2, 2, 3, 5}, {3, 2, 3, 4, 4}, {2, 4, 5, 3, 1}, {6, 7, 1, 4, 5}, {5, 1, 1, 2, 4}
        };

        List<List<Integer>> expected = List.of(
            List.of(0, 4), List.of(1, 3), List.of(1, 4), List.of(2, 2), List.of(3, 0), List.of(3, 1), List.of(4, 0)
        );

        assertThat(pacificAtlantic(input)).isEqualTo(expected);
    }

    @Test
    void test1() {
        int[][] input = {{1}};

        List<List<Integer>> expected = List.of(List.of(0, 0));

        assertThat(pacificAtlantic(input)).isEqualTo(expected);
    }
}
