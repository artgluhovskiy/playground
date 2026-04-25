package org.art.playground.misc.algo.trees_and_graphs;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Given n nodes labeled from 0 to n - 1 and a list of undirected edges (each edge is a pair of nodes),
 * check whether these edges make up a valid tree.
 * <p>
 * Idea: a tree is exactly a connected, acyclic undirected graph. For n &gt; 0, that is equivalent to:
 * (1) n − 1 edges, and (2) the graph is connected, or: (1) no cycles, and (2) connected.
 * This solution uses: edge count, then a single DFS to detect a cycle and to count reachability.
 */
public class m_12_GraphValidTree {

    public boolean validTree(int n, int[][] edges) {
        if (n == 0) {
            return edges.length == 0; // no nodes, no edges
        }
        // A tree on n nodes has n − 1 undirected edges; more ⇒ cycle, fewer ⇒ cannot be connected.
        if (edges.length != n - 1) {
            return false;
        }

        Map<Integer, Set<Integer>> adj = new HashMap<>();
        for (int[] e : edges) {
            int u = e[0], v = e[1];
            adj.computeIfAbsent(u, _ -> new HashSet<>()).add(v);
            adj.computeIfAbsent(v, _ -> new HashSet<>()).add(u);
        }

        // One DFS from 0: on an undirected graph, skip the parent so the edge back to parent is not a cycle.
        Set<Integer> visited = new HashSet<>();
        return dfs(0, -1, adj, visited) && visited.size() == n;
    }

    /**
     * @return true if the component explored from {@code node} is acyclic, false if a back-edge is found
     */
    private boolean dfs(int node, int parent, Map<Integer, Set<Integer>> adj, Set<Integer> visited) {
        if (visited.contains(node)) {
            return false; // re-entered via a path other than parent ⇒ cycle
        }
        visited.add(node);

        for (int next : adj.getOrDefault(node, Set.of())) {
            if (next != parent && !dfs(next, node, adj, visited)) {
                return false;
            }
        }
        return true;
    }

    @Test
    void test0() {
        assertThat(validTree(5, new int[][]{
            {0, 1}, {0, 2}, {0, 3}, {1, 4}
        })).isTrue();
    }

    @Test
    void test1() {
        assertThat(validTree(5, new int[][]{
            {0, 1}, {1, 2}, {2, 3}, {1, 3}, {1, 4}
        })).isFalse();
    }

    @Test
    void test2() {
        assertThat(validTree(3, new int[][]{
            {0, 2}, {1, 2}
        })).isTrue();
    }

    @Test
    void singleNode_isTree() {
        assertThat(validTree(1, new int[][]{})).isTrue();
    }
}
