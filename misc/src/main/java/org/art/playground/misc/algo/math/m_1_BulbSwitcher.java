package org.art.playground.misc.algo.math;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * There are n bulbs that are initially off. You first turn on all the bulbs,
 * then you turn off every second bulb.
 * On the third round, you toggle every third bulb (turning on if it's off or turning off if it's on).
 * For the ith round, you toggle every i bulb. For the nth round, you only toggle the last bulb.
 * Return the number of bulbs that are on after n rounds.
 */
public class m_1_BulbSwitcher {

    public int bulbSwitch(int n) {
        return  (int) Math.sqrt(n);
    }

    @Test
    void test0() {
        assertThat(bulbSwitch(3)).isEqualTo(1);
    }

    @Test
    void test1() {
        assertThat(bulbSwitch(0)).isEqualTo(0);
    }

    @Test
    void test2() {
        assertThat(bulbSwitch(1)).isEqualTo(1);
    }

    @Test
    void test3() {
        assertThat(bulbSwitch(99999999)).isEqualTo(9999);
    }
}
