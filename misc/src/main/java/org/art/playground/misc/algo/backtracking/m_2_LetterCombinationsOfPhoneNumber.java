package org.art.playground.misc.algo.backtracking;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Given a string containing digits from 2-9 inclusive,
 * return all possible letter combinations that the number could represent.
 * Return the answer in any order.
 */
public class m_2_LetterCombinationsOfPhoneNumber {

    private static final Map<Character, List<String>> MAP = new HashMap<>();

    static {
        MAP.put('2', List.of("a", "b", "c"));
        MAP.put('3', List.of("d", "e", "f"));
        MAP.put('4', List.of("g", "h", "i"));
        MAP.put('5', List.of("j", "k", "l"));
        MAP.put('6', List.of("m", "n", "o"));
        MAP.put('7', List.of("p", "q", "r", "s"));
        MAP.put('8', List.of("t", "u", "v"));
        MAP.put('9', List.of("w", "x", "y", "z"));
    }

    public List<String> letterCombinations(String digits) {
        if (digits.isEmpty()) {
            return List.of();
        }

        List<String> result = new ArrayList<>();

        for (int i = digits.length() - 1; i >= 0; i--) {
            char currentDigit = digits.charAt(i);
            List<String> chars = MAP.get(currentDigit);
            result = prependChars(chars, result);
        }

        return result;
    }

    private List<String> prependChars(List<String> chars, List<String> strings) {
        if (strings.isEmpty()) {
            return chars;
        }

        List<String> prependedStrings = new ArrayList<>();
        for (String string : strings) {
            for (String ch : chars) {
                prependedStrings.add(ch + string);
            }
        }

        return prependedStrings;
    }

    @Test
    void test0() {
        var input = "23";

        assertThat(letterCombinations(input))
            .containsExactlyInAnyOrderElementsOf(List.of("ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"));
    }

    @Test
    void test1() {
        var input = "2";

        assertThat(letterCombinations(input))
            .containsExactlyInAnyOrderElementsOf(List.of("a", "b", "c"));
    }

    @Test
    void test2() {
        var input = "";

        assertThat(letterCombinations(input))
            .isEmpty();
    }
}
