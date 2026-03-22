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

    private static final List<int[]> DIRECTIONS = List.of(
        new int[]{0, 1}, new int[]{0, -1},
        new int[]{1, 0}, new int[]{-1, 0}
    );

    public List<List<Integer>> pacificAtlantic(int[][] heights) {
        int rows = heights.length;
        int cols = heights[0].length;

        byte[][] visited = new byte[rows][cols];

        Deque<int[]> queue = new ArrayDeque<>();

        for (int i = 0; i < rows; i++) {
            visited[i][0] |= PACIFIC;
            queue.addLast(new int[]{i, 0});
        }
        for (int j = 1; j < cols; j++) {
            visited[0][j] |= PACIFIC;
            queue.addLast(new int[]{0, j});
        }
        bfs(queue, heights, visited, PACIFIC);

        for (int i = 0; i < rows; i++) {
            visited[i][cols - 1] |= ATLANTIC;
            queue.addLast(new int[]{i, cols - 1});
        }
        for (int j = 0; j < cols - 1; j++) {
            visited[rows - 1][j] |= ATLANTIC;
            queue.addLast(new int[]{rows - 1, j});
        }
        bfs(queue, heights, visited, ATLANTIC);

        List<List<Integer>> result = new ArrayList<>();
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (visited[i][j] == (ATLANTIC | PACIFIC)) {
                    result.add(List.of(i, j));
                }
            }
        }
        return result;
    }

    private void bfs(Deque<int[]> queue, int[][] heights, byte[][] visited, byte type) {
        while (!queue.isEmpty()) {
            int[] current = queue.pollFirst();
            int r = current[0];
            int c = current[1];

            for (int[] dir : DIRECTIONS) {
                int nr = r + dir[0];
                int nc = c + dir[1];

                if (nr >= 0 && nr < heights.length && nc >= 0 && nc < heights[0].length
                    && (visited[nr][nc] & type) == 0
                    && heights[nr][nc] >= heights[r][c]) {

                    visited[nr][nc] |= type;
                    queue.addLast(new int[]{nr, nc});
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
