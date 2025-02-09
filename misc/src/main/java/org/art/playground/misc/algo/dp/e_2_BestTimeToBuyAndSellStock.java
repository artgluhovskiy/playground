package org.art.playground.misc.algo.dp;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * You are given an array prices where prices[i] is the price of a given stock on the ith day.
 * You want to maximize your profit by choosing a single day to buy one stock and
 * choosing a different day in the future to sell that stock.
 * Return the maximum profit you can achieve from this transaction. If you cannot achieve any profit, return 0.
 */
public class e_2_BestTimeToBuyAndSellStock {

    public int maxProfit(int[] prices) {
        if (prices.length == 1) {
            return 0;
        }

        int lowestPriceSeenBefore = prices[0];
        int maxProfit = 0;

        for (int i = 1; i < prices.length; i++) {
            int currentPrice = prices[i];
            int currentLowestPriceSeenBefore = Math.min(lowestPriceSeenBefore, currentPrice);
            int maxCurrentProfit = currentPrice - currentLowestPriceSeenBefore;
            if (maxCurrentProfit > maxProfit) {
                maxProfit = maxCurrentProfit;
            }
            lowestPriceSeenBefore = currentLowestPriceSeenBefore;
        }

        return maxProfit;
    }

    @Test
    void test0() {
        var prices = new int[]{7, 1, 5, 3, 6, 4};

        assertThat(maxProfit(prices)).isEqualTo(5);
    }

    @Test
    void test1() {
        var prices = new int[]{7, 6, 4, 3, 1};

        assertThat(maxProfit(prices)).isEqualTo(0);
    }
}
