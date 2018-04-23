package com.example.headfirst.starbuzz;

public class HostBlend extends Beverage {

    public HostBlend(){
        description = "Host Blend Coffee";
    }

    @Override
    public double cost() {
        return .89;
    }
}
