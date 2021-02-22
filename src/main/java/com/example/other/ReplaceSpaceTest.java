package com.example.other;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ReplaceSpaceTest {
    public String replaceSpace(StringBuffer str) {
        int P1 = str.length() - 1;
        for (int i = 0; i <= P1; i++)
            if (str.charAt(i) == ' ')
                str.append("  ");

        int P2 = str.length() - 1;
        for (int i = P1; i >=0;i--) {
            char c = str.charAt(i);
            if (c == ' ') {
                str.setCharAt(P2--, '0');
                str.setCharAt(P2--, '2');
                str.setCharAt(P2--, '%');
            } else {
                str.setCharAt(P2--, c);
            }
        }
        return str.toString();
    }
    public String compressString(String S) {
        char[] ch = S.toCharArray();
        StringBuffer s = new StringBuffer();

        int i = 0,j = 0;
        s.append(j);
        while (j < S.length() - 1) {
            if (ch[j] != ch[j + 1]) {
                s.append(ch[i]+(j-i+1));
                i = j + 1;
            }
            j++;
        }

        s.append(ch[i] + (j - i + 1));
        return s.length() < S.length() ? s.toString() : S;
    }
    public String replaceSpaces(String S, int length) {
        char[] ch = S.toCharArray();
        int count = 0;
        for(int c : ch  ){
            if(c == ' '){
                count++;
            }
        }
        char[] newCh = new char[length + count*2];
        int j = length + count*2 - 1;
        for( int i = length - 1; i >=0; i--) {
            if(ch[i] == ' '){
                newCh[j] = '0';
                newCh[j-1] = '2';
                newCh[j-2] = '%';
                j = j - 3;
            } else {
                newCh[j] = ch[i];
                j --;
            }
        }

        return String.valueOf(newCh).trim();
    }
    public void setZeroes(int[][] matrix) {
        int rows = matrix.length;
        int cols = matrix[0].length;
        List<Integer> zeroRow = new ArrayList();
        List<Integer> zeroCols = new ArrayList();
        for(int row = 0;row < rows;row++){
            for(int col = 0;col<cols;col++){
                if(matrix[row][col]==0){
                    zeroRow.add(row);
                    zeroCols.add(col);
                }
            }
        }
        for(int row:zeroRow){
            for(int col = 0;col<cols;col++){
                matrix[row][col] = 0;
            }
        }
        for(int col:zeroCols){
            for(int row = 0;row < rows;row++){
                matrix[row][col] = 0;
            }
        }
    }

    public static void main(String[] args) {
        ReplaceSpaceTest replaceSpaceTest = new ReplaceSpaceTest();
        String s = "aabcccccaa";
        int[] a = {1,2,3};
//        System.out.println(replaceSpaceTest.replaceSpaces(s,27));
        System.out.println(replaceSpaceTest.compressString(s));
//        String string = s.replace(" ","%20");
//        System.out.println(replaceSpaceTest.replaceSpace(new StringBuffer(s)));
    }
}
