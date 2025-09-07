package org.art.playground.misc.algo.trees;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * There are a total of numCourses courses you have to take, labeled from 0 to numCourses - 1.
 * You are given an array prerequisites where prerequisites[i] = [ai, bi] indicates
 * that you must take course bi first if you want to take course ai.
 * <p>
 * For example, the pair [0, 1], indicates that to take course 0 you have to first take course 1.
 * Return true if you can finish all courses. Otherwise, return false.
 */
public class m_6_CourseSchedule {

    public boolean canFinish(int numCourses, int[][] prerequisites) {
        Map<Integer, Set<Integer>> preMap = new HashMap<>();
        for (int i = 0; i < numCourses; i++) {
            preMap.put(i, new HashSet<>());
        }

        for (int[] course : prerequisites) {
            preMap.get(course[0]).add(course[1]);
        }

        Set<Integer> visited = new HashSet<>();

        for (int i = 0; i < numCourses; i++) {
            if (!dfs(i, preMap, visited)) {
                return false;
            }
        }

        return true;
    }

    private boolean dfs(int course, Map<Integer, Set<Integer>> preMap, Set<Integer> visited) {
        if (visited.contains(course)) {
            return false;
        }

        Set<Integer> prereqs = preMap.get(course);

        if (prereqs.isEmpty()) {
            return true;
        }

        visited.add(course);

        for (Integer prereq : prereqs) {
            boolean res = dfs(prereq, preMap, visited);
            if (!res) {
                return false;
            }
        }

        visited.remove(course);
        preMap.put(course, new HashSet<>());

        return true;
    }

    @Test
    void test0() {
        int[][] input = new int[][]{
            {1, 0},
            {0, 1}
        };

        assertThat(canFinish(2, input)).isFalse();
    }

    @Test
    void test1() {
        int[][] input = new int[][]{
            {1, 0}
        };

        assertThat(canFinish(2, input)).isTrue();
    }

    @Test
    void test2() {
        int[][] input = new int[][]{
            {0, 10}, {3, 18}, {5, 5}, {6, 11}, {11, 14}, {13, 1}, {15, 1}, {17, 4}
        };

        assertThat(canFinish(20, input)).isFalse();
    }
}
