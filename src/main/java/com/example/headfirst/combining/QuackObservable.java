package com.example.headfirst.combining;


/**
 * 观察者模式(被观察者)
 */
public interface QuackObservable {
    void registerObserver(Observer observer);
    void notifyObservers();
}
