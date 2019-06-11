package com.shivali.familytree.relationships;

import com.shivali.familytree.Family;
import com.shivali.familytree.GenderType;
import com.shivali.familytree.Person;
import com.shivali.familytree.TreeHelper;

public class TestUtil {

    static Family initializeFamilyTree() {
        Family familyRootNode, level1Family1, level1Family2, level1Family3, level1Family4, level2Family1, level2Family2, level2Family3, level2Family4;
        Person father, mother, level1PersonChild1, level2PersonChild1, level2PersonChild2, level2PersonChild3, level2PersonChild4, level2PersonChild5, level2PersonChild6, level3PersonChild1, level3PersonChild2, level3PersonChild3, level3PersonChild4, level3PersonChild5, level3PersonChild6;
        level1PersonChild1 = new Person("Ish", GenderType.Male);
        level1Family1 = new Family(new Person("Chit", GenderType.Male), new Person("Amba", GenderType.Female));
        level1Family2 = new Family(new Person("Vich", GenderType.Male), new Person("Lika", GenderType.Female));
        level1Family3 = new Family(new Person("Aras", GenderType.Male), new Person("Chitra", GenderType.Female));
        level1Family4 = new Family(new Person("Satya", GenderType.Female), new Person("Vyan", GenderType.Male));
        level2Family1 = new Family(new Person("Dritha", GenderType.Female), new Person("Jaya", GenderType.Male));
        level2Family2 = new Family(new Person("Jnki", GenderType.Female), new Person("Arit", GenderType.Male));
        level2Family3 = new Family(new Person("Asva", GenderType.Male), new Person("Satvy", GenderType.Female));
        level2Family4 = new Family(new Person("Vyas", GenderType.Male), new Person("Krip", GenderType.Female));
        level2PersonChild1 = new Person("Tritha", GenderType.Female);
        level2PersonChild2 = new Person("Vritha", GenderType.Male);
        level2PersonChild3 = new Person("Vila", GenderType.Female);
        level2PersonChild4 = new Person("Chilka", GenderType.Female);
        level2PersonChild5 = new Person("Ahit", GenderType.Male);
        level2PersonChild6 = new Person("Atya", GenderType.Female);
        level3PersonChild1 = new Person("Yodhan", GenderType.Male);
        level3PersonChild2 = new Person("Laki", GenderType.Male);
        level3PersonChild3 = new Person("Lavyna", GenderType.Female);
        level3PersonChild4 = new Person("Vasa", GenderType.Male);
        level3PersonChild5 = new Person("Kriya", GenderType.Male);
        level3PersonChild6 = new Person("Krithi", GenderType.Female);
        father = new Person("Shan", GenderType.Male);
        mother = new Person("Anga", GenderType.Female);
        familyRootNode = new Family(father, mother);
        //Added children to root family.
        familyRootNode.addChild(level1Family1);
        familyRootNode.addChild(level1PersonChild1);
        familyRootNode.addChild(level1Family2);
        familyRootNode.addChild(level1Family3);
        familyRootNode.addChild(level1Family4);
        return familyRootNode;

    }

}
