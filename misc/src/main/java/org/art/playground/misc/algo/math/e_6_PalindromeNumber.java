package org.art.playground.misc.algo.math;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Given an integer x, return true if x is a palindrome, and false otherwise.
 */
public class e_6_PalindromeNumber {

    public boolean isPalindrome(int x) {
        if (x < 0) {
            return false;
        }
        
        if (x != 0 && x % 10 == 0) {
            return false;
        }

        int reversed = 0;
        
        // Reverse only half of the number
        while (x > reversed) {
            reversed = reversed * 10 + x % 10;
            x /= 10;
        }
        
        // For even number of digits: x == reversed
        // For odd number of digits: x == reversed / 10 (middle digit doesn't matter)
        return x == reversed || x == reversed / 10;
    }

    @Test
    void test0() {
        assertThat(isPalindrome(191)).isTrue();
    }

    @Test
    void test1() {
        assertThat(isPalindrome(10)).isFalse();
    }

    @Test
    void test2() {
        assertThat(isPalindrome(1)).isTrue();
    }

    @Test
    void test3() {
        assertThat(isPalindrome(1881)).isTrue();
    }

    @Test
    void test4() {
        assertThat(isPalindrome(0)).isTrue();
    }

    @Test
    void test5() {
        assertThat(isPalindrome(100)).isFalse();
    }

    @Test
    void test6() {
        assertThat(isPalindrome(12321)).isTrue();
    }

    @Test
    void test7() {
        assertThat(isPalindrome(123321)).isTrue();
    }

    @Test
    void test8() {
        assertThat(isPalindrome(12345)).isFalse();
    }
}
