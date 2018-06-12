package com.example.headfirst.remoteControl;

public class RemoteControlTest {
    public static void main(String[] args) {
        RemoteControl remote = new RemoteControl();
        CeilingFan ceilingFan = new CeilingFan("Living Room");
        CeilingFanHighCommand highCommand = new CeilingFanHighCommand(ceilingFan);
        CeilingFanMediumCommand mediumCommand = new CeilingFanMediumCommand(ceilingFan);
        CeilingFanLowCommand lowCommand = new CeilingFanLowCommand(ceilingFan);
        CeilingFanOffCommand offCommand = new CeilingFanOffCommand(ceilingFan);

        remote.setCommand(0,highCommand,offCommand);
        remote.setCommand(1,mediumCommand,offCommand);
        remote.setCommand(2,lowCommand,offCommand);

        /*Light livingRoomLight = new Light("Living Room");
        Light kitchenLight = new Light("kitchen");
        Stereo stereo = new Stereo();

        LightOnCommand livingRoomLightOn = new LightOnCommand(livingRoomLight);
        LightOffCommand livingRoomLightOff = new LightOffCommand(livingRoomLight);
        LightOnCommand kitchenLightOn = new LightOnCommand(kitchenLight);
        LightOffCommand kitchenLightOff = new LightOffCommand(kitchenLight);
        StereoOnWhithCDCommand stereoOnWhithCDCommand = new StereoOnWhithCDCommand(stereo);
        StereoOffCommand stereoOffCommand = new StereoOffCommand(stereo);

        remote.setCommand(0,livingRoomLightOn,livingRoomLightOff);
        remote.setCommand(1,kitchenLightOn,kitchenLightOff);
        remote.setCommand(2,stereoOnWhithCDCommand,stereoOffCommand);*/

        System.out.println(remote);

        remote.onButtonWasPushed(0);
        remote.offButtonWasPushed(0);
        System.out.println(remote);
        remote.undoButtonWasPushed();
        remote.onButtonWasPushed(1);
        remote.offButtonWasPushed(1);
        System.out.println(remote);
        remote.undoButtonWasPushed();
        remote.onButtonWasPushed(2);
        remote.offButtonWasPushed(2);
        System.out.println(remote);
        remote.undoButtonWasPushed();
    }
}
