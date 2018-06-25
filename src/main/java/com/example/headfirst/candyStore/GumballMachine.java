package com.example.headfirst.candyStore;

/**
 * 糖果机
 */
public class GumballMachine {

    State soldOutState;
    State soldState;
    State noQuarterState;
    State hasQuarterState;
    State winnerState;


    State state = soldOutState;
    int count = 0;

    public GumballMachine(int count) {
        soldOutState = new SoldOutState(this);
        soldState = new SoldState(this);
        noQuarterState = new NoQuarterState(this);
        hasQuarterState = new HasQuarterState(this);
        winnerState = new WinnerState(this);
        this.count = count;
        if (count > 0){
            state = noQuarterState;
        }
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

    @Override
    public String toString() {
        return "GumballMachine{" +
                "state=" + state +
                '}';
    }
}
