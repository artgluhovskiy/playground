package org.art.playground.misc.algo.strings;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Write a function to find the longest common prefix string amongst an array of strings.
 * If there is no common prefix, return an empty string "".
 */
public class e_8_LongestCommonPrefix {

    public String longestCommonPrefix(String[] strs) {
        String prefix = strs[0];

        for (int i = 1; i < strs.length; i++) {
            String str = strs[i];
            while (str.indexOf(prefix) != 0) {
                prefix = prefix.substring(0, prefix.length() - 1);
            }
        }

        return prefix;
    }

    @Test
    void test0() {
        String[] strs = {"flower", "flow", "flight"};

        assertThat(longestCommonPrefix(strs)).isEqualTo("fl");
    }

    @Test
    void test1() {
        String[] strs = {"dog", "racecar", "car"};

        assertThat(longestCommonPrefix(strs)).isEqualTo("");
    }

    @Test
    void test2() {
        String[] strs = {"a"};

        assertThat(longestCommonPrefix(strs)).isEqualTo("a");
    }

    @Test
    void test3() {
        String[] strs = {"ab", "a"};

        assertThat(longestCommonPrefix(strs)).isEqualTo("a");
    }
}
