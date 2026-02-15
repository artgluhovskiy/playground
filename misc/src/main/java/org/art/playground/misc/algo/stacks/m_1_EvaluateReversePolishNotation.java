package org.art.playground.misc.algo.stacks;

import org.junit.jupiter.api.Test;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * You are given an array of strings tokens that represents an arithmetic expression in a Reverse Polish Notation.
 * <p>
 * Evaluate the expression. Return an integer that represents the value of the expression.
 * <p>
 * Note that:
 * The valid operators are '+', '-', '*', and '/'.
 * Each operand may be an integer or another expression.
 * The division between two integers always truncates toward zero.
 * There will not be any division by zero.
 * The input represents a valid arithmetic expression in a reverse polish notation.
 * The answer and all the intermediate calculations can be represented in a 32-bit integer.
 */
public class m_1_EvaluateReversePolishNotation {

    private static final Set<String> OPS = Set.of("+", "-", "*", "/");

    public int evalRPN(String[] tokens) {
        if (tokens.length == 1) {
            return Integer.parseInt(tokens[0]);
        }

        Deque<String> stack = new ArrayDeque<>();
        String result = null;

        for (String token : tokens) {
            if (OPS.contains(token)) {
                String op2 = stack.pop();
                String op1 = stack.pop();
                String localRes = performOp(op1, op2, token);
                stack.push(localRes);
                result = localRes;
            } else {
                stack.push(token);
            }
        }

        return Integer.parseInt(result);
    }

    private String performOp(String op1, String op2, String op) {
        int op1Int = Integer.parseInt(op1);
        int op2Int = Integer.parseInt(op2);

        int localRes = switch (op) {
            case "+" -> op1Int + op2Int;
            case "-" -> op1Int - op2Int;
            case "*" -> op1Int * op2Int;
            default -> op1Int / op2Int;
        };

        return "" + localRes;
    }

    @Test
    void test0() {
        String[] input = new String[]{"2", "1", "+", "3", "*"};
        assertThat(evalRPN(input)).isEqualTo(9);
    }

    @Test
    void test1() {
        String[] input = new String[]{"4", "13", "5", "/", "+"};
        assertThat(evalRPN(input)).isEqualTo(6);
    }

    @Test
    void test2() {
        String[] input = new String[]{"10", "6", "9", "3", "+", "-11", "*", "/", "*", "17", "+", "5", "+"};
        assertThat(evalRPN(input)).isEqualTo(22);
    }

}
