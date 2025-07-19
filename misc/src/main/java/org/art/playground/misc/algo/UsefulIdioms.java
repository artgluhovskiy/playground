package org.art.playground.misc.algo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class UsefulIdioms {

    public static void main(String[] args) {

        // Idiom 1. Converting a string to a list of characters
        String str1 = "abcd";
        List<Character> chars1 = str1.chars()
            .mapToObj(ch -> (char) ch)
            .toList();

        // Idiom 2. Sort characters in a string
        String str2 = "sbsddf";
        char[] chars2 = str2.toCharArray();
        Arrays.sort(chars2);
        String strWithSortedChars = new String(chars2);

        // Idiom 3. Add to Map value to the List
        Map<Integer, List<Integer>> mapWithValueList = new HashMap<>();
        mapWithValueList.computeIfAbsent(1, _ -> new ArrayList<>()).add(2);

        // Idiom 4. Increment value in Map
        Map<Integer, Integer> mapWithIntValue = new HashMap<>();
        mapWithIntValue.merge(1, 1, Integer::sum);

        // Idiom 5. ASCI characters
        short asciCharSize = 122 - 97;              // lowercase English chars ASCII codes
        int[] asciCharArray = new int[asciCharSize + 1];
        // occurrences could be stored and calculated in such an array

        // Idiom 6. Encoding the direction in the 2-dim array. E.g. up, right, down, left
        // Check the problem: m_5_NumberOfIslands

    }
}
