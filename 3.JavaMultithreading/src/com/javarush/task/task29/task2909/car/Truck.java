package com.javarush.task.task29.task2909.car;

/**
 * Created by Admin on 28.02.2017.
 */
public class Truck extends Car {

    public Truck(int numberOfPassengers) {
        super(0, numberOfPassengers);
    }

    @Override
    public int getMaxSpeed() {
        return Car.MAX_TRUCK_SPEED;
    }
}
