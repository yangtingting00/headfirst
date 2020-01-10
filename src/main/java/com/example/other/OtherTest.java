package com.example.other;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class OtherTest {
    public static void main(String[] args) {
        /*Demo demo = new Demo();
        Demo demo1 = null;
        System.out.println(demo instanceof Demo);
        System.out.println(demo1 instanceof Demo);

        List list = new ArrayList();
        ArrayList list1 = new ArrayList();
        System.out.println(list instanceof List);
        System.out.println(list instanceof ArrayList);
        System.out.println(list1 instanceof List);
        System.out.println(list1 instanceof ArrayList);
        ExecutorService executorService = Executors.newCachedThreadPool();
        executorService.execute(()->{
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        Thread a = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("ddd");
            }
        });
        try {
            a.wait();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/
        ForkJoinExample example = new ForkJoinExample();
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        Future result = forkJoinPool.submit(example);
        try {
            result.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}
