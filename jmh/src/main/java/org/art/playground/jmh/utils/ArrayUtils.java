package org.art.playground.jmh.utils;

import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;

import lombok.experimental.UtilityClass;

@UtilityClass
public class ArrayUtils {

    private static final ThreadLocalRandom TLR = ThreadLocalRandom.current();

    public static int[][] generateRandomMatrix(int size) {
        int[][] result = new int[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                result[i][j] = TLR.nextInt();
            }
        }
        return result;
    }

    public static int[] generateRandomArray(int size) {
        int[] arr = new int[size];
        for (int i = 0; i < size; i++) {
            arr[i] = TLR.nextInt();
        }
        return arr;
    }

    public static int[] generateRandomSortedArray(int size) {
        int[] arr = generateRandomArray(size);
        Arrays.sort(arr);
        return arr;
    }

    public static void printArray(int[][] arr) {
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr.length; j++) {
                System.out.print(arr[i][j] + " ");
            }
            System.out.println();
        }
    }
}
