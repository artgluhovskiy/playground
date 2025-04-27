package org.art.playground.misc.algo.dp;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * You are given an integer array coins representing coins of different denominations
 * and an integer amount representing a total amount of money.
 * <p>
 * Return the fewest number of coins that you need to make up that amount.
 * If that amount of money cannot be made up by any combination of the coins, return -1.
 */
public class m_3_CoinChange {

    public int coinChange(int[] coins, int amount) {
        int[] amounts = new int[amount + 1];

        Arrays.fill(amounts, Integer.MAX_VALUE - 1);

        amounts[0] = 0;

        for (int i = 1; i < amounts.length; i++) {
            for (int j = coins.length - 1; j >= 0; j--) {

                int prevAmountIdx = i - coins[j];

                if (prevAmountIdx >= 0) {
                    amounts[i] = Math.min(amounts[i], amounts[prevAmountIdx] + 1);
                }
            }
        }

        return amounts[amount] != Integer.MAX_VALUE - 1 ? amounts[amount] : -1;
    }

    @Test
    void test0() {
        assertThat(coinChange(new int[]{1, 2, 5}, 11)).isEqualTo(3);
    }

    @Test
    void test1() {
        assertThat(coinChange(new int[]{2}, 3)).isEqualTo(-1);
    }

    @Test
    void test2() {
        assertThat(coinChange(new int[]{1}, 0)).isEqualTo(0);
    }

    @Test
    void test3() {
        assertThat(coinChange(new int[]{186, 419, 83, 408}, 6249)).isEqualTo(20);
    }
}

