package org.art.playground.jmh.arrays;

public class ArrayOps {

    public int find(int[] arr, int value) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == value) {
                return i;
            }
        }
        return -1;
    }

    public long conditionalSum(int[] arr, int upperBound) {
        long sum = 0;
        for (int value : arr) {
            if (value < upperBound) {
                sum += value;
            }
        }
        return sum;
    }

}
