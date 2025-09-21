package org.art.playground.misc.algo.trees_and_graphs;

import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.junit.jupiter.api.Test;

import java.util.Deque;
import java.util.LinkedList;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * You are given a perfect binary tree where all leaves are on the same level, and every parent has two children.
 * Populate each next pointer to point to its next right node. If there is no next right node, the next pointer should be set to NULL.
 * Initially, all next pointers are set to NULL.
 */
public class m_2_PopulatingNextRightPointersInEachNode {

    @ToString
    @EqualsAndHashCode
    class Node {

        public int val;
        public Node left;
        public Node right;
        public Node next;

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, Node _left, Node _right, Node _next) {
            val = _val;
            left = _left;
            right = _right;
            next = _next;
        }
    }

    public Node connect(Node root) {
        if (root == null) {
            return null;
        }

        Node cur = root;
        Node next = root.left;

        while (cur != null && next != null) {
            cur.left.next = cur.right;

            if (cur.next != null) {
                cur.right.next = cur.next.left;
            }

            cur = cur.next;
            if (cur == null) {
                cur = next;
                next = cur.left;
            }
        }

        return root;
    }

    public Node connectSimpleBfs(Node root) {
        if (root == null) {
            return null;
        }

        Deque<Node> deque = new LinkedList<>();
        deque.addFirst(root);

        while (!deque.isEmpty()) {

            int levelSize = deque.size();

            Node prevNode = null;

            for (int i = 0; i < levelSize; i++) {

                Node currentNode = deque.pollLast();

                if (currentNode.left != null) {
                    deque.addFirst(currentNode.left);
                }

                if (currentNode.right != null) {
                    deque.addFirst(currentNode.right);
                }

                // Setting "next" links

                if (prevNode != null) {
                    prevNode.next = currentNode;
                }

                prevNode = currentNode;
            }
        }

        return root;
    }

    @Test
    void test0() {
        Node rootIn = new Node(1, null, null, null);
        Node n2In = new Node(2, null, null, null);
        Node n3In = new Node(3, null, null, null);
        rootIn.left = n2In;
        rootIn.right = n3In;

        Node n4In = new Node(4, null, null, null);
        Node n5In = new Node(5, null, null, null);
        n2In.left = n4In;
        n2In.right = n5In;

        Node n6In = new Node(6, null, null, null);
        Node n7In = new Node(7, null, null, null);
        n3In.left = n6In;
        n3In.right = n7In;

        // ----

        Node rootOut = new Node(1, null, null, null);
        Node n2Out = new Node(2, null, null, null);
        Node n3Out = new Node(3, null, null, null);
        n2Out.next = n3Out;
        rootOut.left = n2Out;
        rootOut.right = n3Out;

        Node n4Out = new Node(4, null, null, null);
        Node n5Out = new Node(5, null, null, null);
        n4Out.next = n5Out;
        n2Out.left = n4Out;
        n2Out.right = n5Out;

        Node n6Out = new Node(6, null, null, null);
        Node n7Out = new Node(7, null, null, null);
        n6Out.next = n7Out;
        n3Out.left = n6Out;
        n3Out.right = n7Out;

        n5Out.next = n6Out;

        assertThat(connect(rootIn)).isEqualTo(rootOut);
    }
}
