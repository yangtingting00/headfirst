package com.example.headfirst.pizza;

public class PizzaStoreTest {
    public static void main(String[] args) {
        PizzaStore nyStore = new NYPizzaStore();
        Pizza nyPizza = nyStore.orderPizza("Cheese");
        System.out.println("nyPizza======"+nyPizza.getName());

        PizzaStore chicagoStore = new ChicagoPizzaStore();
        Pizza chicagoPizza = chicagoStore.orderPizza("Cheese");
        System.out.println("chicagoPizza==========="+chicagoPizza.getName());

    }
}
