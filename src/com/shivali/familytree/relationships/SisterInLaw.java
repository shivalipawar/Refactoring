package com.shivali.familytree.relationships;

import com.shivali.familytree.Family;
import com.shivali.familytree.GenderType;
import com.shivali.familytree.Person;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.shivali.familytree.TreeHelper.*;

public class SisterInLaw implements IRelationShip {
    Family root;

    public SisterInLaw(Family root) {
        this.root = root;
    }

    @Override
    public List<Person> getPersons(String personName) {
        Family parentOfPerson = getParentFamily(personName,root );
        ArrayList<Person> bornChildren = parentOfPerson.getBornChildren();
        if (isBornChildren(bornChildren, personName)) {
            return getSiblingsFamily(root, personName).stream().map(family -> family.spouse).collect(Collectors.toList());
        } else {
            Object personFamily = searchFamilyOf(personName, root);
            Person spouse = getSpouse(personName, (Family) personFamily);
            ArrayList spouseSiblings = getSiblings(root, spouse.getName());
            return getchildDependingOnGender(GenderType.Female, spouseSiblings);
        }
    }

    private Person getSpouse(String personName, Family personFamily) {
        if (personFamily.bornChild.getName().equalsIgnoreCase(personName)) {
            return personFamily.spouse;
        } else {
            return personFamily.bornChild;
        }
    }
}
