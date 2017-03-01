package com.javarush.task.task29.task2909.human;

import java.util.ArrayList;
import java.util.List;

public class University {

    private String name;
    private int age;
    private List<Student> students;

    public University(String name, int age) {
        this.name = name;
        this.age = age;
        students = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    public Student getStudentWithAverageGrade(double averageGrade) {
        Student resultStudent = null;
        for (Student student: students) {
            if (student.getAverageGrade() == averageGrade) {
                resultStudent = student;
                break;
            }
        }
        return resultStudent;
    }

    public Student getStudentWithMaxAverageGrade() {
        Student maxStudent = null;
        double max = 0;
        for (Student student: students)
            if (student.getAverageGrade() > max) {
                max = student.getAverageGrade();
                maxStudent = student;
            }
        return maxStudent;
    }

    public Student getStudentWithMinAverageGrade() {
        Student minStudent = null;
        double min = students.get(0).getAverageGrade();
        for (Student student: students)
            if (student.getAverageGrade() < min) {
                min = student.getAverageGrade();
                minStudent = student;
            }
        return minStudent;
    }

    public void expel(Student student)
    {
        if (students.contains(student))
            students.remove(student);
    }
}