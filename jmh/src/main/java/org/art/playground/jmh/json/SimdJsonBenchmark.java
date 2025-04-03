package org.art.playground.jmh.json;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import lombok.SneakyThrows;
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
import org.simdjson.SimdJsonParser;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

@Fork(value = 1, jvmArgsPrepend = {
    "--add-modules=jdk.incubator.vector",
    "-Djdk.incubator.vector.VECTOR_ACCESS_OOB_CHECK=0",
    "-XX:+UseSuperWord",
})
@Warmup(iterations = 1, time = 10)
@Measurement(iterations = 2, time = 10)
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MICROSECONDS)
public class SimdJsonBenchmark {

    private static final ThreadLocalRandom TLR = ThreadLocalRandom.current();

    private static final ObjectMapper JACKSON_PARSER = new ObjectMapper();

    private static final SimdJsonParser SIMD_PARSER = new SimdJsonParser();

    @Data
    @State(Scope.Thread)
    public static class SimpleTemplateCaseState {

        byte[] jsonData;

        @SneakyThrows
        @Setup(Level.Invocation)
        public void setup() {

            var tmp = """
                {
                   "resource_name": "dynamic/features/elements/resource.object",
                   "nodes": [
                      {
                         "node_name": "chest_state_${index}",
                         "node_type": 3,
                         "options": {
                            "text":""
                         }
                      },
                      {
                         "node_name": "amount_${index}",
                         "node_type": 3,
                         "options": {
                            "text": ""
                         }
                      },
                      {
                         "node_name": "size_${index}",
                         "node_type": 3,
                         "options": {
                            "text": ""
                         }
                      },
                      {
                         "node_name": "size_${index}",
                         "node_type": 3,
                         "options": {
                            "text": ""
                         }
                      }
                   ]
                }
                """;

            var json = tmp.replaceAll("\\$\\{index}", String.valueOf(TLR.nextInt()));

            jsonData = json.getBytes();
        }

    }

    @Data
    static class Model {
        String resourceName;
        List<Node> nodes;
    }

    @Data
    static class Node {
        String nodeName;
        int nodeType;
        Map<String, String> options;
    }

    @SneakyThrows
    @Benchmark
    public void parseJson_Jackson(SimpleTemplateCaseState state, Blackhole blackhole) {
        var jsonData = state.jsonData;

        var model = JACKSON_PARSER.readValue(jsonData, Model.class);

        blackhole.consume(model);
    }

    @SneakyThrows
    @Benchmark
    public void parseJson_Simd(SimpleTemplateCaseState state, Blackhole blackhole) {
        var jsonData = state.jsonData;

        var model = SIMD_PARSER.parse(jsonData, jsonData.length, Model.class);

        blackhole.consume(model);
    }

}
