package com.shivali.familytree.relationships;

import com.shivali.familytree.CustomException;
import com.shivali.familytree.Person;

import java.util.List;

public interface IRelationShip {
    List<Person> getPersons(String personName) throws CustomException;
}
