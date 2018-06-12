package com.example.headfirst.remoteControl;

public class Light {
    String name;

    public Light(String name) {
        this.name = name;
    }

    public void on(){
        System.out.println(name+" light is On");
    }
    public void off(){
        System.out.println(name+" light is Off");
    }
}
