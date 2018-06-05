package com.example.headfirst.pizza;

public class PizzaStoreTest {
    public static void main(String[] args) {
        PizzaStore nyStore = new NYPizzaStore();
        nyStore.orderPizza("Veggie");

        PizzaStore chicagoStore = new ChicagoPizzaStore();
        chicagoStore.orderPizza("Veggie");
    }
}
