package com.example.headfirst.proxy.candyStore;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 * 糖果机(远程代理)
 */
public class GumballMachine extends UnicastRemoteObject implements GumballMachineRemote {

    State soldOutState;
    State soldState;
    State noQuarterState;
    State hasQuarterState;
    State winnerState;


    State state = soldOutState;
    int count;
    String location;

    public GumballMachine(String location,int count) throws RemoteException {
        soldOutState = new SoldOutState(this);
        soldState = new SoldState(this);
        noQuarterState = new NoQuarterState(this);
        hasQuarterState = new HasQuarterState(this);
        winnerState = new WinnerState(this);
        this.count = count;
        if (count > 0){
            state = noQuarterState;
        }
        this.location = location;
    }

    public void insertQuarter(){
        state.insertQuarter();
    }

    public void ejectQuarter(){
        state.ejectQuarter();
    }

    public void turnCrank(){
        state.turnCrank();
        state.dispense();
    }

    public void releaseBall(){
        System.out.println("A gumball comes rolling out the slot...");
        if (count != 0){
            count = count - 1;
        }
    }

    public void refill(int count){
        this.count = count;
        state = noQuarterState;
    }


    public void setState(State state) {
        this.state = state;
    }


    public int getCount() {
        return count;
    }

    public State getSoldOutState() {
        return soldOutState;
    }

    public State getSoldState() {
        return soldState;
    }

    public State getNoQuarterState() {
        return noQuarterState;
    }

    public State getHasQuarterState() {
        return hasQuarterState;
    }

    public State getWinnerState() {
        return winnerState;
    }

    public String getLocation() {
        return location;
    }

    public State getState() {
        return state;
    }

    @Override
    public String toString() {
        return "GumballMachine{" +
                "state=" + state +
                '}';
    }
}
