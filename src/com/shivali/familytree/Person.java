package com.shivali.familytree;

public class Person {
    private String name;
    private GenderType gender;

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                '}';
    }

    public Person(String personName, GenderType personGender) {
        this.name = personName;
        this.gender = personGender;
    }

    public String getName() {
        return name;
    }

    public GenderType getGender() {
        return gender;
    }
}

