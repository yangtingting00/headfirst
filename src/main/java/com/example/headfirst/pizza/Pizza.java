package com.example.headfirst.pizza;

import java.util.ArrayList;

public class Pizza {

    String name;    //名称
    String dough;   //面团
    String sauce;   //酱料
    ArrayList toppings = new ArrayList();   //作料

    /**
     * 准备
     */
    public void prepare(){
        System.out.println("Prepare "+name);
        System.out.println("Tossing dough:"+dough);
        System.out.println("Adding sauce:"+sauce);
        System.out.println("Adding toppings:");
        for (int i = 0; i < toppings.size(); i++) {
            System.out.println("    "+toppings.get(i));
        }
    }

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
