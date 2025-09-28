package org.art.playground.misc.algo.design;

import org.junit.jupiter.api.Test;

import java.util.ArrayDeque;
import java.util.Comparator;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * You are given an array of CPU tasks, each labeled with a letter from A to Z, and a number n.
 * Each CPU interval can be idle or allow the completion of one task.
 * Tasks can be completed in any order, but there's a constraint:
 * there has to be a gap of at least n intervals between two tasks with the same label.
 * <p>
 * Return the minimum number of CPU intervals required to complete all tasks.
 */
public class m_4_TaskScheduler {

    public int leastInterval(char[] tasks, int n) {
        // Priority queue or ready-to-run tasks
        Queue<Task> taskQueue = buildTaskQueue(tasks);

        // Queue of the future-scheduled tasks
        Deque<Task> taskScheduledQueue = new ArrayDeque<>();

        int time = 0;

        while (!taskQueue.isEmpty() || !taskScheduledQueue.isEmpty()) {
            time++;

            if (!taskQueue.isEmpty()) {
                Task task = taskQueue.poll();
                if (task.count > 1) {
                    Task newScheduled = new Task(task.count - 1, time + n);
                    taskScheduledQueue.addLast(newScheduled);
                }
            }

            if (!taskScheduledQueue.isEmpty()) {
                Task taskCandidate = taskScheduledQueue.peekFirst();
                if (taskCandidate.scheduledTime == time) {
                    taskQueue.add(taskScheduledQueue.pollFirst());
                }
            }
        }

        return time;
    }

    private Queue<Task> buildTaskQueue(char[] tasks) {
        Map<Character, Task> taskPool = new HashMap<>();

        for (char task : tasks) {
            if (taskPool.containsKey(task)) {
                taskPool.get(task).incCount();
            } else {
                Task taskDescriptor = new Task(1, 0);
                taskPool.put(task, taskDescriptor);
            }
        }

        PriorityQueue<Task> queue = new PriorityQueue<>(Comparator.comparingInt(Task::getCount).reversed());

        queue.addAll(taskPool.values());

        return queue;
    }

    static class Task {

        int count;

        int scheduledTime;

        public Task(int count, int scheduledTime) {
            this.count = count;
            this.scheduledTime = scheduledTime;
        }

        void incCount() {
            this.count++;
        }

        int getCount() {
            return count;
        }
    }

    @Test
    void test0() {
        assertThat(leastInterval(new char[]{'A', 'A', 'A', 'B', 'B', 'B'}, 2))
            .isEqualTo(8);
    }

    @Test
    void test1() {
        assertThat(leastInterval(new char[]{'A', 'C', 'A', 'B', 'D', 'B'}, 1))
            .isEqualTo(6);
    }

    @Test
    void test2() {
        assertThat(leastInterval(new char[]{'A', 'A', 'A', 'B', 'B', 'B'}, 3))
            .isEqualTo(10);
    }

    @Test
    void test3() {
        assertThat(leastInterval(new char[]{'A', 'B', 'A'}, 2))
            .isEqualTo(4);
    }
}
