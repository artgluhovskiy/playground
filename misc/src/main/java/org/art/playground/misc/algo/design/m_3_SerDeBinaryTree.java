package org.art.playground.misc.algo.design;

import com.sun.source.tree.Tree;
import lombok.EqualsAndHashCode;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Design an algorithm to serialize and deserialize a binary tree.
 * There is no restriction on how your serialization/deserialization algorithm should work.
 * You just need to ensure that a binary tree can be serialized to a string
 * and this string can be deserialized to the original tree structure.
 */
public class m_3_SerDeBinaryTree {

    @EqualsAndHashCode
    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    static class Codec {

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
    }

    @Test
    void test0() {
        TreeNode root = new TreeNode(1);
        TreeNode left = new TreeNode(2);
        TreeNode right = new TreeNode(3);
        root.left = left;
        root.right = right;

        Codec ser = new Codec();
        Codec deser = new Codec();

        TreeNode ans = deser.deserialize(ser.serialize(root));

        assertThat(root).isEqualTo(ans);
    }

    @Test
    void test1() {
        TreeNode root = new TreeNode(1);
        TreeNode left = new TreeNode(2);
        TreeNode right = new TreeNode(3);
        root.left = left;
        root.right = right;

        Codec ser = new Codec();

        assertThat(ser.serialize(root)).isEqualTo("1,2,X,X,3,X,X");
    }

    @Test
    void test2() {
        TreeNode root = new TreeNode(1);
        TreeNode l1 = new TreeNode(2);
        TreeNode r1 = new TreeNode(3);
        TreeNode r12 = new TreeNode(4);
        root.left = l1;
        root.right = r1;
        r1.right = r12;

        Codec ser = new Codec();

        assertThat(ser.serialize(root)).isEqualTo("1,2,X,X,3,X,4,X,X");
    }

    @Test
    void test3() {
        TreeNode root = null;

        Codec ser = new Codec();

        assertThat(ser.serialize(root)).isEqualTo("X");
    }

    @Test
    void test4() {
        TreeNode root = new TreeNode(1);
        TreeNode right = new TreeNode(3);
        root.right = right;

        Codec deser = new Codec();

        assertThat(deser.deserialize("1,X,3,X,X")).isEqualTo(root);
    }

    @Test
    void test5() {
        Codec deser = new Codec();

        assertThat(deser.deserialize("X")).isEqualTo(null);
    }
}
