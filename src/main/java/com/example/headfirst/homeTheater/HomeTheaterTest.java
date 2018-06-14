package com.example.headfirst.homeTheater;

public class HomeTheaterTest {
    public static void main(String[] args) {
        AmpliFier ampliFier = new AmpliFier();
        DvdPlayer dvdPlayer = new DvdPlayer();
        CdPlayer cdPlayer = new CdPlayer();
        PopcornPopper popcornPopper = new PopcornPopper();
        Projector projector = new Projector();
        Screen screen = new Screen();
        TheaterLights theaterLights = new TheaterLights();
        Tuner tuner = new Tuner();
        HomeTheaterFacade homeTheaterFacade = new HomeTheaterFacade(ampliFier,tuner,dvdPlayer,cdPlayer,projector,theaterLights,screen,popcornPopper);
        homeTheaterFacade.watchMovie("haha");
        homeTheaterFacade.endMovie();
    }
}
