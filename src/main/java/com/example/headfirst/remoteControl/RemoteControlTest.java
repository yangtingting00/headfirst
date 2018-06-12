package com.example.headfirst.remoteControl;

public class RemoteControlTest {
    public static void main(String[] args) {
        RemoteControl remote = new RemoteControl();
        Light livingRoomLight = new Light("Living Room");
        /*Light kitchenLight = new Light("kitchen");
        Stereo stereo = new Stereo();*/

        LightOnCommand livingRoomLightOn = new LightOnCommand(livingRoomLight);
        LightOffCommand livingRoomLightOff = new LightOffCommand(livingRoomLight);
        /*LightOnCommand kitchenLightOn = new LightOnCommand(kitchenLight);
        LightOffCommand kitchenLightOff = new LightOffCommand(kitchenLight);
        StereoOnWhithCDCommand stereoOnWhithCDCommand = new StereoOnWhithCDCommand(stereo);
        StereoOffCommand stereoOffCommand = new StereoOffCommand(stereo);*/

        remote.setCommand(0,livingRoomLightOn,livingRoomLightOff);
        /*remote.setCommand(1,kitchenLightOn,kitchenLightOff);
        remote.setCommand(2,stereoOnWhithCDCommand,stereoOffCommand);*/

//        System.out.println(remote);

        remote.onButtonWasPushed(0);
        remote.offButtonWasPushed(0);
        System.out.println(remote);
        remote.undoButtonWasPushed();
        remote.offButtonWasPushed(0);
        remote.onButtonWasPushed(0);
        System.out.println(remote);
        remote.undoButtonWasPushed();
        /*remote.onButtonWasPushed(1);
        remote.offButtonWasPushed(1);
        remote.onButtonWasPushed(2);
        remote.offButtonWasPushed(2);*/
    }
}
