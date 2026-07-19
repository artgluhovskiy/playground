package org.art.playground.misc.algo.strings;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * You are given a string s and an integer k.
 * You can choose any character of the string and change it to any other uppercase English character.
 * You can perform this operation at most k times.
 * <p>
 * Return the length of the longest substring containing the same letter you can get after performing the above operations.
 */
public class m_1_LongestRepeatingCharacterReplacement {

    public int characterReplacement(String s, int k) {
        int[] freqs = new int['Z' - 'A' + 1];

        int left = 0;
        int maxFreq = 0;
        int windowSize = 1;
        int maxWindowSize = 1;

        for (char c : s.toCharArray()) {
            int frequency = freqs['Z' - c] == 0 ? 1 : freqs['Z' - c] + 1;
            maxFreq = Math.max(frequency, maxFreq);
            freqs['Z' - c] = frequency;

            if (windowSize - maxFreq <= k) {
                maxWindowSize = Math.max(windowSize, maxWindowSize);
                windowSize++;
            } else {
                freqs['Z' - s.charAt(left)] -= 1;
                left++;
            }
        }

        return maxWindowSize;
    }

    @Test
    void test0() {
        assertThat(characterReplacement("ABAB", 2)).isEqualTo(4);
    }

    @Test
    void test1() {
        assertThat(characterReplacement("AABABBA", 1)).isEqualTo(4);
    }

    @Test
    void test2() {
        assertThat(characterReplacement("ABAABCCCC", 1)).isEqualTo(5);
    }

    @Test
    void test3() {
        assertThat(characterReplacement("ABAB", 0)).isEqualTo(1);
    }
}
