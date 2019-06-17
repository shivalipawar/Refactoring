package com.shivali.familytree.relationships;

import com.shivali.familytree.*;

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
    public List<Person> getPersons(String personName) throws CustomException {
        Family family = getParentFamily(personName, root);

        if (family != null) {
            if (isBornChildrenOf(family, personName)) {
                return getSiblingsFamily(root, personName).stream().map(f -> f.spouse).collect(Collectors.toList());
            } else {
                Object personFamily = searchFamilyOf(personName, root);
                Person spouse = getSpouse(personName, (Family) personFamily);
                ArrayList spouseSiblings = getSiblings(root, spouse.getName());
                return getchildForGender(GenderType.Female, spouseSiblings);
            }
        } else {
            System.out.println(Constants.PERSON_NOT_FOUND);
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
