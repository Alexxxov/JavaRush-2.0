package com.javarush.task.task29.task2909.human;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Human implements Alive {

    protected int age;
    protected String name;

    protected int[] size;

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

    public void printSize() {
        System.out.println("Рост: " + size[0] + " Вес: " + size[1]);
    }
}