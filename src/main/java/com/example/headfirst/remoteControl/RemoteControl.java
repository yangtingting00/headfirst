package com.example.headfirst.remoteControl;

import java.util.Stack;

public class RemoteControl {
    Command[] onCommands;
    Command[] offCommands;
    Stack<Command> undoCommands;
//    Command undoCommand;

    public RemoteControl() {
        onCommands = new Command[7];
        offCommands = new Command[7];
        Command noCommand = new NoCommand();
        for (int i = 0; i < 7; i++) {
            onCommands[i] = noCommand;
            offCommands[i] = noCommand;
        }
//        undoCommand = noCommand;
        undoCommands = new Stack<Command>();
    }

    public void setCommand(int slot, Command onCommand, Command offCommand){
        onCommands[slot] = onCommand;
        offCommands[slot] = offCommand;
    }

    public void onButtonWasPushed(int slot){
        onCommands[slot].execute();
//        undoCommand = onCommands[slot];
        undoCommands.push(onCommands[slot]);
    }
    public void offButtonWasPushed(int slot){
        offCommands[slot].execute();
//        undoCommand = offCommands[slot];
        undoCommands.push(onCommands[slot]);
    }

    public void undoButtonWasPushed(){
//        undoCommand.undo();
        if (undoCommands.size()>0){
            undoCommands.pop().undo();
        }
    }

    @Override
    public String toString() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("\n---------------remote Control ----------------\n");
        for (int i = 0; i < onCommands.length; i++) {
            stringBuffer.append("[slot "+i+"]" + onCommands[i].getClass().getName()+"   " + offCommands[i].getClass().getName()+"\n");
        }
//        stringBuffer.append("[undo]" + undoCommand.getClass().getName()+"\n");
        return stringBuffer.toString();
    }
}
