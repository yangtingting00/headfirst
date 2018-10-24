package com.example.other;

import java.util.ArrayList;
import java.util.List;

public class OtherTest {
    public static void main(String[] args) {
        Demo demo = new Demo();
        Demo demo1 = null;
        System.out.println(demo instanceof Demo);
        System.out.println(demo1 instanceof Demo);

        List list = new ArrayList();
        ArrayList list1 = new ArrayList();
        System.out.println(list instanceof List);
        System.out.println(list instanceof ArrayList);
        System.out.println(list1 instanceof List);
        System.out.println(list1 instanceof ArrayList);
    }
}
