package com.shivali.familytree;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class Family {
    ArrayList children;
    public Person spouse;
    public Person bornChild;

    public Family(Person bornChild, Person spouse) {
        this.bornChild = bornChild;
        this.spouse = spouse;
        this.children = new ArrayList();
    }

    public void addChild(Object child) {
        children.add(child);
    }

    boolean contains(String name) {
        return this.bornChild.getName().equals(name) || this.spouse.getName().equals(name);
    }

    Person getMother() {
        return (bornChild.getGender() == GenderType.Female) ? bornChild : spouse;
    }

    public ArrayList<Person> getBornChildren() {
        return (ArrayList<Person>) this.children.stream().map((child) -> {
            if (child instanceof Family) return ((Family) child).bornChild;
            else return child;
        }).collect(Collectors.toList());
    }
}
