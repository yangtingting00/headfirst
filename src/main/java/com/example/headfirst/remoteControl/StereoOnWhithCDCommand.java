package com.example.headfirst.remoteControl;

public class StereoOnWhithCDCommand implements Command {
    Stereo stereo;

    public StereoOnWhithCDCommand(Stereo stereo) {
        this.stereo = stereo;
    }

    @Override
    public void execute() {
        stereo.on();
        stereo.setCd();
        stereo.setVolume(11);
    }

    @Override
    public void undo() {

    }
}
