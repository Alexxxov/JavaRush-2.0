package com.javarush.task.task29.task2909.human;

/**
 * Created by Admin on 26.02.2017.
 */
public class Soldier extends Human {

    public static int nextId = 0;
    private int id;

    public static final int FIRST = 1;
    public static final int SECOND = 2;
    public static final int THIRD = 3;
    public static final int FOURTH = 4;
    private int bloodGroup;

    public Soldier(String name, int age)
    {
        super(name, age);
        this.id = nextId;
        nextId++;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void live() {
            fight();
    }

    public void fight() {

    }

    public void setBloodGroup(int code) {
        bloodGroup = code;
    }

    public int getBloodGroup() {
        return bloodGroup;
    }
}
