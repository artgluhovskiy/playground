package org.art.playground.misc.algo.strings;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Implement the myAtoi(string s) function, which converts a string to a 32-bit signed integer.
 * The algorithm for myAtoi(string s) is as follows:
 * Whitespace: Ignore any leading whitespace (" ").
 * Signedness: Determine the sign by checking if the next character is '-' or '+',
 * assuming positivity is neither present.
 * Conversion: Read the integer by skipping leading zeros until a non-digit character
 * is encountered or the end of the string is reached. If no digits were read, then the result is 0.
 * Rounding: If the integer is out of the 32-bit signed integer range [-231, 231 - 1],
 * then round the integer to remain in the range.
 * Specifically, integers less than -231 should be rounded to -231, and integers greater than 231 - 1
 * should be rounded to 231 - 1.
 * Return the integer as the final result.
 */
public class e_6_StringToIntegerAtoi {

    public int myAtoi(String s) {
        char[] chars = s.toCharArray();
        int p = 0;
        boolean isPositive = true;

        // Searching for sign and first digit
        while (p < chars.length) {
            char c = chars[p];

            if (c == ' ') {
                p++;
                continue;
            }

            if (Character.isLetter(c) || c == '.') {
                return 0;
            }

            if (c == '+') {
                p++;
                isPositive = true;
                break;
            }

            if (c == '-') {
                p++;
                isPositive = false;
                break;
            }

            if (c >= '0' && c <= '9') {
                isPositive = true;
                break;
            }

            p++;
        }

        // Searching for the first digit index
        while (p < chars.length) {
            char c = chars[p];

            if (!Character.isDigit(c)) {
                return 0;
            }

            if (c == '0') {
                p++;
                continue;
            }

            if (c > '0' && c <= '9') {
                break;
            }

            p++;
        }

        // Searching for the result
        long result = 0;
        while (p < chars.length) {
            char c = chars[p];

            if (c >= '0' && c <= '9') {
                result = result * 10 + (c - 48);

                if (isPositive && result > Integer.MAX_VALUE) {
                    return Integer.MAX_VALUE;
                }
                if (!isPositive && -1 * result < Integer.MIN_VALUE) {
                    return Integer.MIN_VALUE;
                }
            } else {
                break;
            }

            p++;
        }

        return isPositive ? (int) result : (int) -result;
    }

    @Test
    void test0() {
        String s = "42";

        assertThat(myAtoi(s)).isEqualTo(42);
    }

    @Test
    void test1() {
        String s = " -042";

        assertThat(myAtoi(s)).isEqualTo(-42);
    }

    @Test
    void test2() {
        String s = "1337c0d3";

        assertThat(myAtoi(s)).isEqualTo(1337);
    }

    @Test
    void test3() {
        String s = "0-1";

        assertThat(myAtoi(s)).isZero();
    }

    @Test
    void test4() {
        String s = "words and 987";

        assertThat(myAtoi(s)).isZero();
    }

    @Test
    void test5() {
        String s = ".1";

        assertThat(myAtoi(s)).isZero();
    }

    @Test
    void test6() {
        String s = "2147483648";

        assertThat(myAtoi(s)).isEqualTo(2147483647);
    }

    @Test
    void test7() {
        String s = "2147483646";

        assertThat(myAtoi(s)).isEqualTo(2147483646);
    }
}
