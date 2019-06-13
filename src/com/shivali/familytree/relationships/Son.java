package com.shivali.familytree.relationships;

import com.shivali.familytree.*;

import java.util.ArrayList;
import java.util.List;

import static com.shivali.familytree.TreeHelper.getchildDependingOnGender;
import static com.shivali.familytree.TreeHelper.searchFamilyOf;

public class Son implements IRelationShip {
    Family root;

    public Son(Family root) {
        this.root = root;
    }

    @Override
    public List<Person> getPersons(String personName) throws CustomException {
        Family childsFamily = searchFamilyOf(personName, root);
        if (childsFamily != null) {
            ArrayList<Person> children = childsFamily.getBornChildren();
            return getchildDependingOnGender(GenderType.Male, children);
        } else {
            System.out.println(Constants.PERSON_NOT_FOUND);
            //throw new CustomException("PERSON_NOT_FOUND");
        }
        return null;
    }
}
