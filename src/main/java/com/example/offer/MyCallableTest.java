package com.example.offer;

import java.util.Collections;
import java.util.TreeSet;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;
import java.util.concurrent.locks.ReentrantLock;

public class MyCallableTest {
    public static void main(String[] args) throws InterruptedException,ExecutionException {
        MyCallable mc = new MyCallable();
        FutureTask<String> f = new FutureTask<>(mc);
        new Thread(f).start();
        System.out.println(f.get());
    }
}
