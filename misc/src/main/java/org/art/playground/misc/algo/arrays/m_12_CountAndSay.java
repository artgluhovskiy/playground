package org.art.playground.misc.algo.arrays;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * The count-and-say sequence is a sequence of digit strings defined by the recursive formula:
 * <p>
 * countAndSay(1) = "1"
 * countAndSay(n) is the run-length encoding of countAndSay(n - 1).
 * Given a positive integer n, return the nth element of the count-and-say sequence.
 */
public class m_12_CountAndSay {

    public String countAndSay(int n) {
        if (n == 1) {
            return "1";
        }

        String previous = countAndSay(n - 1);
        return encodeRunLength(previous);
    }

    /**
     * Performs run-length encoding on a string of digits.
     * For example: "1211" becomes "111221" (1 one, 1 two, 2 ones)
     */
    private String encodeRunLength(String digits) {
        if (digits.isEmpty()) {
            return "";
        }

        StringBuilder result = new StringBuilder();
        char currentDigit = digits.charAt(0);
        int count = 1;

        for (int i = 1; i < digits.length(); i++) {
            char digit = digits.charAt(i);
            
            if (digit == currentDigit) {
                count++;
            } else {
                // Append count and digit for the previous run
                result.append(count).append(currentDigit);
                currentDigit = digit;
                count = 1;
            }
        }

        // Don't forget the last run
        result.append(count).append(currentDigit);
        
        return result.toString();
    }

    @Test
    void test0() {
        assertThat(countAndSay(4))
            .isEqualTo("1211");
    }

    @Test
    void test1() {
        assertThat(countAndSay(1))
            .isEqualTo("1");
    }

    @Test
    void test2() {
        assertThat(countAndSay(2))
            .isEqualTo("11");
    }

    @Test
    void test3() {
        assertThat(countAndSay(3))
            .isEqualTo("21");
    }

    @Test
    void test5() {
        assertThat(countAndSay(5))
            .isEqualTo("111221");
    }
}
