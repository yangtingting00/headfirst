package com.example.headfirst.remoteControl;

public interface Command {
    void execute();
    void undo();
}
