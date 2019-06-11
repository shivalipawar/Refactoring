package com.shivali.familytree.relationships;

import com.shivali.familytree.CustomException;
import com.shivali.familytree.Family;
import com.shivali.familytree.GenderType;
import com.shivali.familytree.Person;

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
            throw new CustomException("PERSON_NOT_FOUND");
        }
    }
}
