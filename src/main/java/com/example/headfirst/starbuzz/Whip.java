package com.example.headfirst.starbuzz;

public class Whip extends CondimentDecrator {

    Beverage beverage;

    public Whip(Beverage beverage) {
        this.beverage = beverage;
    }

    @Override
    public String getDescription() {
        return beverage.getDescription()+",Whip";
    }

    @Override
    public double cost() {
        return .11+beverage.cost();
    }
}
