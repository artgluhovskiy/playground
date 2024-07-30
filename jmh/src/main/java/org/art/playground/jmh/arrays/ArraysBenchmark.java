package org.art.playground.jmh.arrays;

import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

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

/*
    Benchmark                           Mode  Cnt     Score   Error  Units
    ArraysBenchmark.findInArray_scalar  avgt    2  6277.206          ns/op
    ArraysBenchmark.findInArray_vector  avgt    2  2962.806          ns/op
 */
/*
    java -jar target/benchmarks.jar -jvmArgsAppend --add-modules=jdk.incubator.vector org.art.playground.jmh.arrays.ArraysBenchmark
 */
@Fork(value = 1, jvmArgsPrepend = {
    "--add-modules=jdk.incubator.vector",
    "-Djdk.incubator.vector.VECTOR_ACCESS_OOB_CHECK=0",
    "-XX:+UseSuperWord",
})
@Warmup(iterations = 1, time = 10)
@Measurement(iterations = 2, time = 10)
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
public class ArraysBenchmark {

    private static final ThreadLocalRandom TLR = ThreadLocalRandom.current();

    private static final int ARR_SIZE = 8 << 13;

    @Data
    @State(Scope.Thread)
    public static class BenchmarkState {

        private ArrayOps_Scalar opsScalar = new ArrayOps_Scalar();

        private ArrayOps_Vector opsVector = new ArrayOps_Vector();

        int value;

        int[] arr1;

        int[] arr2;

        int[][] arrSquare1;

        int[][] arrSquare2;

        @SneakyThrows
        @Setup(Level.Invocation)
        public void setup() {
            value = TLR.nextInt();
            arr1 = ArrayUtils.generateRandomArray(ARR_SIZE);
            arr2 = ArrayUtils.generateRandomArray(ARR_SIZE);
            arrSquare1 = ArrayUtils.generateRandomMatrix(ARR_SIZE);
            arrSquare2 = ArrayUtils.generateRandomMatrix(ARR_SIZE);
        }
    }

    @Benchmark
    public void findInArray_scalar(BenchmarkState state, Blackhole blackhole) {
        int[] arr = state.arr1;
        int value = state.value;
        int result = state.opsScalar.find(arr, value);
        blackhole.consume(result);
    }

    @Benchmark
    public void findInArray_vector(BenchmarkState state, Blackhole blackhole) {
        int[] arr = state.arr1;
        int value = state.value;
        int result = state.opsVector.find(arr, value);
        blackhole.consume(result);
    }






    /* ------------- */

    //    @Benchmark
    public void addArrays_scalar(BenchmarkState state, Blackhole blackhole) {
        int[] arr1 = state.arr1;
        int[] arr2 = state.arr2;
        int[] result = state.opsScalar.add(arr1, arr2);
        blackhole.consume(result);
    }

    //    @Benchmark
    public void addArrays_vector(BenchmarkState state, Blackhole blackhole) {
        int[] arr1 = state.arr1;
        int[] arr2 = state.arr2;
        int[] result = state.opsVector.add(arr1, arr2);
        blackhole.consume(result);
    }

    //    @Benchmark
    public void mulArray_scalar(BenchmarkState state, Blackhole blackhole) {
        int[][] arr1 = state.arrSquare1;
        int[][] arr2 = state.arrSquare2;
        int[][] result = state.opsScalar.mulMatrices(arr1, arr2);
        blackhole.consume(result);
    }

    //    @Benchmark
    public void mulArray_vector(BenchmarkState state, Blackhole blackhole) {
        int[][] arr1 = state.arrSquare1;
        int[][] arr2 = state.arrSquare2;
        int[][] result = state.opsVector.mulMatricesV2(arr1, arr2);
        blackhole.consume(result);
    }

}
