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
    Benchmark                             Mode  Cnt       Score       Error  Units
    ArraysBenchmark.findInArray_scalar    avgt    3  322918.622 ± 19350.211  ns/op
    ArraysBenchmark.findInArray_parallel  avgt    3  178540.271 ± 15667.190  ns/op
    ArraysBenchmark.findInArray_vector    avgt    3  135383.961 ±  6248.461  ns/op

    Additional data:
    JEP: https://openjdk.org/jeps/438
    Playlist: https://www.youtube.com/@tilir/playlists
    SIMD: https://www.youtube.com/results?search_query=%D0%BA%D0%BE%D0%BD%D1%81%D1%82%D0%B0%D0%BD%D1%82%D0%B8%D0%BD+%D0%B2%D0%BB%D0%B0%D0%B4%D0%B8%D0%BC%D0%B8%D1%80%D0%BE%D0%B2+simd
    CMD: sysctl -a | grep machdep.cpu | grep AVX

    java -jar target/benchmarks.jar -jvmArgsAppend --add-modules=jdk.incubator.vector org.art.playground.jmh.arrays.ArraysBenchmark
 */
@Fork(value = 1, jvmArgsPrepend = {
    "--add-modules=jdk.incubator.vector",
    "-Djdk.incubator.vector.VECTOR_ACCESS_OOB_CHECK=0",
    "-XX:+UseSuperWord",
})
@Warmup(iterations = 1, time = 10)
@Measurement(iterations = 3, time = 10)
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
public class ArraysBenchmark {

    private static final ThreadLocalRandom TLR = ThreadLocalRandom.current();

    private static final int ARR_SIZE = 2 << 20;

    @Data
    @State(Scope.Thread)
    public static class BenchmarkState {

        private ArrayOps_Scalar opsScalar = new ArrayOps_Scalar();

        private ArrayOps_Vector opsVector = new ArrayOps_Vector();

        private ArrayOps_Parallel opsParallel = new ArrayOps_Parallel();

        private ArrayOps_Hybrid opsHyb = new ArrayOps_Hybrid();

        int value;

        int[] arr;

        @SneakyThrows
        @Setup(Level.Invocation)
        public void setup() {
            value = TLR.nextInt();

            arr = ArrayUtils.generateRandomArray(ARR_SIZE);
        }
    }

    @Benchmark
    public void findInArray_scalar(BenchmarkState state, Blackhole blackhole) {
        int[] arr = state.arr;
        int value = state.value;
        int result = state.opsScalar.find(arr, value);
        blackhole.consume(result);
    }

    @Benchmark
    public void findInArray_vector(BenchmarkState state, Blackhole blackhole) {
        int[] arr = state.arr;
        int value = state.value;
        int result = state.opsVector.find(arr, value);
        blackhole.consume(result);
    }

    @Benchmark
    public void findInArray_parallel(BenchmarkState state, Blackhole blackhole) {
        int[] arr = state.arr;
        int value = state.value;
        int result = state.opsParallel.find(arr, value);
        blackhole.consume(result);
    }

    @Benchmark
    public void findInArray_hyb(BenchmarkState state, Blackhole blackhole) {
        int[] arr = state.arr;
        int value = state.value;
        int result = state.opsHyb.find(arr, value);
        blackhole.consume(result);
    }
}
