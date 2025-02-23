package org.art.playground.misc.algo.backtracking;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Given an m x n grid of characters board and a string word, return true if word exists in the grid.
 * <p>
 * The word can be constructed from letters of sequentially adjacent cells,
 * where adjacent cells are horizontally or vertically neighboring. The same letter cell may not be used more than once.
 */
public class m_4_WordSearch {

    public boolean exist(char[][] board, String word) {
        boolean[][] visited = new boolean[board.length][board[0].length];

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                boolean res = findSubWord(i, j, board, visited, 0, word);
                if (res == true) {
                    return true;
                }
            }
        }

        return false;
    }

    private boolean findSubWord(int i, int j, char[][] board, boolean[][] visited, int charIdx, String word) {
        if (charIdx == word.length()) {
            return true;
        }

        if (i < 0 || i >= board.length
            || j < 0 || j >= board[i].length
            || board[i][j] != word.charAt(charIdx)) {
            return false;
        }

        if (visited[i][j] == true) {
            return false;
        }

        visited[i][j] = true;

        if (findSubWord(i, j - 1, board, visited, charIdx + 1, word)) {
            return true;
        }

        if (findSubWord(i, j + 1, board, visited, charIdx + 1, word)) {
            return true;
        }

        if (findSubWord(i - 1, j, board, visited, charIdx + 1, word)) {
            return true;
        }

        if (findSubWord(i + 1, j, board, visited, charIdx + 1, word)) {
            return true;
        }

        visited[i][j] = false;

        return false;
    }

    @Test
    void test0() {
        var board = new char[][]{
            new char[]{'A', 'B', 'C', 'E'},
            new char[]{'S', 'F', 'C', 'S'},
            new char[]{'A', 'D', 'E', 'E'},
        };

        assertThat(exist(board, "ABCCED")).isTrue();
    }

    @Test
    void test1() {
        var board = new char[][]{
            new char[]{'A', 'B', 'C', 'E'},
            new char[]{'S', 'F', 'C', 'S'},
            new char[]{'A', 'D', 'E', 'E'},
        };

        assertThat(exist(board, "SEE")).isTrue();
    }

    @Test
    void test2() {
        var board = new char[][]{
            new char[]{'A', 'B', 'C', 'E'},
            new char[]{'S', 'F', 'C', 'S'},
            new char[]{'A', 'D', 'E', 'E'},
        };

        assertThat(exist(board, "ABCB")).isFalse();
    }

    @Test
    void test3() {
        var board = new char[][]{
            new char[]{'A', 'B', 'C', 'E'},
            new char[]{'S', 'F', 'C', 'S'},
            new char[]{'A', 'D', 'E', 'E'},
        };

        assertThat(exist(board, "BCE")).isTrue();
    }
}
