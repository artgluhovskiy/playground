package org.art.playground.misc.algo.math;

import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

public class e_5_HappyNumber {

    public boolean isHappy(int n) {
        int slow = n;
        int fast = n;

        do {
            slow = sumSquared(slow);
            fast = sumSquared(sumSquared(fast));

            if (slow == 1 || fast == 1) {
                return true;
            }

        } while (slow != fast);

        return false;
    }

    public boolean isHappyV2(int n) {
        Set<Integer> visited = new HashSet<>();

        while (!visited.contains(n)) {
            visited.add(n);

            n = sumSquared(n);
            if (n == 1) {
                return true;
            }
        }

        return false;
    }

    private int sumSquared(int n) {
        int sum = 0;
        while (n > 0) {
            int val = n % 10;
            sum = sum + val * val;
            n = n / 10;
        }
        return sum;
    }

    @Test
    void test0() {
        assertThat(isHappy(19)).isTrue();
    }

    @Test
    void test1() {
        assertThat(isHappy(2)).isFalse();
    }
}

