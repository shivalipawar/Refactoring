package com.shivali.familytree;

import java.util.ArrayList;
import java.util.stream.Collectors;

class Family {
    ArrayList children;
    Person marriedToBornChild;
    Person bornChild;

    Family(Person bornChild, Person marriedToBornChild) {
        this.bornChild = bornChild;
        this.marriedToBornChild = marriedToBornChild;
        this.children = new ArrayList();
    }

    void addChild(Object child) {
        children.add(child);
    }

    boolean contains(String name) {
        return this.bornChild.getName().equals(name) || this.marriedToBornChild.getName().equals(name);
    }

    Person getMother(Family node) {
        return (node.bornChild.getGender() == GenderType.Female) ? bornChild : marriedToBornChild;
    }

    ArrayList getBornChildren(){
        return (ArrayList<Person>) this.children.stream().map((child)->{
            if(child instanceof Family) return ((Family) child).bornChild;
            else return child;
        }).collect(Collectors.toList());
    }
}
