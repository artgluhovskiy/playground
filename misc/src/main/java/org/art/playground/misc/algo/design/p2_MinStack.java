package org.art.playground.misc.algo.design;

import org.junit.jupiter.api.Test;

/**
 * Design a stack that supports push, pop, top, and retrieving the minimum element in constant time.
 */
public class p2_MinStack {

    private Node tail;

    public p2_MinStack() {
    }

    public void push(int val) {
        int min;
        if (tail == null || val < tail.min) {
            min = val;
        } else {
            min = tail.min;
        }
        tail = new Node(min, val, tail);
    }

    public void pop() {
        tail = tail.next;
    }

    public int top() {
        return tail.value;
    }

    public int getMin() {
        return tail.min;
    }

    private static class Node {

        public Node(int min, int value, Node next) {
            this.min = min;
            this.value = value;
            this.next = next;
        }

        private final int min;

        private final int value;

        private final Node next;
    }

    @Test
    void test() {
        p2_MinStack minStack = new p2_MinStack();
        minStack.push(-2);
        minStack.push(0);
        minStack.push(-3);
        System.out.println(minStack.getMin());      // return -3
        minStack.pop();
        System.out.println(minStack.top());         // return 0
        System.out.println(minStack.getMin());      // return -2
    }

}
