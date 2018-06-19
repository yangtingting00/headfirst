package com.example.headfirst.restaurant;

import java.util.Iterator;

public class DinerMenuIterator implements Iterator {
    MenuItem[] menuItems;
    int position = 0;

    public DinerMenuIterator(MenuItem[] menuItems) {
        this.menuItems = menuItems;
    }

    @Override
    public boolean hasNext() {
        if (position >= menuItems.length || menuItems[position] == null){
            return false;
        }else {
            return true;
        }
    }

    @Override
    public Object next() {
        MenuItem menuItem = menuItems[position];
        position = position+1;
        return menuItem;
    }

    @Override
    public void remove() {
        if (position < 0){
            throw new IllegalStateException("You can't remove an item util you've done at least one next()");
        }
        if (menuItems[position] != null){
            for (int i = position; i < menuItems.length; i++) {
                menuItems[i] = null;
            }
        }
    }
}
