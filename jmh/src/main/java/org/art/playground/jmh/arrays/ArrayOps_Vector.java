package org.art.playground.jmh.arrays;

import jdk.incubator.vector.IntVector;
import jdk.incubator.vector.VectorMask;
import jdk.incubator.vector.VectorOperators;
import jdk.incubator.vector.VectorSpecies;

public class ArrayOps_Vector {

    private static final VectorSpecies<Integer> SPECIES = IntVector.SPECIES_PREFERRED;

    public int[] add(int[] arr1, int[] arr2) {
        int offset = SPECIES.length();
        int[] result = new int[arr1.length];

        for (int i = 0; i < arr1.length; i += offset) {
            var v1 = IntVector.fromArray(SPECIES, arr1, i);
            var v2 = IntVector.fromArray(SPECIES, arr2, i);
            v1.add(v2).intoArray(result, i);
        }
        return result;
    }

    public int find2(int[] arr, int value) {
        int offset = SPECIES.length();

        for (int i = 0; i < arr.length; i += offset) {

            var underTest = IntVector.fromArray(SPECIES, arr, i);

            VectorMask<Integer> mask = underTest.eq(value);

            int found = mask.firstTrue();
            if (found < offset) {
                return found;
            }
        }

        return -1;
    }

    public int find(int[] array, int target) {
        int length = array.length;
        int i = 0;

        // Process elements in vectorized chunks
        while (i <= length - SPECIES.length()) {
            // Load a vector from the array
            IntVector vector = IntVector.fromArray(SPECIES, array, i);

            // Compare each element in the vector to the target
            var mask = vector.compare(VectorOperators.EQ, target);

            // Check if any element matched
            if (mask.anyTrue()) {
                return i + mask.firstTrue();  // Return the first matching index
            }

            i += SPECIES.length(); // Move to the next chunk
        }

        // Process remaining elements
        for (; i < length; i++) {
            if (array[i] == target) {
                return i;
            }
        }

        return -1;
    }

    public int[][] mulMatrices(int[][] m1, int[][] m2) {
        int size = m1.length;
        int offset = SPECIES.length();

        int[][] result = new int[size][size];

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                result[i][j] = 0;
                for (int k = 0; k < size; k += offset) {
//                    result[i][j] += m1[i][k] * m2[k][j];

                    var v1 = IntVector.fromArray(SPECIES, m1[i], k);

                    int[] temp = new int[offset];
                    for (int l = 0; l < offset; l++) {
                        temp[l] = m2[k + l][j];
                    }

                    var v2 = IntVector.fromArray(SPECIES, temp, 0);
                    IntVector mul = v1.mul(v2);
                    result[i][j] += mul.reduceLanes(VectorOperators.ADD);
                }
            }
        }

        return result;
    }

    public int[][] mulMatricesV2(int[][] m1, int[][] m2) {
        int size = m1.length;
        int offset = SPECIES.length();
        int cacheSize = size / offset;

        IntVector[] rowVectorCache = new IntVector[cacheSize];

        int[][] result = new int[size][size];

        for (int i = 0; i < size; i++) {

            for (int n = 0, r = 0; n < size; n += offset, r++) {
                rowVectorCache[r] = IntVector.fromArray(SPECIES, m1[i], n);
            }


            for (int j = 0; j < size; j++) {
                result[i][j] = 0;
                for (int k = 0, m = 0; k < size; k += offset, m++) {
//                    result[i][j] += m1[i][k] * m2[k][j];

//                    var v1 = IntVector.fromArray(SPECIES, m1[i], k);
                    var v1 = rowVectorCache[m];

                    int[] temp = new int[offset];
                    for (int l = 0; l < offset; l++) {
                        temp[l] = m2[k + l][j];
                    }

                    var v2 = IntVector.fromArray(SPECIES, temp, 0);
                    IntVector mul = v1.mul(v2);
                    result[i][j] += mul.reduceLanes(VectorOperators.ADD);
                }
            }
        }

        return result;
    }
}
