package org.art.playground.misc.algo.strings;

import org.junit.jupiter.api.Test;

import static java.lang.Character.isLetterOrDigit;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * A phrase is a palindrome if, after converting all uppercase letters into lowercase letters
 * and removing all non-alphanumeric characters, it reads the same forward and backward.
 * Alphanumeric characters include letters and numbers.
 * Given a string s, return true if it is a palindrome, or false otherwise.
 */
public class p5_ValidPalindrom {

    public boolean isPalindrome(String s) {
        char[] chars = s.toCharArray();

        int a = 0;
        int b = chars.length - 1;

        while (a < b) {
            char first = chars[a];
            char second = chars[b];

            if (!isLetterOrDigit(first)) {
                a++;
                continue;
            }

            if (!isLetterOrDigit(second)) {
                b--;
                continue;
            }

            if (Character.toLowerCase(first) != Character.toLowerCase(second)) {
                return false;
            }

            a++;
            b--;
        }

        return true;
    }

    @Test
    void test0() {
        String s = "A man, a plan, a canal: Panama";

        assertThat(isPalindrome(s)).isTrue();
    }

    @Test
    void test1() {
        String s = "race a car";

        assertThat(isPalindrome(s)).isFalse();
    }

    @Test
    void test2() {
        String s = " ";

        assertThat(isPalindrome(s)).isTrue();
    }

    @Test
    void test3() {
        String s = "0P";

        assertThat(isPalindrome(s)).isFalse();
    }
}
