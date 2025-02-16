package org.art.playground.misc.core.loom;

import jdk.internal.vm.Continuation;
import jdk.internal.vm.ContinuationScope;
import lombok.extern.slf4j.Slf4j;

// VM options: --enable-preview --add-exports java.base/jdk.internal.vm=ALL-UNNAMED
@Slf4j
public class ContinuationTest {

    public static void main(String[] args) {
        ContinuationScope scope = new ContinuationScope("test");
        Continuation continuation = new Continuation(scope, () -> {
            log.info("1");
            Continuation.yield(scope);
            log.info("2");
            Continuation.yield(scope);
            log.info("3");
        });

        while (!continuation.isDone()) {
            continuation.run();
        }
    }
}
