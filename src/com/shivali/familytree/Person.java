package com.shivali.familytree;

class Person {
    private String name;
    private GenderType gender;

    Person(String personName, GenderType personGender) {
        this.name = personName;
        this.gender = personGender;
    }

    public Person() {

    }

    String getName() {
        return name;
    }

    GenderType getGender() {
        return gender;
    }
}

