package org.art.playground.misc.algo.strings;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Given two strings needle and haystack, return the index of the first occurrence
 * of needle in haystack, or -1 if needle is not part of haystack.
 */
public class e_7_ImplementStrStr {

    public int strStr(String haystack, String needle) {
        if (haystack.length() < needle.length()) {
            return -1;
        }

        char[] hayChars = haystack.toCharArray();
        char[] needleChars = needle.toCharArray();

        for (int i = 0; i < hayChars.length - needleChars.length + 1; i++) {
            for (int j = 0; j < needleChars.length; j++) {
                char needleChar = needleChars[j];
                char hayChar = hayChars[i + j];
                if (hayChar != needleChar) {
                    break;
                }
                if (j == needleChars.length - 1) {
                    return i;
                }
            }
        }

        return -1;
    }

    @Test
    void test0() {
        String s1 = "sadbutsad";
        String s2 = "sad";

        assertThat(strStr(s1, s2)).isEqualTo(0);
    }

    @Test
    void test1() {
        String s1 = "leetcode";
        String s2 = "leeto";

        assertThat(strStr(s1, s2)).isEqualTo(-1);
    }

    @Test
    void test2() {
        String s1 = "leetleetlees";
        String s2 = "leetlees";

        assertThat(strStr(s1, s2)).isEqualTo(4);
    }
}
