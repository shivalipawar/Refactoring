package com.shivali.familytree;

import java.util.ArrayList;
import java.util.List;
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

    ArrayList<Person> getBornChildren(Family rootNode){
        ArrayList children = new ArrayList<Person>();
        if(rootNode == null) return null;
        if(rootNode.children ==null) return null;
        for(Object child : rootNode.children){
            if(child instanceof Family) children.add(((Family) child).bornChild);
            else children.add(child);
        }
        return children;
    }
}
