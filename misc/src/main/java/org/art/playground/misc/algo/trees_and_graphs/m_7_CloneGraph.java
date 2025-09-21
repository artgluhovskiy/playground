package org.art.playground.misc.algo.trees_and_graphs;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Given a reference of a node in a connected undirected graph.
 * Return a deep copy (clone) of the graph.
 */
public class m_7_CloneGraph {

    public Node cloneGraph(Node node) {
        if (node == null) {
            return null;
        }

        Map<Integer, Node> visited = new HashMap<>();

        return doCloneGraph(node, visited);
    }

    private Node doCloneGraph(Node node, Map<Integer, Node> visited) {
        if (visited.containsKey(node.val)) {
            return visited.get(node.val);
        }

        Node copiedNode = new Node(node.val);
        visited.put(node.val, copiedNode);

        for (Node neighbor : node.neighbors) {
            copiedNode.neighbors.add(doCloneGraph(neighbor, visited));
        }

        return copiedNode;
    }

    @Test
    void test0() {
        Node n1 = new Node(1);
        Node n2 = new Node(2);
        Node n3 = new Node(3);
        Node n4 = new Node(4);

        n1.setNeighbors(List.of(n2, n4));
        n2.setNeighbors(List.of(n1, n3));
        n3.setNeighbors(List.of(n2, n4));
        n4.setNeighbors(List.of(n1, n3));

        Node cloned = cloneGraph(n1);

        System.out.println(cloned);
    }

    @Getter
    @Setter
    @EqualsAndHashCode
    static class Node {
        public int val;
        public List<Node> neighbors;

        public Node() {
            val = 0;
            neighbors = new ArrayList<Node>();
        }

        public Node(int _val) {
            val = _val;
            neighbors = new ArrayList<Node>();
        }

        public Node(int _val, ArrayList<Node> _neighbors) {
            val = _val;
            neighbors = _neighbors;
        }
    }
}

