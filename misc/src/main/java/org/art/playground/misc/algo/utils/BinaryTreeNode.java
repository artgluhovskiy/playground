package org.art.playground.misc.algo.utils;

import java.util.Objects;
import java.util.stream.Stream;

import lombok.Getter;

import static java.util.stream.Collectors.toList;

/**
 * Binary Tree node implementation.
 */
@Getter
public class BinaryTreeNode<T> extends TreeNode<T> {

    public BinaryTreeNode<T> left;

    public BinaryTreeNode<T> right;

    public BinaryTreeNode<T> parent;

    public BinaryTreeNode(T elem, BinaryTreeNode<T> left, BinaryTreeNode<T> right) {
        super(elem, Stream.of(left, right).filter(Objects::nonNull).collect(toList()));
        this.left = left;
        if (left != null) {
            this.left.setParent(this);
        }
        this.right = right;
        if (right != null) {
            this.right.setParent(this);

        }
    }

    public void setParent(BinaryTreeNode<T> parent) {
        this.parent = parent;
    }

    @Override
    public String toString() {
        return "TreeNode {" +
            " elem = " + getVal() +
            ", left value = " + (left != null ? left.getVal() : "null") +
            ", right value = " + (right != null ? right.getVal() : "null") +
            ", parent value = " + (parent != null ? parent.getVal() : "null") +
            " }";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        BinaryTreeNode<T> that = (BinaryTreeNode<T>) o;
        return getVal() == that.getVal() && Objects.equals(left, that.left) && Objects.equals(right, that.right);
    }

    @Override
    public int hashCode() {
        return Objects.hash(getVal(), left, right, parent);
    }
}
