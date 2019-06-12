package com.shivali.familytree.relationships;

import com.shivali.familytree.*;

import java.util.ArrayList;
import java.util.List;

import static com.shivali.familytree.TreeHelper.getParentFamily;
import static com.shivali.familytree.TreeHelper.getchildDependingOnGender;

public class PaternalAunt implements IRelationShip {
    Family root;

    public PaternalAunt(Family root) {
        this.root = root;
    }

    public PaternalAunt() {}

    @Override
    public List<Person> getPersons(String personName) throws CustomException {
        Family parentOfPerson = getParentFamily(personName, root);
        ArrayList<Person> siblingsOfFather;
        Sibling sibling = new Sibling(root);
        if(parentOfPerson!= null){
            if (parentOfPerson.bornChild.getGender().equals(GenderType.Male)) {
                siblingsOfFather = (ArrayList<Person>) sibling.getPersons(parentOfPerson.bornChild.getName());
                return getchildDependingOnGender(GenderType.Female, siblingsOfFather);
            }
        }else{
            System.out.println(Constants.PERSON_NOT_FOUND);
            throw new CustomException("PERSON_NOT_FOUND");
        }
        return null;
    }
}
