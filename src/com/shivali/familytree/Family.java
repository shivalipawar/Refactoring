package com.shivali.familytree;

import java.util.ArrayList;

class Family {

    ArrayList children;
    Person spouse;
    Person bornChild;

    Family(Person bornChild, Person spouse){
        this.bornChild = bornChild;
        this.spouse = spouse;
        this.children = new ArrayList();
    }

    void addChild(Object child){
        children.add(child);
    }

    boolean contains(String name) {
        return this.bornChild.getName().equals(name) || this.spouse.getName().equals(name);
    }

    Person getMother(Family node){
        Person mother;
        return mother = (node.bornChild.getGender()==GenderType.Female) ? bornChild : spouse;
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
