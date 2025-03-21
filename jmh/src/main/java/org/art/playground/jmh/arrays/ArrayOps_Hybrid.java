package org.art.playground.jmh.arrays;

import jdk.incubator.vector.IntVector;
import jdk.incubator.vector.VectorOperators;
import jdk.incubator.vector.VectorSpecies;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class ArrayOps_Hybrid {
    private static final VectorSpecies<Integer> SPECIES = IntVector.SPECIES_PREFERRED;
    private static final int THRESHOLD = 1024;

    private static class VectorSearchTask extends RecursiveTask<Integer> {
        private final int[] array;
        private final int start, end, target;

        public VectorSearchTask(int[] array, int start, int end, int target) {
            this.array = array;
            this.start = start;
            this.end = end;
            this.target = target;
        }

        @Override
        protected Integer compute() {
            if (end - start <= THRESHOLD) {
                return vectorSearch();
            }

            int mid = start + (end - start) / 2;
            var leftTask = new VectorSearchTask(array, start, mid, target);
            var rightTask = new VectorSearchTask(array, mid, end, target);

            leftTask.fork();
            int rightResult = rightTask.compute();
            int leftResult = leftTask.join();

            return leftResult != -1 ? leftResult : rightResult;
        }

        private int vectorSearch() {
            int i = start;
            int vectorLimit = end - SPECIES.length();

            while (i <= vectorLimit) {
                var vector = IntVector.fromArray(SPECIES, array, i);
                var mask = vector.compare(VectorOperators.EQ, target);

                if (mask.anyTrue()) {
                    return i + mask.firstTrue();
                }
                i += SPECIES.length();
            }

            for (; i < end; i++) {
                if (array[i] == target) {
                    return i;
                }
            }
            return -1;
        }
    }

    public int find(int[] array, int target) {
        return ForkJoinPool.commonPool()
            .invoke(new VectorSearchTask(array, 0, array.length, target));
    }
}