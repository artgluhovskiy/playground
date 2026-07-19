package org.art.playground.misc.algo.strings;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Given a string s, return the number of palindromic substrings in it.
 * A string is a palindrome when it reads the same backward as forward.
 * A substring is a contiguous sequence of characters within the string.
 */
public class m_2_PalindromicSubstrings {

    public int countSubstrings(String s) {
        int count = 0;
        for (int i = 0; i < s.length(); i++) {
            count += countAround(s, i, i);
            count += countAround(s, i, i + 1);
        }
        return count;
    }

    private int countAround(String s, int left, int right) {
        int count = 0;
        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            count++;
            left--;
            right++;
        }
        return count;
    }

    @Test
    void test0() {
        assertThat(countSubstrings("abc")).isEqualTo(3);
    }

    @Test
    void test1() {
        assertThat(countSubstrings("aaa")).isEqualTo(6);
    }
}
