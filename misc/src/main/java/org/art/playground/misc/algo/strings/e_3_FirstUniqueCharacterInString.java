package org.art.playground.misc.algo.strings;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Given a string s, find the first non-repeating character in it and return its index.
 * If it does not exist, return -1.
 */
public class e_3_FirstUniqueCharacterInString {

    public int firstUniqChar(String s) {
        short size = 122 - 97;              // lowercase English chars ASCII codes
        int[] occurrences = new int[size + 1];

        char[] charArray = s.toCharArray();
        for (char ch : charArray) {
            short effectiveCh = (short) (ch - 97);
            occurrences[effectiveCh] += 1;
        }

        for (int i = 0; i < charArray.length; i++) {
            char ch = charArray[i];
            short effectiveCh = (short) (ch - 97);
            if (occurrences[effectiveCh] == 1) {
                return i;
            }
        }

        return -1;
    }

    @Test
    void test0() {
        var input = "leetcode";

        assertThat(firstUniqChar(input)).isEqualTo(0);
    }

    @Test
    void test1() {
        var input = "loveleetcode";

        assertThat(firstUniqChar(input)).isEqualTo(2);
    }

    @Test
    void test2() {
        var input = "aabb";

        assertThat(firstUniqChar(input)).isEqualTo(-1);
    }

    @Test
    void test3() {
        var input = "yekbsxznylrwamcaugrqrurvpqybkpfzwbqiysrdnrsnbftvrnszfjbkbmrctjizkjqoxqzddyfnavnhqeblfmzqgsjflghaulbadwqsyuetdelujphmlgtmkoaoijypvcajctbaumeromgejtewbwqptotrorephegyobbstvywljboeihdliknluqdpgampjyjpinxhhqexoctysfdciqjbzilnodzoihihusxluqoayenluziobxiodrfdkinkzzozmxfezfvllpdvogqqtquwcsijwachefspywdgsohqtlquhnoecccgbkrzqcprzmwvygqwddnehhi";

        assertThat(firstUniqChar(input)).isEqualTo(-1);
    }
}
