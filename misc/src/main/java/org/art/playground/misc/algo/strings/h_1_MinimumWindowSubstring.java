package org.art.playground.misc.algo.strings;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Given two strings s and t of lengths m and n respectively, return the minimum window substring
 * of s such that every character in t (including duplicates) is included in the window.
 * If there is no such substring, return the empty string "".
 * The testcases will be generated such that the answer is unique.
 */
public class h_1_MinimumWindowSubstring {

    public String minWindow(String s, String t) {
        // Sliding window on s: expand right until the window covers t, then shrink left while it still covers t.
        // Track the shortest valid window; both pointers only move forward → O(|s| + |t|).

        if (t.isEmpty()) {
            return "";
        }

        // Required frequency of each character in t.
        Map<Character, Integer> need = new HashMap<>();
        t.chars().forEach(ch -> need.merge((char) ch, 1, Integer::sum));

        // required = distinct chars in t that must be "satisfied".
        // formed = how many of those chars currently have window count == need count (exact match, not just ≥).
        int required = need.size();
        int formed = 0;

        int left = 0;
        int right = 0;

        boolean found = false;
        int bestLeft = 0;
        int bestRight = 0;
        int bestSeenWindowSize = Integer.MAX_VALUE;

        // Counts in the current window [left, right] — only for characters that appear in need.
        Map<Character, Integer> window = new HashMap<>();

        while (right < s.length()) {
            // --- Expand: include s[right] in the window ---
            char c = s.charAt(right);
            if (need.containsKey(c)) {
                window.merge(c, 1, Integer::sum);
                // Just reached the exact count t requires for this character.
                if (window.get(c).equals(need.get(c))) {
                    formed++;
                }
            }

            // --- Shrink while valid: window contains all of t with correct multiplicities ---
            while (formed == required) {
                int length = right - left + 1; // inclusive bounds; right not advanced yet this iteration
                if (length < bestSeenWindowSize) {
                    bestSeenWindowSize = length;
                    bestRight = right;
                    bestLeft = left;
                    found = true;
                }

                char leftChar = s.charAt(left);
                if (need.containsKey(leftChar)) {
                    // About to drop below the required count for this character.
                    if (window.get(leftChar).equals(need.get(leftChar))) {
                        formed--;
                    }
                    decrement(window, leftChar);
                }
                // Chars in s but not in t are skipped in window; they do not affect formed.
                left++;
            }

            right++;
        }

        return found ? s.substring(bestLeft, bestRight + 1) : "";
    }

    /** Decrement count for c in window; remove key when count hits zero. */
    private static void decrement(Map<Character, Integer> counts, char c) {
        int next = counts.get(c) - 1;
        if (next == 0) {
            counts.remove(c);
        } else {
            counts.put(c, next);
        }
    }

    @Test
    void test0() {
        assertThat(minWindow("ADOBECODEBANC", "ABC"))
            .isEqualTo("BANC");
    }

    @Test
    void test1() {
        assertThat(minWindow("a", "a"))
            .isEqualTo("a");
    }

    @Test
    void test2() {
        assertThat(minWindow("a", "aa"))
            .isEqualTo("");
    }
}
