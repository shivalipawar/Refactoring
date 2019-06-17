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
        Family personsFamily = getParentFamily(personName, root);
        if (isBornChildrenOf(personsFamily, personName)) {
            return getSiblingsFamily(root, personName)
                    .stream().map(person -> person.spouse)
                    .collect(Collectors.toList());
        } else {
            Family personFamily = searchFamilyOf(personName, root);
            Person spouse = getSpouse(personName, personFamily);
            ArrayList spouseSiblings = getSiblings(root, spouse.getName());
            return getchildForGender(GenderType.Male, spouseSiblings);
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
