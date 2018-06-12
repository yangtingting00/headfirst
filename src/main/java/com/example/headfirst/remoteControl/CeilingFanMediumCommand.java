package com.example.headfirst.remoteControl;

public class CeilingFanMediumCommand implements Command {
    CeilingFan ceilingFan;
    int preSpeed;

    public CeilingFanMediumCommand(CeilingFan ceilingFan) {
        this.ceilingFan = ceilingFan;
    }

    @Override
    public void execute() {
        preSpeed = ceilingFan.getSpeed();
        ceilingFan.medium();
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
