package com.github.eugeneheen.berry.kit.test.core;

public class User {
    private String name;

    private int age;

    private String desc;

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

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    @Override
    public String toString() {
        return "Name: " + this.getName() + ",Age: " + String.valueOf(this.getAge());
    }
}
