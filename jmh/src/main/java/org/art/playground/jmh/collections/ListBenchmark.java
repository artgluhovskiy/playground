package org.art.playground.jmh.collections;

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
import org.openjdk.jmh.annotations.TearDown;
import org.openjdk.jmh.annotations.Warmup;
import org.openjdk.jmh.infra.Blackhole;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * Benchmark                                   Mode  Cnt     Score     Error  Units
 * ListBenchmark.addFirst_ArrayDeque           avgt    4    28.712 ±   7.583  ns/op
 * ListBenchmark.addFirst_ArrayList            avgt    4   308.210 ±  44.881  ns/op
 * ListBenchmark.addFirst_LinkedList           avgt    4    32.847 ±  11.127  ns/op
 * </p>
 * ListBenchmark.addLast_ArrayDeque            avgt    4    95.866 ± 424.256  ns/op
 * ListBenchmark.addLast_ArrayList             avgt    4    36.921 ±  22.811  ns/op
 * ListBenchmark.addLast_LinkedList            avgt    4    33.008 ±  13.937  ns/op
 * </p>
 * ListBenchmark.add_ArrayDeque                avgt    4    31.600 ±  30.193  ns/op
 * ListBenchmark.add_ArrayList                 avgt    4    31.492 ±   9.694  ns/op
 * ListBenchmark.add_LinkedList                avgt    4    32.314 ±  30.036  ns/op
 * </p>
 * ListBenchmark.getAndRemoveFirst_ArrayDeque  avgt    4    28.632 ±  37.198  ns/op
 * ListBenchmark.getAndRemoveFirst_ArrayList   avgt    4   296.380 ±  15.775  ns/op
 * ListBenchmark.getAndRemoveFirst_LinkedList  avgt    4    35.525 ±  16.618  ns/op
 * </p>
 * ListBenchmark.getAndRemoveLast_ArrayDeque   avgt    4    30.332 ±  10.628  ns/op
 * ListBenchmark.getAndRemoveLast_ArrayList    avgt    4    28.864 ±   6.593  ns/op
 * ListBenchmark.getAndRemoveLast_LinkedList   avgt    4    30.665 ±   8.643  ns/op
 * </p>
 * ListBenchmark.getFirst_ArrayDeque           avgt    4    30.741 ±  12.375  ns/op
 * ListBenchmark.getFirst_ArrayList            avgt    4    28.557 ±  11.574  ns/op
 * ListBenchmark.getFirst_LinkedList           avgt    4    28.260 ±  10.702  ns/op
 * </p>
 * ListBenchmark.setInTheMiddle_ArrayList      avgt    4    29.901 ±   7.604  ns/op
 * ListBenchmark.setInTheMiddle_LinkedList     avgt    4  6440.551 ± 182.455  ns/op
 */
@Fork(value = 2)
@Warmup(iterations = 1, time = 5)
@Measurement(iterations = 2, time = 5)
@State(Scope.Thread)
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
public class ListBenchmark {

    private static final int SIZE = 3000;

    @State(Scope.Thread)
    public static class GlobalState {

        ArrayList<String> arrayList;

        ArrayDeque<String> arrayDeque;

        LinkedList<String> linkedList;

        String elemToAdd;

        @Setup(Level.Invocation)
        public void setupState() {
            arrayList = new ArrayList<>();
            arrayDeque = new ArrayDeque<>();
            linkedList = new LinkedList<>();

            for (int i = 0; i < SIZE; i++) {
                String val = UUID.randomUUID().toString();
                arrayList.add(val);
                arrayDeque.add(val);
                linkedList.add(val);
            }

            elemToAdd = UUID.randomUUID().toString();
        }

        @TearDown(value = Level.Invocation)
        public void tearDown() {
            arrayList = null;
            arrayDeque = null;
            linkedList = null;
            elemToAdd = null;
        }
    }

    @Benchmark
    public void add_ArrayList(GlobalState globalState, Blackhole blackhole) {
        boolean result = globalState.arrayList.add(globalState.elemToAdd);
        blackhole.consume(result);
    }

    @Benchmark
    public void add_ArrayDeque(GlobalState globalState, Blackhole blackhole) {
        boolean result = globalState.arrayDeque.add(globalState.elemToAdd);
        blackhole.consume(result);
    }

    @Benchmark
    public void add_LinkedList(GlobalState globalState, Blackhole blackhole) {
        boolean result = globalState.linkedList.add(globalState.elemToAdd);
        blackhole.consume(result);
    }

    @Benchmark
    public void addFirst_ArrayList(GlobalState globalState) {
        globalState.arrayList.add(0, globalState.elemToAdd);
    }

    @Benchmark
    public void addFirst_ArrayDeque(GlobalState globalState, Blackhole blackhole) {
        boolean result = globalState.arrayDeque.offerFirst(globalState.elemToAdd);
        blackhole.consume(result);
    }

    @Benchmark
    public void addFirst_LinkedList(GlobalState globalState, Blackhole blackhole) {
        boolean result = globalState.linkedList.offerFirst(globalState.elemToAdd);
        blackhole.consume(result);
    }

    @Benchmark
    public void addLast_ArrayList(GlobalState globalState) {
        globalState.arrayList.add(SIZE - 1, globalState.elemToAdd);
    }

    @Benchmark
    public void addLast_ArrayDeque(GlobalState globalState, Blackhole blackhole) {
        boolean result = globalState.arrayDeque.offerLast(globalState.elemToAdd);
        blackhole.consume(result);
    }

    @Benchmark
    public void addLast_LinkedList(GlobalState globalState, Blackhole blackhole) {
        boolean result = globalState.linkedList.offerLast(globalState.elemToAdd);
        blackhole.consume(result);
    }

    @Benchmark
    public void setInTheMiddle_ArrayList(GlobalState globalState, Blackhole blackhole) {
        String result = globalState.arrayList.set(SIZE / 2, globalState.elemToAdd);
        blackhole.consume(result);
    }

    @Benchmark
    public void setInTheMiddle_LinkedList(GlobalState globalState, Blackhole blackhole) {
        String result = globalState.linkedList.set(SIZE / 2, globalState.elemToAdd);
        blackhole.consume(result);
    }

    @Benchmark
    public void getFirst_ArrayList(GlobalState globalState, Blackhole blackhole) {
        String result = globalState.arrayList.get(0);
        blackhole.consume(result);
    }

    @Benchmark
    public void getFirst_ArrayDeque(GlobalState globalState, Blackhole blackhole) {
        String result = globalState.arrayDeque.getFirst();
        blackhole.consume(result);
    }

    @Benchmark
    public void getFirst_LinkedList(GlobalState globalState, Blackhole blackhole) {
        String result = globalState.linkedList.getFirst();
        blackhole.consume(result);
    }

    @Benchmark
    public void getAndRemoveFirst_ArrayList(GlobalState globalState, Blackhole blackhole) {
        String result = globalState.arrayList.remove(0);
        blackhole.consume(result);
    }

    @Benchmark
    public void getAndRemoveFirst_ArrayDeque(GlobalState globalState, Blackhole blackhole) {
        String result = globalState.arrayDeque.pollFirst();
        blackhole.consume(result);
    }

    @Benchmark
    public void getAndRemoveFirst_LinkedList(GlobalState globalState, Blackhole blackhole) {
        String result = globalState.linkedList.pollFirst();
        blackhole.consume(result);
    }

    @Benchmark
    public void getAndRemoveLast_ArrayList(GlobalState globalState, Blackhole blackhole) {
        String result = globalState.arrayList.remove(SIZE - 1);
        blackhole.consume(result);
    }

    @Benchmark
    public void getAndRemoveLast_ArrayDeque(GlobalState globalState, Blackhole blackhole) {
        String result = globalState.arrayDeque.pollLast();
        blackhole.consume(result);
    }

    @Benchmark
    public void getAndRemoveLast_LinkedList(GlobalState globalState, Blackhole blackhole) {
        String result = globalState.linkedList.pollLast();
        blackhole.consume(result);
    }

}
