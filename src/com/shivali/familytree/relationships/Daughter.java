package com.shivali.familytree.relationships;

import com.shivali.familytree.*;

import java.util.ArrayList;
import java.util.List;

import static com.shivali.familytree.TreeHelper.getchildForGender;
import static com.shivali.familytree.TreeHelper.searchFamilyOf;

public class Daughter implements IRelationShip {
    Family root;

    public Daughter(Family root) {
        this.root = root;
    }

    @Override
    public List<Person> getPersons(String personName) throws CustomException {
        Family childsFamily = searchFamilyOf(personName, root);
        ArrayList<Person> children = childsFamily.getBornChildren();
        return getchildForGender(GenderType.Female, children);

    }
}
