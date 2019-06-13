package com.shivali.familytree.relationships;

import com.shivali.familytree.CustomException;
import com.shivali.familytree.Family;
import com.shivali.familytree.GenderType;
import com.shivali.familytree.Person;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

public class BrotherInLawTest {

    Family familyRootNode, level1Family1, level1Family2, level1Family3, level1Family4,level2Family1,level2Family3,level2Family4;
    Person level1PersonChild1, father, mother,level2PersonChild1,level2PersonChild2,level3PersonChild1,level2PersonChild6;
    BrotherInLaw brotherInLaw;

    @Before
    public void initialize() {
        level1PersonChild1 = new Person("Ish", GenderType.Male);
        level1Family1 = new Family(new Person("Chit", GenderType.Male), new Person("Amba", GenderType.Female));
        level1Family2 = new Family(new Person("Vich", GenderType.Male), new Person("Lika", GenderType.Female));
        level1Family3 = new Family(new Person("Aras", GenderType.Male), new Person("Chitra", GenderType.Female));
        level1Family4 = new Family(new Person("Satya", GenderType.Female), new Person("Vyan", GenderType.Male));
        level2Family1 = new Family(new Person("Dritha", GenderType.Female), new Person("Jaya", GenderType.Male));
        level2Family3 = new Family(new Person("Asva", GenderType.Male), new Person("Satvy", GenderType.Female));
        level2Family4 = new Family(new Person("Vyas", GenderType.Male), new Person("Krip", GenderType.Female));
        level2PersonChild1 = new Person("Tritha", GenderType.Female);
        level2PersonChild2 = new Person("Vritha", GenderType.Male);
        level3PersonChild1 = new Person("Yodhan", GenderType.Male);
        level2PersonChild6 = new Person("Atya", GenderType.Female);
        father = new Person("Shan", GenderType.Male);
        mother = new Person("Anga", GenderType.Female);
        familyRootNode = new Family(father, mother);
        //Added children to root family.
        familyRootNode.addChild(level1Family1);
        familyRootNode.addChild(level1PersonChild1);
        familyRootNode.addChild(level1Family2);
        familyRootNode.addChild(level1Family3);
        familyRootNode.addChild(level1Family4);
        brotherInLaw = new BrotherInLaw(familyRootNode);
    }

    @Test
    public void getPersonsShouldReturnListOfBrotherInLawForSpouseBrother() throws CustomException {
        level1Family1.addChild(level2Family3);
        level1Family1.addChild(level2Family4);
        level1Family1.addChild(level2PersonChild6);
        ArrayList expected = new ArrayList();
        expected.add(level2Family4.bornChild);
        ArrayList result = (ArrayList) brotherInLaw.getPersons(level2Family3.spouse.getName());
        Assert.assertEquals(expected, result);
    }

    @Test
    public void getPersonsShouldReturnListOfBrotherInLawForSiblingHusband() throws CustomException {
        level1Family1.addChild(level2Family1);
        level1Family1.addChild(level2PersonChild1);
        level1Family1.addChild(level2PersonChild2);
        ArrayList expected = new ArrayList();
        expected.add(level2Family1.spouse);
        ArrayList result = (ArrayList) brotherInLaw.getPersons(level2PersonChild1.getName());
        Assert.assertEquals(expected, result);
    }
}