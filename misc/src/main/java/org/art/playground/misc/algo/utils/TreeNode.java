package org.art.playground.misc.algo.utils;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TreeNode<T> {

    public final T val;

    public List<TreeNode<T>> adjacentNodes;

    public boolean visited;

    public TreeNode(T val, List<TreeNode<T>> adjacentNodes) {
        this.val = val;
        this.adjacentNodes = adjacentNodes;
    }
}
