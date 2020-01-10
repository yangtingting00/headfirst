package com.example.other;

import java.util.HashMap;
import java.util.Map;

public class TreeTest {

    public TreeNode reConstructBinaryTree(int[] pre, int[] in) {
        return reConstructBinaryTree(pre, 0, pre.length - 1,in, 0,in.length - 1);
    }

    private TreeNode reConstructBinaryTree(int[] pre, int startPre, int endPre, int[] in, int startIn, int endIn) {
        if (startPre > endPre || startIn > endIn)
            return null;
        TreeNode root = new TreeNode(pre[startPre]);

        for (int i = startIn; i <= endIn; i++)
            if (in[i] == pre[startPre]) {
                root.left = reConstructBinaryTree(pre, startPre + 1, startPre + i - startIn, in, startIn, i - 1);
                root.right = reConstructBinaryTree(pre, i - startIn + startPre + 1, endPre, in, i + 1, endIn);
                break;
            }

        return root;
    }
    public void preOrderTraverse(TreeNode node) {
        if(node==null)
            return;
        System.out.println(node.val);
        preOrderTraverse(node.left);
        preOrderTraverse(node.right);
    }
    public void inOrderTraverse(TreeNode node) {
        if(node==null)
            return;
        inOrderTraverse(node.left);
        System.out.println(node.val);
        inOrderTraverse(node.right);
    }
    public void postOrderTraverse(TreeNode node) {
        if(node==null)
            return;
        postOrderTraverse(node.left);
        postOrderTraverse(node.right);
        System.out.println(node.val);
    }

    public static void main(String[] args) {
        TreeTest treeTest = new TreeTest();
        int[] pre = {3,9,20,15,7};
        int[] in = {9,3,15,20,7};
        TreeNode node = treeTest.reConstructBinaryTree(pre,in);
        treeTest.preOrderTraverse(node);
        System.out.println();
        treeTest.inOrderTraverse(node);
        System.out.println();
        treeTest.postOrderTraverse(node);
    }
}
