package org.art.playground.misc.core.loom.generator;

import jdk.internal.vm.Continuation;
import jdk.internal.vm.ContinuationScope;

import java.util.function.Consumer;

public class Generator<T> {

    private final ContinuationScope scope;
    private final Continuation continuation;
    private final Source<T> source;

    public boolean hasNext() {
        return !continuation.isDone();
    }

    public T next() {
        T value = source.getValue();
        continuation.run();
        return value;
    }

    public Generator(Consumer<Source<T>> consumer) {
        scope = new ContinuationScope("Generator");
        source = new Source<>(scope);
        continuation = new Continuation(scope, () -> consumer.accept(source));
        continuation.run();
    }

    public static <T> Generator<T> generator(Consumer<Source<T>> consumer) {
        return new Generator<>(consumer);
    }
}
