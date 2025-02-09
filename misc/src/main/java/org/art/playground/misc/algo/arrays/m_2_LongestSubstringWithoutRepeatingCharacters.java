package org.art.playground.misc.algo.arrays;

import static org.assertj.core.api.Assertions.assertThat;
import java.util.HashSet;
import java.util.Set;
import org.junit.jupiter.api.Test;

/**
 * Given a string s, find the length of the longest substring without repeating characters.
 */
@SuppressWarnings("java:S5960")
public class m_2_LongestSubstringWithoutRepeatingCharacters {

    public int lengthOfLongestSubstring(String s) {
        int first = 0;
        int second = 0;
        int max = 0;

        Set<Character> charBuffer = new HashSet<>();

        while (second < s.length()) {
            char ch = s.charAt(second);
            if (!charBuffer.contains(ch)) {
                charBuffer.add(ch);
                max = Math.max(charBuffer.size(), max);
                second++;
            } else {
                charBuffer.remove(s.charAt(first));
                first++;
            }
        }

        return max;
    }

    @Test
    void test0() {
        assertThat(lengthOfLongestSubstring("abcabcbb")).isEqualTo(3);
    }

    @Test
    void test1() {
        assertThat(lengthOfLongestSubstring("bbbbb")).isEqualTo(1);
    }

    @Test
    void test2() {
        assertThat(lengthOfLongestSubstring("pwwkew")).isEqualTo(3);
    }

    @Test
    void test3() {
        assertThat(lengthOfLongestSubstring("abcdeccfgh")).isEqualTo(5);
    }
}
