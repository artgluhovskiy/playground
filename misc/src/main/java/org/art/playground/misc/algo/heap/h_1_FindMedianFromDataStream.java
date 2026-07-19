package org.art.playground.misc.algo.heap;

import org.junit.jupiter.api.Test;

import java.util.Comparator;
import java.util.PriorityQueue;

import static org.assertj.core.api.Assertions.assertThat;

public class h_1_FindMedianFromDataStream {

    private PriorityQueue<Integer> maxHeap = new PriorityQueue<>();
    private PriorityQueue<Integer> minHeap = new PriorityQueue<>(Comparator.reverseOrder());

    public h_1_FindMedianFromDataStream() {
    }

    public void addNum(int num) {
        maxHeap.offer(num);

        minHeap.offer(maxHeap.poll());

        if (minHeap.size() > maxHeap.size()) {
            maxHeap.offer(minHeap.poll());
        }
    }

    public double findMedian() {
        if (minHeap.size() == maxHeap.size()) {
            Integer val1 = !minHeap.isEmpty() ? minHeap.peek() : 0;
            Integer val2 = !maxHeap.isEmpty() ? maxHeap.peek() : 0;
            return (val1 + val2) / (float) 2;
        } else {
            return !maxHeap.isEmpty() ? maxHeap.peek() : 0;
        }
    }

    @Test
    void test0() {
        h_1_FindMedianFromDataStream st = new h_1_FindMedianFromDataStream();
        st.addNum(1);
        st.addNum(2);
        assertThat(st.findMedian()).isEqualTo(1.5);
        st.addNum(3);
        assertThat(st.findMedian()).isEqualTo(2.0);
    }
}
