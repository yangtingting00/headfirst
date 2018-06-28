package com.example.headfirst.combining;


/**
 * 观察者模式
 */
public interface QuackObservable {
    void registerObserver(Observer observer);
    void notifyObservers();
}
