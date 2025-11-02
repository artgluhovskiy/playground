package org.art.playground.misc.algo.design;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class e_3_LoggerRateLimiter {

    public static class Logger {

        public static final int COOLDOWN = 10;

        private final Map<String, Integer> msgStorage = new HashMap<>();

        public boolean shouldPrintMessage(int timestamp, String message) {
            Integer lastTimestamp = msgStorage.get(message);

            if (lastTimestamp != null && timestamp < lastTimestamp + COOLDOWN) {
                return false;
            }

            msgStorage.put(message, timestamp);
            return true;
        }
    }

    @Test
    void test0() {
        Logger logger = new Logger();
        assertThat(logger.shouldPrintMessage(1, "foo")).isTrue();
        assertThat(logger.shouldPrintMessage(2, "bar")).isTrue();
        assertThat(logger.shouldPrintMessage(3, "foo")).isFalse();
        assertThat(logger.shouldPrintMessage(8, "bar")).isFalse();
        assertThat(logger.shouldPrintMessage(10, "foo")).isFalse();
        assertThat(logger.shouldPrintMessage(11, "foo")).isTrue();
    }
}
