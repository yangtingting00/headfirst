package com.example.headfirst.pizza;

public class ChicagoPizzaStore extends PizzaStore {

    @Override
    public Pizza createPizza(String type) {
        if (type.equals("Cheese")){
            return new ChicagoStylePizza();
        }
        return null;
    }
}
