package org.art.playground.misc.algo.math;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Given a roman numeral, convert it to an integer.
 */
public class p4_RomanToInteger {

    private static final Map<String, Integer> DICT = new HashMap<>();

    static {
        DICT.put("I", 1);
        DICT.put("V", 5);
        DICT.put("X", 10);
        DICT.put("L", 50);
        DICT.put("C", 100);
        DICT.put("D", 500);
        DICT.put("M", 1000);
    }

    public int romanToInt(String s) {
        int result = 0;
        int length = s.length();

        for (int i = 0; i < length; i++) {
            if (i + 1 < length) {
                int first = DICT.get(String.valueOf(s.charAt(i)));
                int second = DICT.get(String.valueOf(s.charAt(i + 1)));
                if (first < second) {
                    result -= first;
                    continue;
                }
            }
            result += DICT.get(String.valueOf(s.charAt(i)));
        }

        return result;
    }

    @Test
    void test0() {
        assertThat(romanToInt("III")).isEqualTo(3);
    }

    @Test
    void test1() {
        assertThat(romanToInt("LVIII")).isEqualTo(58);
    }

    @Test
    void test2() {
        assertThat(romanToInt("MCMXCIV")).isEqualTo(1994);
    }
}
