package org.art.playground.jmh;

import lombok.SneakyThrows;
import org.art.playground.jmh.arrays.ArraysBenchmark;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.options.OptionsBuilder;

public class BenchmarkRunner {

    @SneakyThrows
    public static void main(String[] args) {
        var opt = new OptionsBuilder()
//            .include(JsonUpdateBenchmark.class.getSimpleName())
            .include(ArraysBenchmark.class.getSimpleName())
            .build();

        new Runner(opt).run();
    }
}