package org.art.playground.misc.algo.design;

import org.junit.jupiter.api.Test;

import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.assertj.core.api.Assertions.assertThat;

public class m_2_Flatten2DVector {

    static class Vector2D implements Iterator<Integer> {

        private final int[][] v;

        private int row = 0;
        private int col = 0;

        public Vector2D(int[][] v) {
            this.v = v;
        }

        @Override
        public Integer next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }

            int value = v[row][col];

            col++;

            return value;
        }

        @Override
        public boolean hasNext() {
            advanceToNextRowIfNeeded();
            return row < v.length;
        }

        private void advanceToNextRowIfNeeded() {
            while (row < v.length && col == v[row].length) {
                row++;
                col = 0;
            }
        }
    }

    @Test
    void test0() {
        int[][] data = new int[][]{
            new int[]{1, 2},
            new int[]{3},
            new int[]{4}
        };

        Vector2D v = new Vector2D(data);

        assertThat(v.next()).isEqualTo(1);
        assertThat(v.next()).isEqualTo(2);
        assertThat(v.next()).isEqualTo(3);
        assertThat(v.hasNext()).isTrue();
        assertThat(v.hasNext()).isTrue();
        assertThat(v.next()).isEqualTo(4);
        assertThat(v.hasNext()).isFalse();
    }

    @Test
    void test1() {
        int[][] data = new int[][]{
            new int[]{},
            new int[]{3}
        };

        Vector2D v = new Vector2D(data);

        assertThat(v.hasNext()).isTrue();
    }

    @Test
    void test2() {
        int[][] data = new int[][]{
            new int[]{},
            new int[]{}
        };

        Vector2D v = new Vector2D(data);

        assertThat(v.hasNext()).isFalse();
    }

    @Test
    void test3() {
        int[][] data = new int[][]{
            new int[]{},
            new int[]{},
            new int[]{1}
        };

        Vector2D v = new Vector2D(data);

        assertThat(v.hasNext()).isTrue();
    }

    @Test
    void test4() {
        int[][] data = new int[][]{
            new int[]{},
            new int[]{1},
            new int[]{}
        };

        Vector2D v = new Vector2D(data);

        assertThat(v.hasNext()).isTrue();
    }
}
