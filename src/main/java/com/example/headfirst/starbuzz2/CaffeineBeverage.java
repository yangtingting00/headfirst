package com.example.headfirst.starbuzz2;

public abstract class CaffeineBeverage {
     final void prepareRecipe(){
         boilWater();
         brew();
         pourInCup();
         addCondiments();
     }

    /**
     * 煮沸水
     */
    void boilWater(){
        System.out.println("Boiling water");
    }

    /**
     * 倒入杯子
     */
    void pourInCup(){
        System.out.println("Pouring into cup");
    }

    /**
     * 冲泡
     */
    abstract void brew();

    /**
     * 添加调料
     */
    abstract void addCondiments();


}
