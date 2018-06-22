package com.example.headfirst.restaurant;

import java.util.Iterator;

public abstract class MenuComponent {
    /***************************组合方法begin*********************************/
    public void add(MenuComponent menuComponent){
        throw new UnsupportedOperationException();
    }
    public void remove(MenuComponent menuComponent){
        throw new UnsupportedOperationException();
    }
    public MenuComponent getChild(int i){
        throw new UnsupportedOperationException();
    }
    /*****************************组合方法end************************************/

    /*****************************操作方法begin**********************************/
    public String getName(){
        throw new UnsupportedOperationException();
    }
    public String getDescription(){
        throw new UnsupportedOperationException();
    }
    public double getPrice(){
        throw new UnsupportedOperationException();
    }
    public boolean isVegetarian(){
        throw new UnsupportedOperationException();
    }
    public void print(){
        throw new UnsupportedOperationException();
    }
    /*****************************操作方法end************************************/

    public abstract Iterator createIterator();
}
