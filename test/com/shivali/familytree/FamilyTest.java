package com.shivali.familytree;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

public class FamilyTest {

    Family familyRootNode, bornChildFamily;
    Person born,spouse,bornChild;

    @Before
    public void initialize(){
        bornChild =new Person("Inka",GenderType.Female);
        born = new Person("Shan",GenderType.Male);
        spouse = new Person("Anga",GenderType.Female);
        familyRootNode = new Family(born,spouse);
        bornChildFamily = new Family(new Person("Chit",GenderType.Male),new Person("Amba",GenderType.Female));
    }

    @Test
    public void getBornChildShouldReturnListOfChildAsPerson(){
        ArrayList<Person> outputExpected = new ArrayList<>();
        outputExpected.add(bornChild);
        outputExpected.add(bornChildFamily.bornChild);
        familyRootNode.addChild(bornChild);
        familyRootNode.addChild(bornChildFamily);
        ArrayList<Person> bornChildren = familyRootNode.getBornChildren();
        Assert.assertEquals(outputExpected,bornChildren);
    }
}