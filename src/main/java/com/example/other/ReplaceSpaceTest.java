package com.example.other;

public class ReplaceSpaceTest {
    public String replaceSpace(StringBuffer str) {
        int P1 = str.length() - 1;
        for (int i = 0; i <= P1; i++)
            if (str.charAt(i) == ' ')
                str.append("  ");

        int P2 = str.length() - 1;
        while (P1 >= 0 && P2 > P1) {
            char c = str.charAt(P1--);
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

    public static void main(String[] args) {
        ReplaceSpaceTest replaceSpaceTest = new ReplaceSpaceTest();
        String s = "a b c ";
        int[] a = {1,2,3};
        String string = s.replaceAll(" ","%20");
//        System.out.println(replaceSpaceTest.replaceSpace(new StringBuffer(s)));
    }
}
