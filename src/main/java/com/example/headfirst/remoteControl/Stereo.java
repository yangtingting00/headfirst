package com.example.headfirst.remoteControl;

public class Stereo {
    public void on(){
        System.out.println("Stereo is On");
    }
    public void off(){
        System.out.println("Stereo is Off");
    }
    public void setCd(){
        System.out.println("Stereo is set for CD input");
    }
    public void  setDvd(){
        System.out.println("Stereo is set for DVD input");
    }
    public void setRadio(){
        System.out.println("Stereo is set for RADIO input");
    }
    public void setVolume(int volume){
        System.out.println("Stereo volume set to "+volume);
    }
}
