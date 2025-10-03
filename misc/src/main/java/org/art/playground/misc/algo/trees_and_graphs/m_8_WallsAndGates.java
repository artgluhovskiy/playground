package org.art.playground.misc.algo.trees_and_graphs;

import org.junit.jupiter.api.Test;

import java.util.ArrayDeque;
import java.util.Deque;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Multi-source BFS algo.
 */
public class m_8_WallsAndGates {

    private static final Integer GATE = 0;
    private static final Integer WALL = -1;

    public void wallsAndGates(int[][] rooms) {
        int rows = rooms.length;
        int cols = rooms[0].length;

        boolean[][] visited = new boolean[rows][cols];
        Deque<int[]> queue = new ArrayDeque<>();

        // Find all GATES and use them as sources for BFS
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (rooms[i][j] == GATE) {
                    queue.addLast(new int[]{i, j});
                    visited[i][j] = true;
                }
            }
        }

        int dist = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();

            for (int i = 0; i < size; i++) {
                int[] room = queue.pollFirst();

                rooms[room[0]][room[1]] = dist;

                addRoom(room[0] + 1, room[1], rooms, visited, queue);
                addRoom(room[0] - 1, room[1], rooms, visited, queue);
                addRoom(room[0], room[1] + 1, rooms, visited, queue);
                addRoom(room[0], room[1] - 1, rooms, visited, queue);
            }

            dist++;
        }
    }

    private void addRoom(int i, int j, int[][] rooms, boolean[][] visited, Deque<int[]> queue) {
        if (i >= 0 && i < rooms.length
            && j >= 0 && j < rooms[0].length
            && !visited[i][j]
            && rooms[i][j] != WALL) {

            queue.addLast(new int[]{i, j});
            visited[i][j] = true;
        }
    }

    @Test
    void test0() {
        int[][] input = new int[][]{
            new int[]{Integer.MAX_VALUE, -1, 0, Integer.MAX_VALUE},
            new int[]{Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, -1},
            new int[]{Integer.MAX_VALUE, -1, Integer.MAX_VALUE, -1},
            new int[]{0, -1, Integer.MAX_VALUE, Integer.MAX_VALUE},
        };

        wallsAndGates(input);

        assertThat(input).isDeepEqualTo(
            new int[][]{
                new int[]{3, -1, 0, 1},
                new int[]{2, 2, 1, -1},
                new int[]{1, -1, 2, -1},
                new int[]{0, -1, 3, 4},
            }
        );
    }
}
