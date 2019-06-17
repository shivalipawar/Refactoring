package com.shivali.familytree.relationships;

import com.shivali.familytree.*;

import java.util.ArrayList;
import java.util.List;

import static com.shivali.familytree.TreeHelper.getParentFamily;
import static com.shivali.familytree.TreeHelper.getchildForGender;

public class MaternalAunt implements IRelationShip {
    Family root;

    public MaternalAunt(Family root) {
        this.root = root;
    }

    @Override
    public List<Person> getPersons(String personName) throws CustomException {
        Family parentOfPerson = getParentFamily(personName, root);
        if (parentOfPerson.bornChild.getGender().equals(GenderType.Female)) {
            ArrayList<Person> siblingsOfMother = (ArrayList<Person>) new Sibling(root)
                    .getPersons(parentOfPerson.bornChild.getName());
            return getchildForGender(GenderType.Female, siblingsOfMother);
        }
        return new ArrayList<>();

    }
}
