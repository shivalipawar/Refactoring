package com.shivali.familytree.relationships;

import com.shivali.familytree.Family;
import com.shivali.familytree.GenderType;
import com.shivali.familytree.Person;

import java.util.ArrayList;
import java.util.List;

import static com.shivali.familytree.TreeHelper.getParentFamily;
import static com.shivali.familytree.TreeHelper.getchildDependingOnGender;

public class MaternalAunt implements IRelationShip {
    Family root;

    public MaternalAunt(Family root) {
        this.root = root;
    }

    @Override
    public List<Person> getPersons(String personName) {
        Family parentOfPerson = getParentFamily(personName, root);
        ArrayList<Person> siblingsOfMother;
        Sibling sibling = new Sibling(root);
        if (parentOfPerson.bornChild.getGender().equals(GenderType.Female)) {
            siblingsOfMother = (ArrayList<Person>) sibling.getPersons(parentOfPerson.bornChild.getName());
            return getchildDependingOnGender(GenderType.Female, siblingsOfMother);
        }
        return null;
    }
}
