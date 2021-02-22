package com.example.offer;

import java.util.HashMap;
import java.util.Map;

public class OfferTest {
    private static Map<Integer,Integer> indexForInOrder = new HashMap<>();
    public static void main(String[] args) {
        //查找数组中重复数字
        int[] num = {2,3,1,0,4,5};
//        System.out.println(duplicate(num));

        //在二维数组中查找某个数是否存在
        int[][] matrix = {
                {1, 4, 7, 11, 15},
                {2, 5, 8, 12, 19},
                {3, 6, 9, 16, 22},
                {10, 13, 14, 17, 24},
                {18, 21, 23, 26, 30}
        };
        int target = 4;
//        System.out.println(find(target,matrix));

        //替换空格
        String s = "A B C";
//        System.out.println(replaceSpace(new StringBuffer(s)));
        int[] preOrder = {1,2,4,7,3,5,6,8};
        int[] inOrder = {4,7,2,1,5,3,8,6};
        BinaryTreeNode binaryTreeNode = construct(preOrder,inOrder);
//        preOrderTraverse(binaryTreeNode);
        inOrderTraverse(binaryTreeNode);

    }

    /**
     * 查找数组中重复数字
     * @param num
     * @return
     */
    public static Integer duplicate(int[] num){
        if (num != null && num.length >0){
            for (int i = 0; i < num.length; i++) {
                while (num[i] != i){
                    if (num[i] == num[num[i]]){
                        return num[i];
                    }else {
                        int tmp = num[i];
                        num[i] = num[tmp];
                        num[tmp] = tmp;
                    }
                }
            }
        }
        return null;
    }

    /**
     * 在二维数组中查找某个数是否存在
     * @param target
     * @param matrix
     * @return
     */
    public static boolean find(int target,int[][] matrix){
        if (matrix==null || matrix.length==0 || matrix[0].length==0){
            return false;
        }
        int rows = matrix.length,cols = matrix[0].length;
        //从右上角开始查找
        /*int r = 0,c = cols-1;
        while (r < rows && c >= 0){
            if (target == matrix[r][c]){
                return true;
            } else if (target < matrix[r][c]){
                c --;
            } else {
                r ++;
            }
        }*/
        //从左下角开始找
        int r = rows -1, c = 0;
        while (r >= 0 && c < cols){
            if (target == matrix[r][c]){
                return true;
            } else if (target > matrix[r][c]){
                c ++;
            } else {
                r --;
            }
        }
        return false;
    }

    /**
     * 将字符串中的空格替换成%20
     * @param str
     * @return
     */
    public static String replaceSpace(StringBuffer str){
        //每发现一个空格在尾部填充两个空格
        int l1 = str.length()-1;    //原始最后一位字符下标
        for (int i = 0; i < l1; i++) {
            if (str.charAt(i) == ' '){
                str.append("  ");
            }
        }
        int l2 = str.length()-1;    //新字符串最后一位字符下标
        while (l1 >=0 && l2 > l1){
            char c = str.charAt(l1--);
            if (c == ' '){
                str.setCharAt(l2--,'0');
                str.setCharAt(l2--,'2');
                str.setCharAt(l2--,'%');
            } else {
                str.setCharAt(l2--,c);
            }
        }
        return str.toString();
    }

    /**
     * 重建二叉树
     * @param preOrder
     * @param inOrder
     * @return
     */
    public static BinaryTreeNode construct(int[] preOrder, int[] inOrder){
        for (int i = 0; i < inOrder.length; i++) {
            indexForInOrder.put(inOrder[i],i);
        }
        return construct(preOrder,0,preOrder.length-1,0);
    }

    private static BinaryTreeNode construct(int[] preOrder,int startPre,int entPre,int startIn){
        if (startPre > entPre)
            return null;
        BinaryTreeNode binaryTreeNode = new BinaryTreeNode();
        binaryTreeNode.value = preOrder[startPre];
        int indexForIn = indexForInOrder.get(preOrder[startPre]);
        int leftTreeSize = indexForIn - startIn;
        binaryTreeNode.leftNode = construct(preOrder,startPre+1,startPre+leftTreeSize,startIn);
        binaryTreeNode.rightNode = construct(preOrder,startPre+leftTreeSize+1,entPre,startIn+leftTreeSize+1);

        return binaryTreeNode;
    }

    /**
     * 前序打印二叉树
     * @param binaryTreeNode
     */
    public static void preOrderTraverse(BinaryTreeNode binaryTreeNode) {
        if(binaryTreeNode==null)
            return;
        System.out.println(binaryTreeNode.value);
        preOrderTraverse(binaryTreeNode.leftNode);
        preOrderTraverse(binaryTreeNode.rightNode);
    }

    /**
     * 中序打印二叉树
     * @param binaryTreeNode
     */
    public static void inOrderTraverse(BinaryTreeNode binaryTreeNode) {
        if(binaryTreeNode==null)
            return;
        inOrderTraverse(binaryTreeNode.leftNode);
        System.out.println(binaryTreeNode.value);
        inOrderTraverse(binaryTreeNode.rightNode);
    }

    /**
     * 后续打印二叉树
     * @param binaryTreeNode
     */
    public static void postOrderTraverse(BinaryTreeNode binaryTreeNode) {
        if(binaryTreeNode==null)
            return;
        postOrderTraverse(binaryTreeNode.leftNode);
        postOrderTraverse(binaryTreeNode.rightNode);
        System.out.println(binaryTreeNode.value);
    }



}
