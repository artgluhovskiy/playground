package org.art.playground.jmh.arrays;

import lombok.Data;
import lombok.SneakyThrows;
import org.art.playground.jmh.utils.ArrayUtils;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Fork;
import org.openjdk.jmh.annotations.Level;
import org.openjdk.jmh.annotations.Measurement;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.Warmup;
import org.openjdk.jmh.infra.Blackhole;

import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

/*
    ArrayConditionalSumBenchmark.conditionalSum_random  avgt    5  2753321.844 ± 442126.222  ns/op
    ArrayConditionalSumBenchmark.conditionalSum_sorted  avgt    5   827871.737 ±  35026.537  ns/op      > 3 times faster

    Refs:
    https://stackoverflow.com/questions/11227809/why-is-processing-a-sorted-array-faster-than-processing-an-unsorted-array/11227902#11227902
 */
@Fork(value = 1)
@Warmup(iterations = 1, time = 10)
@Measurement(iterations = 5, time = 10)
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
public class ArrayConditionalSumBenchmark {

    private static final ThreadLocalRandom TLR = ThreadLocalRandom.current();

    private static final int ARR_SIZE = 2 << 20;

    @Data
    @State(Scope.Thread)
    public static class BenchmarkState {

        private ArrayOps arrayOps = new ArrayOps();

        int value;

        int[] arr;

        int[] arrSorted;

        @SneakyThrows
        @Setup(Level.Invocation)
        public void setup() {
            value = TLR.nextInt();

            arr = ArrayUtils.generateRandomArray(ARR_SIZE);

            arrSorted = ArrayUtils.generateRandomSortedArray(ARR_SIZE);
        }
    }

    @Benchmark
    public void conditionalSum_random(BenchmarkState state, Blackhole blackhole) {
        int[] arr = state.arr;
        int value = state.value;
        long result = state.arrayOps.conditionalSum(arr, value);
        blackhole.consume(result);
    }

    @Benchmark
    public void conditionalSum_sorted(BenchmarkState state, Blackhole blackhole) {
        int[] arr = state.arrSorted;
        int value = state.value;
        long result = state.arrayOps.conditionalSum(arr, value);
        blackhole.consume(result);
    }
}
