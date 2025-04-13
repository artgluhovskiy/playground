package org.art.playground.misc.algo.design;

import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.assertj.core.api.Assertions.assertThat;

public class m_1_LruCache {

    public static class LRUCache {

        private final HashMap<Integer, Node> buffer;

        private final int capacity;

        private final AccessQueue accessQueue = new AccessQueue();

        public LRUCache(int capacity) {
            this.capacity = capacity;
            this.buffer = new HashMap<>(capacity);
        }

        public int get(int key) {
            Node cachedNode = buffer.get(key);
            if (cachedNode != null) {
                accessQueue.remove(cachedNode);
                accessQueue.insert(cachedNode);
                return cachedNode.value;
            } else {
                return -1;
            }
        }

        public void put(int key, int value) {
            Node cachedNode = buffer.get(key);
            if (cachedNode != null) {
                accessQueue.remove(cachedNode);

            } else if (buffer.size() >= this.capacity) {
                Node toRemove = accessQueue.tail.prev;
                accessQueue.remove(toRemove);
                buffer.remove(toRemove.key);
            }

            Node newNode = new Node(key, value);
            accessQueue.insert(newNode);
            buffer.put(key, newNode);
        }

        static class AccessQueue {

            Node head;

            Node tail;

            public AccessQueue() {
                head = new Node(-1, -1);
                tail = new Node(-1, -1);
                head.next = tail;
                tail.prev = head;
            }

            void insert(Node node) {
                head.next.prev = node;
                node.next = head.next;
                node.prev = head;
                head.next = node;
            }

            void remove(Node node) {
                node.next.prev = node.prev;
                node.prev.next = node.next;
                node.next = null;
                node.prev = null;
            }
        }

        static class Node {

            Node prev;

            Node next;

            final int key;

            final int value;

            public Node(int key, int value) {
                this.key = key;
                this.value = value;
            }
        }
    }

    @Test
    void test0() {
        LRUCache cache = new LRUCache(2);
        cache.put(1, 0);
        cache.put(2, 2);
        cache.get(1);
        cache.put(3, 3);
        cache.get(2);
        cache.put(4, 4);
        cache.get(1);
        cache.get(3);
        cache.get(4);

        assertThat(cache.get(1)).isEqualTo(-1);
        assertThat(cache.get(2)).isEqualTo(-1);
        assertThat(cache.get(3)).isEqualTo(3);
    }
}
