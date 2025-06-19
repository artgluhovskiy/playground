package org.art.playground.misc.algo.arrays;

import org.junit.jupiter.api.Test;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Given an array of strings strs, group the anagrams together. You can return the answer in any order.
 */
public class m_8_GroupAnagrams {

    public List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> anagrams = new HashMap<>();

        for (String str : strs) {
            String key = getAnagramKey(str);
            anagrams.computeIfAbsent(key, k -> new ArrayList<>()).add(str);
        }

        return new ArrayList<>(anagrams.values());
    }

    private String getAnagramKey(String str) {
        char[] chars = str.toCharArray();
        Arrays.sort(chars);
        return new String(chars);
    }

    /**
     * Alternative implementation using character count as key.
     * More efficient for longer strings with limited character set.
     */
    public List<List<String>> groupAnagramsOptimized(String[] strs) {
        Map<String, List<String>> anagrams = new HashMap<>();

        for (String str : strs) {
            String key = getCharacterCountKey(str);
            anagrams.computeIfAbsent(key, k -> new ArrayList<>()).add(str);
        }

        return new ArrayList<>(anagrams.values());
    }

    private String getCharacterCountKey(String str) {
        int[] count = new int[26]; // Assuming lowercase letters only
        
        for (char c : str.toCharArray()) {
            count[c - 'a']++;
        }
        
        // Create a string representation of the count array
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 26; i++) {
            if (count[i] > 0) {
                sb.append((char)('a' + i)).append(count[i]);
            }
        }
        return sb.toString();
    }

    @Test
    void test0() {
        var input = new String[]{
            "eat", "tea", "tan", "ate", "nat", "bat"
        };

        var expected = List.of(
            List.of("tan", "nat"), List.of("bat"), List.of("eat", "tea", "ate")
        );

        var result = groupAnagrams(input);
        assertThat(result).hasSize(expected.size());
        assertThat(result).allMatch(group -> 
            expected.stream().anyMatch(expectedGroup -> 
                expectedGroup.size() == group.size() && 
                expectedGroup.containsAll(group)
            )
        );
    }

    @Test
    void testOptimized() {
        var input = new String[]{
            "eat", "tea", "tan", "ate", "nat", "bat"
        };

        var result = groupAnagramsOptimized(input);
        assertThat(result).hasSize(3);
        
        // Verify each group contains anagrams
        for (List<String> group : result) {
            if (group.size() > 1) {
                String first = group.get(0);
                assertThat(group).allMatch(str -> 
                    getAnagramKey(str).equals(getAnagramKey(first))
                );
            }
        }
    }
}
