package org.art.playground.misc.core.loom.generator;

import lombok.extern.slf4j.Slf4j;

import static org.art.playground.misc.core.loom.generator.Generator.generator;

@Slf4j
public class GeneratorTest {

    public static void main(String[] args) {

        Generator<String> gen = generator(source -> {
            source.yield("A");

            source.yield("B");

            source.yield("C");
        });

        while (gen.hasNext()) {
            log.info(gen.next());
            log.info("Do something");
        }
    }
}
