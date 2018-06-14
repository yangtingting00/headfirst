package com.example.headfirst.homeTheater;

public class HomeTheaterFacade {

    AmpliFier ampliFier;
    Tuner tuner;
    DvdPlayer dvdPlayer;
    CdPlayer cdPlayer;
    Projector projector;
    TheaterLights theaterLights;
    Screen screen;
    PopcornPopper popcornPopper;

    public HomeTheaterFacade(AmpliFier ampliFier, Tuner tuner, DvdPlayer dvdPlayer, CdPlayer cdPlayer, Projector projector, TheaterLights theaterLights, Screen screen, PopcornPopper popcornPopper) {
        this.ampliFier = ampliFier;
        this.tuner = tuner;
        this.dvdPlayer = dvdPlayer;
        this.cdPlayer = cdPlayer;
        this.projector = projector;
        this.theaterLights = theaterLights;
        this.screen = screen;
        this.popcornPopper = popcornPopper;
    }

    public void watchMovie(String movie){
        System.out.println("Get ready to watch a movie ...");
        popcornPopper.on();
        popcornPopper.pop();
        theaterLights.dim();
        screen.down();
        projector.on();
        projector.wideScreenMode();
        ampliFier.on();
        ampliFier.setDvd(dvdPlayer);
        ampliFier.setSurroundSound();
        ampliFier.setVolume(5);
        dvdPlayer.on();
        dvdPlayer.play(movie);
    }

    public void endMovie(){
        System.out.println("Shutting movie theater down ...");
        popcornPopper.off();
        theaterLights.on();
        screen.up();
        projector.off();
        ampliFier.off();
        dvdPlayer.stop();
        dvdPlayer.eject();
        dvdPlayer.off();
    }
}
