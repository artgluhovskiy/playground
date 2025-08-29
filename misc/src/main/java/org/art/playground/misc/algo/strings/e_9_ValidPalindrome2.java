package org.art.playground.misc.algo.strings;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Given a string s, return true if the s can be palindrome
 * after deleting at most one character from it.
 */
public class e_9_ValidPalindrome2 {

    public boolean validPalindrome(String s) {
        if (s.length() < 2) {
            return true;
        }

        int leftIdx = 0;
        int rightIdx = s.length() - 1;

        while (rightIdx - leftIdx > 0) {

            if (s.charAt(leftIdx) == s.charAt(rightIdx)) {
                leftIdx++;
                rightIdx--;
            } else {
                return isValidPalindrome(leftIdx + 1, rightIdx, s)
                    || isValidPalindrome(leftIdx, rightIdx - 1, s);
            }
        }

        return true;
    }

    private boolean isValidPalindrome(int left, int right, String s) {
        while (left < right) {
            if (s.charAt(left) != s.charAt(right)) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }

    @Test
    void test0() {
        assertThat(validPalindrome("aba")).isTrue();
    }

    @Test
    void test1() {
        assertThat(validPalindrome("abca")).isTrue();
    }

    @Test
    void test2() {
        assertThat(validPalindrome("abc")).isFalse();
    }

    @Test
    void test3() {
        assertThat(validPalindrome("abba")).isTrue();
    }

    @Test
    void test4() {
        assertThat(validPalindrome("cupucu")).isTrue();
    }
}
