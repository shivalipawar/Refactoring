package com.shivali.familytree;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;

import static org.mockito.Mockito.when;

public class TreeHelperTest {
    TreeHelper treeHelper;
    Family familyRootNode, level1Family1, level1Family2, level1Family3, level1Family4, level2Family1,level2Family2,level2Family3,level2Family4;
    Person father, mother, level1PersonChild1, level2PersonChild1, level2PersonChild2,level2PersonChild3,level2PersonChild4,level2PersonChild5,level2PersonChild6, level3PersonChild1,level3PersonChild2,level3PersonChild3,level3PersonChild4,level3PersonChild5,level3PersonChild6;
    ArrayList<Object> childrenList;
    ArrayList<Object> familyTreeList;

    @Before
    public void initialize() {
        treeHelper = new TreeHelper();
        childrenList = new ArrayList<Object>();
        familyTreeList = new ArrayList<Object>();
        level1PersonChild1 = new Person("Ish", GenderType.Male);
        level1Family1 = new Family(new Person("Chit", GenderType.Male), new Person("Amba", GenderType.Female));
        level1Family2 = new Family(new Person("Vich", GenderType.Male), new Person("Lika", GenderType.Female));
        level1Family3 = new Family(new Person("Aras", GenderType.Male), new Person("Chitra", GenderType.Female));
        level1Family4 = new Family(new Person("Satya", GenderType.Female), new Person("Vyan", GenderType.Male));
        level2Family1 = new Family(new Person("Dritha", GenderType.Female),new Person("Jaya",GenderType.Male));
        level2Family2 = new Family(new Person("Jnki", GenderType.Female),new Person("Arit",GenderType.Male));
        level2Family3 = new Family(new Person("Asva", GenderType.Male),new Person("Satvy",GenderType.Female));
        level2Family4 = new Family(new Person("Vyas", GenderType.Male),new Person("Krip",GenderType.Female));
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
    }

    @Test(expected = CustomException.class)
    public void addChildShouldThrowExceptionWhenTryingToAddChildToMale() throws CustomException {
        String mother = "Pjali";
        String childName = "Srutak";
        treeHelper.addChildToTree(mother, childName, GenderType.Male, familyRootNode);
    }

    @Test(expected = CustomException.class)
    public void addChildShouldThrowExceptionWhenMotherNotFound() throws CustomException {
        String mother = "Chit";
        String childName = "Vani";
        treeHelper.addChildToTree(mother, childName, GenderType.Male, familyRootNode);
    }

    @Test
    public void addChildShouldSuccessfullyAddChildToGivenMother() throws CustomException {
        String mother = level1Family1.marriedToBornChild.getName();
        String childName = "Tritha";
        treeHelper.addChildToTree(mother, childName, GenderType.Female, familyRootNode);
        Person p = (Person) level1Family1.children.get(0);
        Assert.assertNotNull(level1Family1.children);
        Assert.assertEquals(p.getName(), childName);
    }

    @Test
    public void searchMotherShouldReturnNullWhenMotherNotFound() {
        String motherName = "Ambika";
        Object obj = treeHelper.searchMothersFamily(motherName, familyRootNode);
        Assert.assertNull(obj);
    }

    @Test
    public void getFamilyShouldReturnFamilyObjectForMarriedChild(){
        String childName ="Chit";
        Object obj = treeHelper.getParentFamily(childName,familyRootNode);
        Assert.assertEquals(familyRootNode,obj);
    }

    @Test
    public  void getFamilyShouldReturnPersonObjectForUnmarriedChild(){
        level1Family1.addChild(level2Family1);
        String childName ="Jaya";
        Object obj= treeHelper.getParentFamily(childName,familyRootNode);
        Assert.assertEquals(level1Family1,obj);
    }

    @Test
    public void getResultForGivenRelationShouldReturnCorrectSibling() throws CustomException {
        ArrayList expected = new ArrayList();
        ArrayList mockedListOfAllChildren = new ArrayList();
        TreeHelper mockedTH = Mockito.mock(TreeHelper.class);
        Family mockedFamily = Mockito.mock(Family.class);
        expected.add(level2Family1.bornChild);
        expected.add(level2PersonChild2);
        mockedListOfAllChildren.add(level2Family1.bornChild);
        mockedListOfAllChildren.add(level2PersonChild1);
        mockedListOfAllChildren.add(level2PersonChild2);
        level1Family1.addChild(level2Family1);
        level1Family1.addChild(level2PersonChild1);
        level1Family1.addChild(level2PersonChild2);
        when(mockedTH.getParentFamily(level2PersonChild1.getName(),familyRootNode)).thenReturn(level1Family1);
        when(mockedFamily.getBornChildren(level1Family1)).thenReturn(mockedListOfAllChildren);
        ArrayList result =treeHelper.getResultForGivenRelation(familyRootNode, level2PersonChild1.getName(),"Siblings");
        Assert.assertEquals(expected,result);
    }

    @Test
    public void getResultForGivenRelationShouldReturnOneDaughterIfNotMultiple() throws CustomException {
        ArrayList expected = new ArrayList();
        expected.add(level1Family4.bornChild);
        TreeHelper mockedTH = Mockito.mock(TreeHelper.class);
        when(mockedTH.searchPerson(familyRootNode.bornChild.getName(),familyRootNode)).thenReturn(familyRootNode);
        ArrayList result =treeHelper.getResultForGivenRelation(familyRootNode,familyRootNode.bornChild.getName(),"Daughter");
        Assert.assertEquals(expected,result);
    }
    @Test
    public void getResultForGivenRelationShouldReturnCorrectListOfDaughter() throws CustomException {
        level1Family2.addChild(level2PersonChild3);
        level1Family2.addChild(level2PersonChild4);
        ArrayList expected = new ArrayList();
        expected.add(level2PersonChild3);
        expected.add(level2PersonChild4);
        TreeHelper mockedTH = Mockito.mock(TreeHelper.class);
        when(mockedTH.searchPerson(level1Family2.marriedToBornChild.getName(),familyRootNode)).thenReturn(level1Family2);
        ArrayList result =treeHelper.getResultForGivenRelation(familyRootNode, level1Family2.marriedToBornChild.getName(),"Daughter");
        Assert.assertEquals(expected,result);
    }
    @Test
    public void getResultForGivenRelationShouldReturnOneSonIfNotMultiple() throws CustomException {
        level1Family1.addChild(level2Family1);
        level2Family1.addChild(level3PersonChild1);
        ArrayList expected = new ArrayList();
        expected.add(level3PersonChild1);
        TreeHelper mockedTH = Mockito.mock(TreeHelper.class);
        when(mockedTH.searchPerson(level2Family1.bornChild.getName(),familyRootNode)).thenReturn(level2Family1);
        ArrayList result =treeHelper.getResultForGivenRelation(familyRootNode, level2Family1.marriedToBornChild.getName(),"Son");
        Assert.assertEquals(expected,result);
    }
    @Test
    public void getResultForGivenRelationShouldReturnListOfSon() throws CustomException {
        ArrayList expected = new ArrayList();
        expected.add(level1Family1.bornChild);
        expected.add(level1PersonChild1);
        expected.add(level1Family2.bornChild);
        expected.add(level1Family3.bornChild);
        TreeHelper mockedTH = Mockito.mock(TreeHelper.class);
        when(mockedTH.searchPerson(familyRootNode.bornChild.getName(),familyRootNode)).thenReturn(familyRootNode);
        ArrayList result =treeHelper.getResultForGivenRelation(familyRootNode,familyRootNode.bornChild.getName(),"Son");
        Assert.assertEquals(expected,result);
    }
    @Test
    public void getResultForGivenRelationShouldReturnListOfPaternalUncle() throws CustomException {
        level1Family1.addChild(level2Family1);
        level1Family1.addChild(level2PersonChild1);
        level1Family1.addChild(level2PersonChild2);
        level2Family1.addChild(level3PersonChild1);
        ArrayList expected = new ArrayList();
        expected.add(level1PersonChild1);
        expected.add(level1Family2.bornChild);
        expected.add(level1Family3.bornChild);
        ArrayList result = treeHelper.getResultForGivenRelation(familyRootNode,level2PersonChild1.getName(),"Paternal-Uncle");
        Assert.assertEquals(expected,result);
    }
    @Test
    public void getResultForGivenRelationShouldReturnListOfPaternalAunt() throws CustomException {
        level1Family1.addChild(level2Family1);
        level1Family1.addChild(level2PersonChild1);
        level1Family1.addChild(level2PersonChild2);
        level2Family1.addChild(level3PersonChild1);
        ArrayList expected = new ArrayList();
        expected.add(level1Family4.bornChild);
        ArrayList result = treeHelper.getResultForGivenRelation(familyRootNode,level2PersonChild1.getName(),"Paternal-Aunt");
        Assert.assertEquals(expected,result);
    }
    @Test
    public void getResultForGivenRelationShouldReturnListOfMaternalUncle() throws CustomException {
        level1Family4.addChild(level2Family3);
        level1Family4.addChild(level2Family4);
        ArrayList expected = new ArrayList();
        expected.add(level1Family1.bornChild);
        expected.add(level1PersonChild1);
        expected.add(level1Family2.bornChild);
        expected.add(level1Family3.bornChild);
        ArrayList result = treeHelper.getResultForGivenRelation(familyRootNode,level2Family3.bornChild.getName(),"Maternal-Uncle");
        Assert.assertEquals(expected,result);
    }
    @Test
    public void getResultForGivenRelationShouldReturnListOfMaternalAunt() throws CustomException {
        level1Family4.addChild(level2Family3);
        level1Family4.addChild(level2Family4);
        ArrayList expected = new ArrayList();
        ArrayList result = treeHelper.getResultForGivenRelation(familyRootNode,level2Family3.bornChild.getName(),"Maternal-Aunt");
        Assert.assertEquals(expected,result);
    }
    @Test
    public void getResultForGivenRelationShouldReturnListOfSisterInLawForSpouseSister() throws CustomException {
        level1Family1.addChild(level2Family1);
        level1Family1.addChild(level2PersonChild1);
        level1Family1.addChild(level2PersonChild2);
        ArrayList expected = new ArrayList();
        expected.add(level2PersonChild1);
        ArrayList result = treeHelper.getResultForGivenRelation(familyRootNode,level2Family1.marriedToBornChild.getName(),"Sister-in-law");
        Assert.assertEquals(expected,result);
    }
    @Test
    public void getResultForGivenRelationShouldReturnListOfSisterInLawForSiblingWives() throws CustomException {
        level1Family4.addChild(level2Family3);
        level1Family4.addChild(level2Family4);
        level1Family4.addChild(level2PersonChild6);
        ArrayList expected = new ArrayList();
        expected.add(level2Family3.marriedToBornChild);
        ArrayList result = treeHelper.getResultForGivenRelation(familyRootNode,level2Family4.bornChild.getName(),"Sister-in-law");
        Assert.assertEquals(expected,result);
    }
    @Test
    public void getResultForGivenRelationShouldReturnListOfBrotherInLawForSpouseBrother() throws CustomException {
        level1Family1.addChild(level2Family3);
        level1Family1.addChild(level2Family4);
        level1Family1.addChild(level2PersonChild6);
        ArrayList expected = new ArrayList();
        expected.add(level2Family4.bornChild);
        ArrayList result = treeHelper.getResultForGivenRelation(familyRootNode,level2Family3.marriedToBornChild.getName(),"Brother-in-law");
        Assert.assertEquals(expected,result);
    }
    @Test
    public void getResultForGivenRelationShouldReturnListOfBrotherInLawForSiblingHusband() throws CustomException {
        level1Family1.addChild(level2Family1);
        level1Family1.addChild(level2PersonChild1);
        level1Family1.addChild(level2PersonChild2);
        ArrayList expected = new ArrayList();
        expected.add(level2Family1.marriedToBornChild);
        ArrayList result = treeHelper.getResultForGivenRelation(familyRootNode,level2PersonChild1.getName(),"Brother-in-law");
        Assert.assertEquals(expected,result);
    }
}