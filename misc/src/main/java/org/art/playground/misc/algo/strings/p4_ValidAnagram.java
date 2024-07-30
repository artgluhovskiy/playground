package org.art.playground.misc.algo.strings;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Given two strings s and t, return true if t is an anagram of s, and false otherwise.
 * An Anagram is a word or phrase formed by rearranging the letters of a different word or phrase,
 * typically using all the original letters exactly once.
 */
public class p4_ValidAnagram {

    public boolean isAnagram(String s, String t) {
        short size = 122 - 97;              // lowercase English chars ASCII codes
        int[] occurrences = new int[size + 1];

        for (char c : s.toCharArray()) {
            occurrences[c - 97] += 1;
        }

        for (char c : t.toCharArray()) {
            occurrences[c - 97] -= 1;
        }

        for (int occ : occurrences) {
            if (occ != 0) {
                return false;
            }
        }

        return true;
    }

    @Test
    void test0() {
        String s = "anagram";
        String t = "nagaram";

        assertThat(isAnagram(s, t)).isTrue();
    }

    @Test
    void test1() {
        String s = "rat";
        String t = "car";

        assertThat(isAnagram(s, t)).isFalse();
    }
}
