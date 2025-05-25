package org.art.playground.misc.core.stream_gatherer;

import java.util.Map;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.stream.Gatherer;

public class CustomGatherers {

    public static <T, P> Gatherer<T, Map<P, T>, T> reduceBy(Function<T, P> selector, BiFunction<T, T, T> reducer) {
        return new ReduceBy<>(selector, reducer);
    }
}
