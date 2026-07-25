package org.art.playground.misc.algo.strings;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Design an algorithm to encode a list of strings to a string.
 * The encoded string is then sent over the network and is decoded back to the original list of strings.
 */
public class m_3_EncodeAndDecodeStrings {

    private static final String DELIM = "#";

    public String encode(List<String> strs) {
        StringBuilder sb = new StringBuilder();

        for (String str : strs) {
            sb.append(str.length())
                .append(DELIM)
                .append(str);
        }

        return sb.toString();
    }

    public List<String> decode(String str) {
        String currentStr = str;

        List<String> res = new ArrayList<>();

        while (!currentStr.isEmpty()) {
            int delimIdx = currentStr.indexOf(DELIM);

            if (delimIdx == -1) {
                break;
            }

            int length = Integer.parseInt(currentStr.substring(0, delimIdx));
            String singleStr = currentStr.substring(delimIdx + 1, delimIdx + 1 + length);
            res.add(singleStr);

            currentStr = currentStr.substring(delimIdx + 1 + length);
        }

        return res;
    }

    @Test
    void test0() {
        assertThat(decode(encode(List.of("Hello", "World"))))
            .isEqualTo(List.of("Hello", "World"));
    }

    @Test
    void test1() {
        assertThat(decode(encode(List.of(""))))
            .isEqualTo(List.of(""));
    }

    @Test
    void test2() {
        assertThat(decode(encode(List.of("xxxxxxxxxx", "y"))))
            .isEqualTo(List.of("xxxxxxxxxx", "y"));
    }

    @Test
    void test3() {
        assertThat(decode(encode(List.of("a#b", "##"))))
            .isEqualTo(List.of("a#b", "##"));
    }

    @Test
    void test4() {
        assertThat(decode(encode(List.of("", "", "a"))))
            .isEqualTo(List.of("", "", "a"));
    }

    @Test
    void test5() {
        assertThat(decode(encode(List.of())))
            .isEqualTo(List.of());
    }
}
