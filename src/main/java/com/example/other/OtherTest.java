package com.example.other;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class OtherTest {
    public static void main(String[] args) {
        String s = "a b d e";
        System.out.println(s.replace(" ","%20"));

    }

    public boolean isUnique(String s){
        if (s != null && s.length()>0){
            Set<Character> set = new HashSet<>();
            for (Character c: s.toCharArray()) {
                if (set.contains(c)){
                    return false;
                }else {
                    set.add(c);
                }
            }
        }
        return true;
    }
}
