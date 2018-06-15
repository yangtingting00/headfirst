package com.example.headfirst.starbuzz2;

public class Coffee extends CaffeineBeverage {


    /**
     * 冲泡咖啡
     */
    public void brew(){
        System.out.println("Dripping  Coffee through filter");
    }


    /**
     * 添加糖和牛奶
     */
    public void addCondiments(){
        System.out.println("Adding Sugar and Milk");
    }
}
