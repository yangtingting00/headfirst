package com.example.headfirst.remoteControl;

public class CeilingFanOffCommand implements Command {
    CeilingFan ceilingFan;
    int preSpeed;

    public CeilingFanOffCommand(CeilingFan ceilingFan) {
        this.ceilingFan = ceilingFan;
    }

    @Override
    public void execute() {
        preSpeed = ceilingFan.getSpeed();
        ceilingFan.off();
    }

    @Override
    public void undo() {
        if (preSpeed == CeilingFan.HIGH){
            ceilingFan.high();
        } else if (preSpeed == CeilingFan.MEDIUM){
            ceilingFan.medium();
        } else if (preSpeed == CeilingFan.LOW){
            ceilingFan.low();
        } else if (preSpeed == CeilingFan.OFF){
            ceilingFan.off();
        }
    }
}
