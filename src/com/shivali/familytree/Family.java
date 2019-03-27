package com.shivali.familytree;

import java.util.ArrayList;
import java.util.stream.Collectors;

class Family {
    ArrayList children;
    Person spouse;
    Person bornChild;

    Family(Person bornChild, Person spouse) {
        this.bornChild = bornChild;
        this.spouse = spouse;
        this.children = new ArrayList();
    }

    void addChild(Object child) {
        children.add(child);
    }

    boolean contains(String name) {
        return this.bornChild.getName().equals(name) || this.spouse.getName().equals(name);
    }

    Person getMother() {
        return (bornChild.getGender() == GenderType.Female) ? bornChild : spouse;
    }

    ArrayList<Person> getBornChildren() {
        return (ArrayList<Person>) this.children.stream().map((child) -> {
            if (child instanceof Family) return ((Family) child).bornChild;
            else return child;
        }).collect(Collectors.toList());
    }
}
