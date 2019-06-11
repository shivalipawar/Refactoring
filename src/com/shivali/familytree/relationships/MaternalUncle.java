package com.shivali.familytree.relationships;

import com.shivali.familytree.CustomException;
import com.shivali.familytree.Family;
import com.shivali.familytree.GenderType;
import com.shivali.familytree.Person;

import java.util.ArrayList;
import java.util.List;

import static com.shivali.familytree.TreeHelper.getParentFamily;
import static com.shivali.familytree.TreeHelper.getchildDependingOnGender;

public class MaternalUncle implements IRelationShip {
    Family root;

    public MaternalUncle(Family root) {
        this.root = root;
    }

    public MaternalUncle() {}

    @Override
    public List<Person> getPersons(String personName) {
        Family parentOfPerson = getParentFamily(personName, root);
        ArrayList<Person> siblingsOfMother;
        Sibling sibling = new Sibling(root);
        siblingsOfMother = (ArrayList<Person>) sibling.getPersons(parentOfPerson.bornChild.getName());
        return getchildDependingOnGender(GenderType.Male, siblingsOfMother);
    }
}