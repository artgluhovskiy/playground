package org.art.playground.jmh.arrays;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class ArrayOps_Parallel {

    private static class SearchTask extends RecursiveTask<Integer> {

        private static final int THRESHOLD = 500;

        private final int[] array;

        private final int start, end, target;

        public SearchTask(int[] array, int start, int end, int target) {
            this.array = array;
            this.start = start;
            this.end = end;
            this.target = target;
        }

        @Override
        protected Integer compute() {
            if (end - start <= THRESHOLD) {
                return sequentialSearch();
            } else {
                int mid = start + (end - start) / 2;
                SearchTask leftTask = new SearchTask(array, start, mid, target);
                SearchTask rightTask = new SearchTask(array, mid, end, target);

                leftTask.fork();
                int rightResult = rightTask.compute();
                int leftResult = leftTask.join();

                if (leftResult != -1) return leftResult;
                return rightResult;
            }
        }

        private int sequentialSearch() {
            for (int i = start; i < end; i++) {
                if (array[i] == target) {
                    return i;
                }
            }
            return -1;
        }
    }

    public int find(int[] array, int target) {
        return ForkJoinPool.commonPool()
            .invoke(new SearchTask(array, 0, array.length, target));
    }
}
