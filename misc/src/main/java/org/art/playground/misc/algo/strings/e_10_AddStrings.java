package org.art.playground.misc.algo.strings;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Given two non-negative integers, num1 and num2 represented as string, return the sum of num1 and num2 as a string.
 * <p>
 * You must solve the problem without using any built-in library for handling large integers (such as BigInteger).
 * You must also not convert the inputs to integers directly.
 */
public class e_10_AddStrings {

    public String addStrings(String num1, String num2) {
        StringBuilder res = new StringBuilder();

        int pt1 = num1.length() - 1;
        int pt2 = num2.length() - 1;

        boolean carry = false;

        while (pt1 >= 0 || pt2 >= 0) {

            int sum = 0;

            if (pt1 >= 0) {
                int val1 = num1.charAt(pt1) - '0';
                sum = sum + val1;
                pt1--;
            }

            if (pt2 >= 0) {
                int val2 = num2.charAt(pt2) - '0';
                sum = sum + val2;
                pt2--;
            }

            if (carry) {
                sum = sum + 1;
            }

            carry = sum >= 10;

            int val = sum % 10;

            res.append(val);
        }

        if (carry) {
            res.append(1);
        }

        return res.reverse().toString();
    }

    @Test
    void test0() {
        assertThat(addStrings("11", "123")).isEqualTo("134");
    }

    @Test
    void test1() {
        assertThat(addStrings("456", "77")).isEqualTo("533");
    }

    @Test
    void test2() {
        assertThat(addStrings("0", "0")).isEqualTo("0");
    }

    @Test
    void test3() {
        assertThat(addStrings("1", "9")).isEqualTo("10");
    }
}
