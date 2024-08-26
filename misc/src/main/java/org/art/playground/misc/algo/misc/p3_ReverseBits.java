package org.art.playground.misc.algo.misc;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Reverse bits of a given 32 bits unsigned integer.
 */
public class p3_ReverseBits {

    // you need treat n as an unsigned value
    public int reverseBits(int n) {
        int result = 0;

        for (int i = 0; i < 32; i++) {
            int bit = (n >> i) & 1;
            result = result | (bit << (31 - i));
        }

        return result;
    }

    @Test
    void test0() {
        assertThat(reverseBits(43261596)).isEqualTo(964176192);
    }

    @Test
    void test1() {
        assertThat(reverseBits(-3)).isEqualTo(-1073741825);
    }
}
