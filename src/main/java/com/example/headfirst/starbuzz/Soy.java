package com.example.headfirst.starbuzz;

public class Soy extends CondimentDecrator {

    Beverage beverage;

    public Soy(Beverage beverage) {
        this.beverage = beverage;
    }

    @Override
    public String getDescription() {
        return beverage.getDescription() +",Soy";
    }

    @Override
    public double cost() {
        double cost = beverage.cost();
        if (beverage.size==Size.TALL){
            cost += .10;
        } else if (beverage.size==Size.GRANDE){
            cost += .15;
        } else if (beverage.size == Size.VENTI){
            cost += .20;
        }
        return cost;
    }
}
