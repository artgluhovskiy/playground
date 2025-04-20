package org.art.playground.misc.algo.matrix;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Given an m x n matrix mat of integers, sort each matrix diagonal in ascending order and return the resulting matrix.
 */
public class m_1_SortMatrixDiagonally {

    public int[][] diagonalSort(int[][] mat) {
        Map<Integer, PriorityQueue<Integer>> diagonals = new HashMap<>();

        for (int i = 0; i < mat.length; i++) {
            for (int j = 0; j < mat[i].length; j++) {
                int id = i - j;
                diagonals.computeIfAbsent(id, _ -> new PriorityQueue<>()).add(mat[i][j]);
            }
        }

        for (int i = 0; i < mat.length; i++) {
            for (int j = 0; j < mat[i].length; j++) {
                mat[i][j] = diagonals.get(i - j).poll();
            }
        }

        return mat;
    }

    @Test
    void test0() {
        var input = new int[][]{
            new int[]{3, 3, 1, 1},
            new int[]{2, 2, 1, 2},
            new int[]{1, 1, 1, 2}
        };

        var expected = new int[][]{
            new int[]{1, 1, 1, 1},
            new int[]{1, 2, 2, 2},
            new int[]{1, 2, 3, 3}
        };

        assertThat(diagonalSort(input)).isEqualTo(expected);
    }
}
