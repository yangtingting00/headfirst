package com.example.headfirst.starbuzz;

public class Mocha extends CondimentDecrator {

    Beverage beverage;

    public Mocha(Beverage beverage) {
        this.beverage = beverage;
    }

    @Override
    public String getDescription() {
        return beverage.getDescription() + ",Mocha";
    }

    @Override
    public double cost() {
        return .20+beverage.cost();
    }
}
