package org.art.playground.misc.core.stream_gatherer;

import java.util.List;
import java.util.stream.Gatherers;
import java.util.stream.Stream;

public class StreamGathererSamples {

    public static void main(String[] args) {

        // Fixed window
        List<List<Integer>> windows = Stream.of(1, 2, 3, 4, 5, 6)
            .gather(Gatherers.windowFixed(3))
            .toList();

        System.out.println(windows);
        // [[1, 2, 3], [4, 5, 6]]

        // Sliding window
        List<List<Integer>> slidingWindows = Stream.of(1, 2, 3, 4)
            .gather(Gatherers.windowSliding(2))
            .toList();

        System.out.println(slidingWindows);
        // [[1, 2], [2, 3], [3, 4]]

        // Stateful mapping
        List<String> cumulative = Stream.of("a", "b", "c")
            .gather(Gatherers.scan(() -> "", (acc, elem) -> acc + elem))
            .toList();

        System.out.println(cumulative);
        // [a, ab, abc]

        // Custom Gatherer
        var rewards = List.of(
            new Reward(10, "COINS"),
            new Reward(15, "GEMS"),
            new Reward(20, "COINS")
        );

        rewards.stream()
            .gather(CustomGatherers.reduceBy(Reward::type, Reward::add))
            .forEach(System.out::println);
    }

    record Reward(int amount, String type) {

        public Reward add(Reward toAdd) {
            return new Reward(amount + toAdd.amount, type);
        }
    }
}
