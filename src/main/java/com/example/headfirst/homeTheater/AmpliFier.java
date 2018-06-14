package com.example.headfirst.homeTheater;

/**
 * 扩音器
 */
public class AmpliFier {
    public void on(){
        System.out.println("Amplifier on");
    }
    public void setDvd(DvdPlayer dvd){
        System.out.println("Amplifier dvd ");
    }
    public void setSurroundSound(){
        System.out.println("Amplifier set surround sound");
    }
    public void setVolume(int volume){
        System.out.println("Amplifier set volume:"+volume);
    }

    public void off(){
        System.out.println("Amplifier off");
    }
}
