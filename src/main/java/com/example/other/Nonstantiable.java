package com.example.other;

public class Nonstantiable {
    private Nonstantiable(){
        throw new AssertionError();
    }
}
