package org.art.playground.misc.algo.misc;

import static org.assertj.core.api.Assertions.assertThat;
import java.util.ArrayDeque;
import org.junit.jupiter.api.Test;

public class p5_ValidParentheses {

    public boolean isValid(String s) {
        ArrayDeque<Character> stack = new ArrayDeque<>();

        for (char c : s.toCharArray()) {
            if (c == '[' || c == '(' || c == '{') {
                stack.push(c);
            } else {
                if (stack.isEmpty()) {
                    return false;
                }
                char poped = stack.pop();
                if ((c == ']' && poped != '[')
                    || (c == ')' && poped != '(')
                    || (c == '}' && poped != '{')) {
                    return false;
                }
            }
        }

        if (!stack.isEmpty()) {
            return false;
        }

        return true;
    }

    @Test
    void test0() {
        assertThat(isValid("()")).isTrue();
    }

    @Test
    void test1() {
        assertThat(isValid("()[]{}")).isTrue();
    }

    @Test
    void test2() {
        assertThat(isValid("(]")).isFalse();
    }

    @Test
    void test3() {
        assertThat(isValid("([])")).isTrue();
    }
}
