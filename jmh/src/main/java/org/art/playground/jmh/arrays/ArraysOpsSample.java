package org.art.playground.jmh.arrays;

import static org.art.playground.jmh.utils.ArrayUtils.generateRandomMatrix;
import static org.art.playground.jmh.utils.ArrayUtils.printArray;
import static org.assertj.core.api.Assertions.assertThat;

public class ArraysOpsSample {

    private static final ArrayOps_Scalar OPS_SCALAR = new ArrayOps_Scalar();

    private static final ArrayOps_Vector OPS_VECTOR = new ArrayOps_Vector();

    public static void main(String[] args) {
        int size = 16;

//        int m1[][] = {
//            {1, 1, 1, 1, 1, 1, 1, 1},
//            {2, 2, 2, 2, 2, 2, 2, 2},
//            {3, 3, 3, 3, 3, 3, 3, 3},
//            {4, 4, 4, 4, 4, 4, 4, 4},
//            {5, 5, 5, 5, 5, 5, 5, 5},
//            {6, 6, 6, 6, 6, 6, 6, 6},
//            {7, 7, 7, 7, 7, 7, 7, 7},
//            {8, 8, 8, 8, 8, 8, 8, 8},
//        };
//
//        int m2[][] = {
//            {1, 1, 1, 1, 1, 1, 1, 1},
//            {2, 2, 2, 2, 2, 2, 2, 2},
//            {3, 3, 3, 3, 3, 3, 3, 3},
//            {4, 4, 4, 4, 4, 4, 4, 4},
//            {5, 5, 5, 5, 5, 5, 5, 5},
//            {6, 6, 6, 6, 6, 6, 6, 6},
//            {7, 7, 7, 7, 7, 7, 7, 7},
//            {8, 8, 8, 8, 8, 8, 8, 8},
//        };

        int[][] m1 = generateRandomMatrix(size);
        int[][] m2 = generateRandomMatrix(size);

        int[][] res1 = OPS_SCALAR.mulMatrices(m1, m2);
        int[][] res2 = OPS_VECTOR.mulMatricesV2(m1, m2);

        System.out.println("SCALAR: result:");
        printArray(res1);

        System.out.println("VECTOR: result:");
        printArray(res2);

        assertThat(res1).isDeepEqualTo(res2);
    }
}
