package com.example.headfirst.starbuzz2;

public abstract class CaffeineBeverageWithHook {
     final void prepareRecipe(){
         boilWater();
         brew();
         pourInCup();
         if (customerWantsCondiments())
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

    /**
     * 用户是否想要调味料
     * @return
     */
    boolean customerWantsCondiments(){
        return true;
    }


}
