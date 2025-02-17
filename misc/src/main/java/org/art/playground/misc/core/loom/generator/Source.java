package org.art.playground.misc.core.loom.generator;

import jdk.internal.vm.Continuation;
import jdk.internal.vm.ContinuationScope;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class Source<T> {

    private final ContinuationScope scope;

    private T value;

    public void yield(T t) {
        value = t;
        Continuation.yield(scope);
    }
}
