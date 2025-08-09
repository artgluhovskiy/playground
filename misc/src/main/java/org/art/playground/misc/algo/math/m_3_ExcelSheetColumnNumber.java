package org.art.playground.misc.algo.math;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Given a string columnTitle that represents the column title as appears in an Excel sheet,
 * return its corresponding column number.
 * <p>
 * Excel column titles follow base-26 numbering where:
 * - A = 1, B = 2, ..., Z = 26
 * - AA = 27, AB = 28, ..., AZ = 52
 * - BA = 53, BB = 54, ..., BZ = 78
 * - etc.
 */
public class m_3_ExcelSheetColumnNumber {

    /**
     * Converts Excel column title to column number.
     *
     * @param columnTitle the column title (e.g., "A", "AB", "ZY")
     * @return the corresponding column number
     */
    public int titleToNumber(String columnTitle) {
        if (columnTitle == null || columnTitle.isEmpty()) {
            return 0;
        }

        int result = 0;
        // Iterate from left to right, multiplying by 26 for each position
        for (char c : columnTitle.toCharArray()) {
            // Convert character to 1-26 range (A=1, B=2, ..., Z=26)
            int value = c - 'A' + 1;
            result = result * 26 + value;
        }

        return result;
    }

    @Test
    void test0() {
        assertThat(titleToNumber("A")).isEqualTo(1);
        assertThat(titleToNumber("AB")).isEqualTo(28);
        assertThat(titleToNumber("ZY")).isEqualTo(701);
    }

    @Test
    void testAdditionalCases() {
        assertThat(titleToNumber("Z")).isEqualTo(26);
        assertThat(titleToNumber("AA")).isEqualTo(27);
        assertThat(titleToNumber("AZ")).isEqualTo(52);
        assertThat(titleToNumber("BA")).isEqualTo(53);
        assertThat(titleToNumber("ZZ")).isEqualTo(702);
        assertThat(titleToNumber("AAA")).isEqualTo(703);
    }

    @Test
    void testEdgeCases() {
        assertThat(titleToNumber("")).isEqualTo(0);
        assertThat(titleToNumber(null)).isEqualTo(0);
    }
}
