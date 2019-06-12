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

    public SisterInLaw() {}

    @Override
    public List<Person> getPersons(String personName) throws CustomException {
        Family parentOfPerson = getParentFamily(personName,root );
        if(parentOfPerson!= null){
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
        else{
            System.out.println(Constants.PERSON_NOT_FOUND);
            throw new CustomException("PERSON_NOT_FOUND");
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
