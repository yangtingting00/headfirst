package com.example.headfirst.combining;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * 组合模式+迭代器模式
 */
public class Flock implements Quackable {
    ArrayList quackers = new ArrayList();


    public void add(Quackable quacker){
        quackers.add(quacker);
    }

    @Override
    public void quack() {
        Iterator iterator = quackers.iterator();
        while (iterator.hasNext()){
            Quackable quackable = (Quackable)iterator.next();
            quackable.quack();
        }
    }

    @Override
    public void registerObserver(Observer observer) {
        Iterator iterator = quackers.iterator();
        while (iterator.hasNext()){
            Quackable quackable = (Quackable)iterator.next();
            quackable.registerObserver(observer);
        }
    }

    @Override
    public void notifyObservers() {
    }
}
