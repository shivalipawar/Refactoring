package com.shivali.familytree.relationships;

import com.shivali.familytree.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.shivali.familytree.TreeHelper.*;

public class BrotherInLaw implements IRelationShip {

    Family root;

    public BrotherInLaw(Family root) {
        this.root = root;
    }

    @Override
    public List<Person> getPersons(String personName) throws CustomException {
        ArrayList<Family> personSiblingsFamilies;
        Family parentOfPerson = getParentFamily(personName, root);
        if (parentOfPerson!= null) {
            ArrayList bornChildren = parentOfPerson.getBornChildren();
            if (isBornChildren(bornChildren, personName)) {
                personSiblingsFamilies = getSiblingsFamily(root, personName);
                return personSiblingsFamilies.stream().map(person -> person.spouse).collect(Collectors.toList());
            } else {
                Family personFamily = searchFamilyOf(personName, root);
                Person spouse = getSpouse(personName, personFamily);
                ArrayList spouseSiblings = getSiblings(root, spouse.getName());
                return getchildDependingOnGender(GenderType.Male, spouseSiblings);
            }
        }
        else{
            System.out.println(Constants.PERSON_NOT_FOUND);
            //throw new CustomException("PERSON_NOT_FOUND");
        }
            return null;
    }

    private Person getSpouse(String personName, Family personFamily) {
        if (personFamily.bornChild.getName().equalsIgnoreCase(personName)) {
            return personFamily.spouse;
        } else {
            return personFamily.bornChild;
        }
    }
}
