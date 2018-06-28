package com.example.headfirst.combining;

public class DuckSimulator {
    public static void main(String[] args) {
        DuckSimulator simulator = new DuckSimulator();
        AbstractDuckFactory duckFactory = new CountingDuckFactory();
//        AbstractDuckFactory duckFactory = new DuckFactory();
        simulator.simulator(duckFactory);
    }

    /**
     * 工厂方法
     * @param duckFactory
     */
    void simulator(AbstractDuckFactory duckFactory){
//        Quackable mallardDuck = duckFactory.createMallardDuck();
        Quackable redheadDuck = duckFactory.createRedheadDuck();
        Quackable duckCall = duckFactory.createDuckCall();
        Quackable rubberDuck = duckFactory.createRubberDuck();
        Quackable goose = new GooseAdapter(new Goose());

        System.out.println("\nDuck DuckSimulator: With Composite - Flocks");

        Flock flockOfDucks = new Flock();
        flockOfDucks.add(redheadDuck);
        flockOfDucks.add(duckCall);
        flockOfDucks.add(rubberDuck);
        flockOfDucks.add(goose);

        Flock flockOfMallards = new Flock();
        Quackable mallardDuckOne = duckFactory.createMallardDuck();
        Quackable mallardDuckTwo = duckFactory.createMallardDuck();
        Quackable mallardDuckThree = duckFactory.createMallardDuck();
        Quackable mallardDuckFour = duckFactory.createMallardDuck();
        flockOfMallards.add(mallardDuckOne);
        flockOfMallards.add(mallardDuckTwo);
        flockOfMallards.add(mallardDuckThree);
        flockOfMallards.add(mallardDuckFour);

        flockOfDucks.add(flockOfMallards);

        /*System.out.println("\nDuck Simulator: Whole Flock Simulation");
        simulator(flockOfDucks);

        System.out.println("\nDuck Simulator: Mallard Flock Simulation");
        simulator(flockOfMallards);*/

        System.out.println("\nDuck Simulator: With Observer");
        Quackologist quackologist = new Quackologist();
        flockOfDucks.registerObserver(quackologist);
        simulator(flockOfDucks);

//        simulator(mallardDuck);
        /*simulator(redheadDuck);
        simulator(duckCall);
        simulator(rubberDuck);
        simulator(goose);*/

        System.out.println("The ducks quacked " + QuackCounter.getNumberOfQuacks() + " times");
    }

    void simulator(Quackable duck){
        duck.quack();
    }
}
