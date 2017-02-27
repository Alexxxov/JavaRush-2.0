package com.javarush.task.task29.task2909.human;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Human implements Alive {

    private static int nextId = 0;
    private int id;

    private BloodGroup bloodGroup;

    protected Size size;

    protected int age;
    protected String name;

    public class Size
    {
        public Size(int height, int weight)
        {
            this.height = height;
            this.weight = weight;
        }

        public int height;
        public int weight;
    }

    private final List<Human> children = new ArrayList<>();

    public List<Human> getChildren() {
        return Collections.unmodifiableList(children);
    }

    public void addChild (Human human)
    {
        children.add(human);
    }

    public void removeChild (Human human)
    {
        children.remove(human);
    }

    public Human(String name, int age) {
        this.name = name;
        this.age = age;
        this.id = nextId;
        nextId++;
    }

    public int getId() {
        return id;
    }

    public String getPosition()
    {
        return "Человек";
    }

    public void printData() {
        System.out.println( getPosition() + ": " + name);
    }

    public int getAge() {
        return age;
    }

    public String getName() {
        return name;
    }

    @Override
    public void live() {
    }

    public BloodGroup getBloodGroup() {
        return bloodGroup;
    }

    public void setBloodGroup(BloodGroup bloodGroup) {
        this.bloodGroup = bloodGroup;
    }

    public void printSize() {
        System.out.println("Рост: " + size.height + " Вес: " + size.weight);
    }
}