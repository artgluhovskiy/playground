package org.art.playground.misc.algo.backtracking;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Given n pairs of parentheses, write a function to generate all combinations of well-formed parentheses.
 */
public class m_1_GenerateParentheses {

    public List<String> generateParenthesis(int n) {
        List<String> res = new ArrayList<>();
        backtrack(res, "", 0, 0, n);
        return res;
    }

    private void backtrack(List<String> res, String current, int opened, int closed, int max) {
        if (current.length() == 2 * max) {
            res.add(current);
        }

        if (opened < max) {
            backtrack(res, current + "(", opened + 1, closed, max);
        }
        if (closed < opened) {
            backtrack(res, current + ")", opened, closed + 1, max);
        }
    }

    @Test
    void test0() {
        assertThat(generateParenthesis(3))
            .containsExactlyInAnyOrderElementsOf(
                List.of("((()))", "(()())", "(())()", "()(())", "()()()"));
    }

    @Test
    void test1() {
        assertThat(generateParenthesis(1))
            .isEqualTo(List.of("()"));
    }
}
