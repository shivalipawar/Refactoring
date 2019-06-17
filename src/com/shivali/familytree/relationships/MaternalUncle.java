package com.shivali.familytree.relationships;

import com.shivali.familytree.*;

import java.util.ArrayList;
import java.util.List;

import static com.shivali.familytree.TreeHelper.getParentFamily;
import static com.shivali.familytree.TreeHelper.getchildForGender;

public class MaternalUncle implements IRelationShip {
    Family root;

    public MaternalUncle(Family root) {
        this.root = root;
    }

    @Override
    public List<Person> getPersons(String personName) throws CustomException {
        Family parentOfPerson = getParentFamily(personName, root);
        ArrayList<Person> siblingsOfMother = (ArrayList<Person>) new Sibling(root)
                .getPersons(parentOfPerson.bornChild.getName());
        return getchildForGender(GenderType.Male, siblingsOfMother);
    }
}
