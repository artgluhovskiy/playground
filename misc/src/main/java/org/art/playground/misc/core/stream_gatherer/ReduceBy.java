package org.art.playground.misc.core.stream_gatherer;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Gatherer;

public class ReduceBy<T, P> implements Gatherer<T, Map<P, T>, T> {

    private final Function<T, P> selector;

    private final BiFunction<T, T, T> reducer;

    public ReduceBy(Function<T, P> selector, BiFunction<T, T, T> reducer) {
        this.selector = selector;
        this.reducer = reducer;
    }

    @Override
    public Supplier<Map<P, T>> initializer() {
        return HashMap::new;
    }

    @Override
    public Integrator<Map<P, T>, T, T> integrator() {
        return Integrator.of((state, item, _) -> {
            state.merge(selector.apply(item), item, reducer);
            return true;
        });
    }

    @Override
    public BiConsumer<Map<P, T>, Downstream<? super T>> finisher() {
        return (state, downstream) -> state.values().forEach(downstream::push);
    }
}
