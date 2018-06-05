package com.example.headfirst.pizza;

public abstract class PizzaStore {

    public final Pizza orderPizza(String type){
        Pizza pizza = this.createPizza(type);
        pizza.prepare();
        pizza.bake();
        pizza.cut();
        pizza.box();
        return pizza;
    }

    public abstract Pizza createPizza(String type);
}
