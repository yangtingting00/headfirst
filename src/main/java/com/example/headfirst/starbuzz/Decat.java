package com.example.headfirst.starbuzz;

public class Decat extends Beverage {

    public Decat(){
        description = "Decat Coffee";
    }

    @Override
    public double cost() {
        return 2.2;
    }
}
