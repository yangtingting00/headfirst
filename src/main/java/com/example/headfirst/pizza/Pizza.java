package com.example.headfirst.pizza;

import com.example.headfirst.pizza.ingredient.*;

public abstract class Pizza {

    String name;    //名称
    Dough dough;   //面团
    Sauce sauce;   //酱料
    Veggies veggies[];  //蔬菜
    Cheese cheese;  //芝士
    Pepperoni pepperoni;    //意大利辣香肠
    Clams clam;    //蛤蚌

    /**
     * 准备
     */
    public abstract void prepare();

    /**
     * 烤
     */
    public void bake(){
        System.out.println("Bake for 25 minutes at 350");
    }

    /**
     * 切
     */
    public void cut(){
        System.out.println("Cutting the pizza into diagonal slices");
    }

    /**
     * 装盒
     */
    public void box(){
        System.out.println("Place pizza in official PizzaStore box");
    }

    public String getName() {
        return name;
    }
}
