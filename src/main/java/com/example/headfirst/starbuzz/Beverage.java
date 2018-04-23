package com.example.headfirst.starbuzz;

public abstract class Beverage {

    String description = "Unknown Beverage";

    public enum Size{TALL,GRANDE,VENTI}
    Size size = Size.TALL;

    public String getDescription(){
        return description;
    }

    public Size getSize() {
        return size;
    }

    public void setSize(Size size) {
        this.size = size;
    }

    public abstract double cost();
}
