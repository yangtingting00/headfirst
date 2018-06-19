package com.example.headfirst.restaurant;

public class Waitress {
    DinerMenu dinerMenu;
    PancakeHouseMenu pancakeHouseMenu;

    public Waitress(DinerMenu dinerMenu, PancakeHouseMenu pancakeHouseMenu) {
        this.dinerMenu = dinerMenu;
        this.pancakeHouseMenu = pancakeHouseMenu;
    }

    public void printMenu(){
        Iterator pancakeIterator = dinerMenu.createIterator();
        Iterator dinerIterator = pancakeHouseMenu.createIterator();
        System.out.println("MENU\n----\nBREAKFAST");
        this.printMenu(pancakeIterator);
        System.out.println("\nLUNCH");
        this.printMenu(dinerIterator);
    }
    private void printMenu(Iterator iterator){
        while (iterator.hasNext()){
            MenuItem menuItem = (MenuItem) iterator.next();
            System.out.print(menuItem.getName()+",");
            System.out.print(menuItem.getPrice());
            System.out.println(menuItem.getDescription()+(menuItem.isVegetarian()?"(素食)":""));
        }
    }
}
