package com.shivali.familytree.relationships;

import com.shivali.familytree.*;

import java.util.ArrayList;
import java.util.List;

import static com.shivali.familytree.TreeHelper.getParentFamily;
import static com.shivali.familytree.TreeHelper.getchildForGender;

public class PaternalUncle implements IRelationShip {
    Family root;

    public PaternalUncle(Family root) {
        this.root = root;
    }

    @Override
    public List<Person> getPersons(String personName) throws CustomException {
        Family parentOfPerson = getParentFamily(personName, root);
        ArrayList<Person> siblingsOfFather;
        Sibling sibling = new Sibling(root);
        if (parentOfPerson != null) {
            if (parentOfPerson.bornChild.getGender().equals(GenderType.Male)) {
                siblingsOfFather = (ArrayList<Person>) sibling.getPersons(parentOfPerson.bornChild.getName());
                return getchildForGender(GenderType.Male, siblingsOfFather);
            } else {
                return new ArrayList<>();
            }
        } else {
            System.out.println(Constants.PERSON_NOT_FOUND);
            throw new CustomException("PERSON_NOT_FOUND");
        }
    }
}
