package com.shivali.familytree;

public class Person {
    private String name;
    private GenderType gender;

    Person(String personName,GenderType personGender){
        this.name = personName;
        this.gender = personGender;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public GenderType getGender() {
        return gender;
    }

    public void setGender(GenderType gender) {
        this.gender = gender;
    }
}

