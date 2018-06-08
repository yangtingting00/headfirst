package com.example.headfirst.pizza;

@Deprecated
public class SimplePizzaFactory {
    Pizza pizza = null;
    public Pizza createPizza(String type){
        /*if (type.equals("Cheese")){
            pizza = new CheesePizza();
        }else if (type.equals("Veggie")){
            pizza = new VeggiePizza();
        }else if (type.equals("Clam")){
            pizza = new ClamPizza();
        }else if (type.equals("Pepperoni")){
            pizza = new PepperoniPizza();
        }*/
        return  pizza;
    }
}
