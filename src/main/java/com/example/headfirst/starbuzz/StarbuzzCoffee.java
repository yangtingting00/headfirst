package com.example.headfirst.starbuzz;

public class StarbuzzCoffee {
    public static void main(String[] args) {
        Beverage beverage = new Espresso();
        System.out.println(beverage.getDescription()+"  "+beverage.getSize()+"   $"+beverage.cost());

        beverage = new Soy(beverage);
        beverage = new Whip(beverage);
        beverage = new Mocha(beverage);
        beverage = new Soy(beverage);
        System.out.println(beverage.getDescription()+"  "+beverage.getSize()+"   $"+beverage.cost());

    }
}
