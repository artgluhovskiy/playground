package org.art.playground.jmh.json;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
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

/**
 * Benchmark                                          Mode  Cnt   Score   Error  Units
 * JsonUpdateBenchmark.updateNestedTemplate_JsonPath  avgt    4  13.526 ± 3.354  us/op
 * JsonUpdateBenchmark.updateSimpleTemplate_JsonPath  avgt    4   7.530 ± 0.416  us/op  (!)
 * JsonUpdateBenchmark.updateSimpleTemplate_RegExp    avgt    4   2.707 ± 0.057  us/op
 */
@Fork(value = 2)
@Warmup(iterations = 1, time = 10)
@Measurement(iterations = 2, time = 10)
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MICROSECONDS)
public class JsonUpdateBenchmark {

    private static final ObjectMapper MAPPER = new ObjectMapper();

    private static final Pattern PATTERN = Pattern.compile("\\$\\.nodes\\[\\?\\(@\\.node_name(?:\\s*)==(?:\\s*)\\'(.*)\'\\)\\]\\.options\\.(.*)");

    // CASE 1: Simple template with FLAT structure
    @Data
    @State(Scope.Thread)
    public static class SimpleTemplateCaseState {

        String template;

        Map<String, Object> templateMap;

        // jsonPath - value
        Map<String, String> values;

        @SneakyThrows
        @Setup(Level.Invocation)
        public void setup() {

            template = """
                {
                   "resource_name": "dynamic/features/elements/resource.object",
                   "nodes": [
                      {
                         "node_name": "chest_state",
                         "node_type": 3,
                         "options": {
                            "text":""
                         }
                      },
                      {
                         "node_name": "amount",
                         "node_type": 3,
                         "options": {
                            "text": ""
                         }
                      },
                      {
                         "node_name": "size",
                         "node_type": 3,
                         "options": {
                            "text": ""
                         }
                      }
                   ]
                }
                """;

            templateMap = MAPPER.readValue(template, new TypeReference<>() {
            });

            values = Map.of(
                "$.nodes[?(@.node_name=='amount')].options.text", UUID.randomUUID().toString(),
                "$.nodes[?(@.node_name=='size')].options.text", UUID.randomUUID().toString(),
                "$.nodes[?(@.node_name=='chest_state')].options.text", UUID.randomUUID().toString()
            );
        }

    }

    // CASE 2: Template with NESTED structure
    @State(Scope.Thread)
    public static class NestedTemplateCaseState {

        String template;

        Map<String, Object> templateMap;

        // jsonPath - value
        Map<String, String> values;

        @SneakyThrows
        @Setup(Level.Invocation)
        public void setup() {
            template = """
                {
                   "resource_name": "dynamic/features/elements/resource.object",
                   "nodes": [
                      {
                         "node_name": "chest_state",
                         "node_type": 3,
                         "options": {
                            "text": ""
                         }
                      },
                      {
                         "node_name": "amount",
                         "node_type": 3,
                         "options": {
                            "text": ""
                         }
                      },
                      {
                         "node_name": "size",
                         "node_type": 2,
                         "options": {
                            "style_config": {
                                "resource_name": "dynamic/features/elements/other/resource.object",
                                "nodes": [
                                     {
                                         "node_name": "chest_state",
                                         "node_type": 3,
                                         "options": {
                                            "text": ""
                                         }
                                    },
                                    {
                                         "node_name": "amount",
                                         "node_type": 3,
                                         "options": {
                                            "text": ""
                                         }
                                    }
                                ]
                            }
                         }
                      }
                   ]
                }
                """;

            templateMap = MAPPER.readValue(template, new TypeReference<>() {
            });

            values = Map.of(
                "$.nodes[?(@.node_name=='size')].options.style_config.nodes[?(@.node_name=='amount')].options.text", UUID.randomUUID().toString(),
                "$.nodes[?(@.node_name=='size')].options.style_config.nodes[?(@.node_name=='chest_state')].options.text", UUID.randomUUID().toString(),
                "$.nodes[?(@.node_name=='chest_state')].options.text", UUID.randomUUID().toString(),
                "$.nodes[?(@.node_name=='amount')].options.text", UUID.randomUUID().toString()
            );
        }

    }

    @Benchmark
    public void updateSimpleTemplate_JsonPath(SimpleTemplateCaseState state, Blackhole blackhole) {
        var result = processJsonByJsonPath(state.template, state.values);
        blackhole.consume(result);
    }

    @Benchmark
    public void updateNestedTemplate_JsonPath(NestedTemplateCaseState state, Blackhole blackhole) {
        var result = processJsonByJsonPath(state.template, state.values);
        blackhole.consume(result);
    }

    @Benchmark
    public void updateSimpleTemplate_RegExp(SimpleTemplateCaseState state, Blackhole blackhole) {
        var result = processJsonByRegExp(state.templateMap, state.values);
        blackhole.consume(result);
    }

    private String processJsonByJsonPath(String template, Map<String, String> values) {
        DocumentContext result = null;
        var json = JsonPath.parse(template);
        for (var valueEntry : values.entrySet()) {
            var jsonPath = valueEntry.getKey();
            var value = valueEntry.getValue();
            result = json.set(jsonPath, value);
        }
        return result.jsonString();
    }

    @SuppressWarnings("unchecked")
    private Map<String, Object> processJsonByRegExp(Map<String, Object> template, Map<String, String> values) {
        for (Map.Entry<String, String> valueEntry : values.entrySet()) {
            var jsonPath = valueEntry.getKey();
            var value = valueEntry.getValue();

            var matcher = PATTERN.matcher(jsonPath);
            matcher.find();
            var nodeName = matcher.group(1);
            var valuePathStr = matcher.group(2);
            var valuePath = buildValuePath(valuePathStr);

            var nodes = (List<Map<String, Object>>) template.get("nodes");

            for (Map<String, Object> node : nodes) {
                if (node.get("node_name").equals(nodeName)) {
                    findAndReplace(node, "options", valuePath, value);
                }
            }
        }
        return template;
    }

    private LinkedList<String> buildValuePath(String valuePathStr) {
        var valuePathArray = valuePathStr.split(".");

        var valuePath = new LinkedList<String>();

        if (valuePathArray.length == 0) {
            valuePath.add(valuePathStr);
        } else {
            valuePath = Arrays.stream(valuePathArray)
                .collect(Collectors.toCollection(LinkedList::new));
        }
        return valuePath;
    }

    @SuppressWarnings("unchecked")
    private void findAndReplace(Map<String, Object> nodeMap, String step, LinkedList<String> trace, String value) {
        if (!(nodeMap.get(step) instanceof Map)) {
            nodeMap.put(step, value);
        }

        if (trace.isEmpty()) {
            return;
        }

        String nextStep = trace.pop();

        findAndReplace((Map<String, Object>) nodeMap.get(step), nextStep, trace, value);
    }

}
