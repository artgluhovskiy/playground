package org.art.playground.misc.algo.arrays;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Given a string s, return the longest palindromic substring in s.
 */
public class m_9_LongestPalindromicSubstring {

    public String longestPalindrome(String s) {
        if (s == null || s.length() < 2) {
            return s;
        }

        int start = 0;
        int maxLength = 1;

        for (int i = 0; i < s.length(); i++) {
            // Check odd length palindromes
            int len1 = expandAroundCenter(s, i, i);
            
            // Check even length palindromes
            int len2 = expandAroundCenter(s, i, i + 1);
            
            // Get the maximum length from both checks
            int currentMaxLength = Math.max(len1, len2);
            
            // Update if we found a longer palindrome
            if (currentMaxLength > maxLength) {
                start = i - (currentMaxLength - 1) / 2;
                maxLength = currentMaxLength;
            }
        }

        return s.substring(start, start + maxLength);
    }

    private int expandAroundCenter(String s, int left, int right) {
        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            left--;
            right++;
        }
        return right - left - 1;
    }

    @Test
    void test0() {
        assertThat(longestPalindrome("babad")).isEqualTo("bab");
    }

    @Test
    void test1() {
        assertThat(longestPalindrome("cbbd")).isEqualTo("bb");
    }

    @Test
    void test2() {
        assertThat(longestPalindrome("a")).isEqualTo("a");
    }

    @Test
    void test3() {
        assertThat(longestPalindrome("ac")).isEqualTo("a");
    }
}
