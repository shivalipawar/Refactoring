package com.shivali.familytree.relationships;

import com.shivali.familytree.Family;
import com.shivali.familytree.Person;

import java.util.List;
import java.util.stream.Collectors;

import static com.shivali.familytree.TreeHelper.getParentFamily;

public class Sibling implements IRelationShip {
    Family root;

    Sibling(Family root) {
        this.root = root;
    }

    @Override
    public List<Person> getPersons(String personName) {
        return getParentFamily(personName, root).getBornChildren().stream()
                .filter(p -> !p.getName().equals(personName))
                .collect(Collectors.toList());
    }
}
