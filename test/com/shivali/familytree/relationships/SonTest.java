package com.shivali.familytree.relationships;

import com.shivali.familytree.CustomException;
import com.shivali.familytree.Family;
import com.shivali.familytree.GenderType;
import com.shivali.familytree.Person;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

public class SonTest {

    Family familyRootNode, level1Family1, level1Family2, level1Family3, level1Family4;
    Person level1PersonChild1, father, mother;
    Son son;

    @Before
    public void initialize() {
        level1PersonChild1 = new Person("Ish", GenderType.Male);
        level1Family1 = new Family(new Person("Chit", GenderType.Male), new Person("Amba", GenderType.Female));
        level1Family2 = new Family(new Person("Vich", GenderType.Male), new Person("Lika", GenderType.Female));
        level1Family3 = new Family(new Person("Aras", GenderType.Male), new Person("Chitra", GenderType.Female));
        level1Family4 = new Family(new Person("Satya", GenderType.Female), new Person("Vyan", GenderType.Male));
        father = new Person("Shan", GenderType.Male);
        mother = new Person("Anga", GenderType.Female);
        familyRootNode = new Family(father, mother);
        //Added children to root family.
        familyRootNode.addChild(level1Family1);
        familyRootNode.addChild(level1PersonChild1);
        familyRootNode.addChild(level1Family2);
        familyRootNode.addChild(level1Family3);
        familyRootNode.addChild(level1Family4);
        son = new Son(familyRootNode);
    }

    @Test
    public void getPersonsShouldReturnCorrectSon() throws CustomException {
        ArrayList expected = new ArrayList();
        expected.add(level1Family1.bornChild);
        expected.add(level1PersonChild1);
        expected.add(level1Family2.bornChild);
        expected.add(level1Family3.bornChild);
        ArrayList result = (ArrayList) son.getPersons(father.getName());
        Assert.assertEquals(expected, result);
    }
}