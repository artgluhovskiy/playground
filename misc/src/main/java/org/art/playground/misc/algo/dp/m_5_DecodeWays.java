package org.art.playground.misc.algo.dp;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * You have intercepted a secret message encoded as a string of numbers. The message is decoded via the following mapping:
 * <p>
 * "1" -> 'A'
 * <p>
 * "2" -> 'B'
 * <p>
 * ...
 * <p>
 * "25" -> 'Y'
 * <p>
 * "26" -> 'Z'
 * <p>
 * However, while decoding the message, you realize that there are many different ways you can decode the message because some codes are contained in other codes ("2" and "5" vs "25").
 * <p>
 * For example, "11106" can be decoded into:
 * <p>
 * "AAJF" with the grouping (1, 1, 10, 6)
 * "KJF" with the grouping (11, 10, 6)
 * The grouping (1, 11, 06) is invalid because "06" is not a valid code (only "6" is valid).
 * Note: there may be strings that are impossible to decode.
 * <p>
 * Given a string s containing only digits, return the number of ways to decode it. If the entire string cannot be decoded in any valid way, return 0.
 * <p>
 * The test cases are generated so that the answer fits in a 32-bit integer.
 */
public class m_5_DecodeWays {

    public int numDecodings(String s) {
        if (s.isEmpty() || s.charAt(0) == '0') {
            return 0;
        }

        int[] dp = new int[s.length()];
        dp[0] = 1;

        for (int i = 1; i < s.length(); i++) {

            int singleDigit = Integer.parseInt("" + s.charAt(i));
            if (isValidDigit(singleDigit)) {
                dp[i] = dp[i - 1];
            }

            int doubleDigit = Integer.parseInt("" + s.charAt(i - 1) + s.charAt(i));
            if (s.charAt(i - 1) != '0' && isValidDigit(doubleDigit)) {
                if (i > 1) {
                    dp[i] = dp[i] + dp[i - 2];
                } else {
                    dp[i] = dp[i] + 1;
                }
            }
        }

        return dp[s.length() - 1];
    }

    private boolean isValidDigit(int val) {
        return val >= 1 && val <= 26;
    }

    @Test
    void test0() {
        assertThat(numDecodings("12")).isEqualTo(2);
    }

    @Test
    void test1() {
        assertThat(numDecodings("226")).isEqualTo(3);
    }

    @Test
    void test2() {
        assertThat(numDecodings("06")).isEqualTo(0);
    }

    @Test
    void test3() {
        assertThat(numDecodings("10")).isEqualTo(1);
    }

    @Test
    void test4() {
        assertThat(numDecodings("2101")).isEqualTo(1);
    }

    @Test
    void test5() {
        assertThat(numDecodings("1123")).isEqualTo(5);
    }
}
