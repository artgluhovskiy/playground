package org.art.playground.misc.algo.arrays;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Best Time to Buy and Sell Stock II (Leetcode).
 * </p>
 * You are given an integer array prices where prices[i] is the price of a given stock on the ith day.
 * On each day, you may decide to buy and/or sell the stock. You can only hold at most
 * one share of the stock at any time. However, you can buy it then immediately sell it on the same day.
 * Find and return the maximum profit you can achieve.
 */
public class p2_BestTimeToBuyAndSellStockII {

    public int maxProfit(int[] prices) {
        if (prices.length == 1) {
            return 0;
        }

        int profit = 0;

        int buy = 0;
        int sell = 1;

        while (sell < prices.length) {
            if (prices[sell] >= prices[buy]) {
                profit += prices[sell] - prices[buy];
            }

            buy++;
            sell++;
        }

        return profit;
    }

    @Test
    void test1() {
        int[] input = {7, 1, 5, 3, 6, 4};
        int expectedOutput = 7;

        int actualOutput = maxProfit(input);

        assertThat(actualOutput).isEqualTo(expectedOutput);
    }

    @Test
    void test2() {
        int[] input = {1, 2, 3, 4, 5};
        int expectedOutput = 4;

        int actualOutput = maxProfit(input);

        assertThat(actualOutput).isEqualTo(expectedOutput);
    }

    @Test
    void test3() {
        int[] input = {7, 6, 4, 3, 1};
        int expectedOutput = 0;

        int actualOutput = maxProfit(input);

        assertThat(actualOutput).isEqualTo(expectedOutput);
    }

}
