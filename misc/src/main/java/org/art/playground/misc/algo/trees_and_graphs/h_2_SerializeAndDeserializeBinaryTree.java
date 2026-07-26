package org.art.playground.misc.algo.trees_and_graphs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Serialization is the process of converting a data structure or object into
 * a sequence of bits so that it can be stored in a file or memory buffer,
 * or transmitted across a network connection link to be reconstructed later
 * in the same or another computer environment.
 * Design an algorithm to serialize and deserialize a binary tree.
 * There is no restriction on how your serialization/deserialization algorithm should work.
 * You just need to ensure that a binary tree can be serialized to a string and this string
 * can be deserialized to the original tree structure.
 * Clarification: The input/output format is the same as how LeetCode serializes a binary tree.
 * You do not necessarily need to follow this format, so please be creative and
 * come up with different approaches yourself.
 */
public class h_2_SerializeAndDeserializeBinaryTree {
    private static final String NULL = "X";
    private static final String DEL = ",";

    public String serialize(TreeNode root) {
        List<String> vals = new ArrayList<>();

        bfsSer(root, vals);

        return String.join(DEL, vals);
    }

    private void bfsSer(TreeNode root, List<String> vals) {
        if (root == null) {
            vals.add(NULL);
            return;
        }

        vals.add(String.valueOf(root.val));
        bfsSer(root.left, vals);
        bfsSer(root.right, vals);
    }

    public TreeNode deserialize(String data) {
        Queue<String> vals = new LinkedList<>(Arrays.asList(data.split(DEL)));
        return bfsDeser(vals);
    }

    private TreeNode bfsDeser(Queue<String> vals) {
        String val = vals.poll();
        if (NULL.equals(val)) {
            return null;
        }

        TreeNode node = new TreeNode(Integer.parseInt(val));
        node.left = bfsDeser(vals);
        node.right = bfsDeser(vals);

        return node;
    }

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }
}
