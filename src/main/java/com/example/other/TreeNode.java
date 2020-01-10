package com.example.other;

public class TreeNode<E> {
    TreeNode<E> left = null;
    TreeNode<E> right = null;
    E val;
    public TreeNode(E val){
        this.val = val;
    }
}
