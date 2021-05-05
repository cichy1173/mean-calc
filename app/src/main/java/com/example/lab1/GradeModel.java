package com.example.lab1;

public class GradeModel {

    private String name;
    private int value;
    private final int id;
    static int count = 0;

    public GradeModel(String name) {
        this.name = name;
        this.id = count;
        count++;
        this.value = 2;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public int getId() {
        return id;
    }

    public static int getCount() {
        return count;
    }

    public static void setCount(int count) {
        GradeModel.count = count;
    }
}
