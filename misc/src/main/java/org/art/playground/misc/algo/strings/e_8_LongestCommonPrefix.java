package org.art.playground.misc.algo.strings;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Write a function to find the longest common prefix string amongst an array of strings.
 * If there is no common prefix, return an empty string "".
 */
public class e_8_LongestCommonPrefix {

    public String longestCommonPrefix(String[] strs) {
        if(strs.length == 1) {
            return strs[0];
        }

        String pref = strs[0];

        for(int i = 1; i < strs.length; i++) {
            String str = strs[i];
            pref = findCommonPref(pref, str);
        }

        return pref;
    }

    private String findCommonPref(String s1, String s2) {
        int idx = 0;

        for(int i = 0; i < Math.min(s1.length(), s2.length()); i++) {
            if (s1.charAt(i) == s2.charAt(i)) {
                idx++;
            } else {
                break;
            }
        }

        return s1.substring(0, idx);
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
