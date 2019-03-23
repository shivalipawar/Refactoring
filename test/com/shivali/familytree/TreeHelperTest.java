package com.shivali.familytree;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

public class TreeHelperTest {
    TreeHelper treeHelper;
    Family familyRootNode, children2,children3;
    Person father, mother, children1,children4,children5,children6;
    ArrayList<Object> childrenList;
    ArrayList<Object> familyTreeList;

    @Before
    public void initialize() {
        treeHelper = new TreeHelper();
        childrenList = new ArrayList<Object>();
        familyTreeList = new ArrayList<Object>();
        children1 = new Person("Ish", GenderType.Female);
        children2 = new Family(new Person("Chit", GenderType.Male), new Person("Amba", GenderType.Female));
        children3 = new Family(new Person("Dritha", GenderType.Male),new Person("Jaya",GenderType.Female));
        children4 = new Person("Tritha", GenderType.Female);
        children5 = new Person("Vritha", GenderType.Female);
        children6 = new Person("NotInActualTree", GenderType.Male);
        father = new Person("Shan", GenderType.Male);
        mother = new Person("Anga", GenderType.Female);
        familyRootNode = new Family(father, mother);
        //Added children to root family.
        familyRootNode.addChild(children1);
        familyRootNode.addChild(children2);
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
        String mother = children2.spouse.getName();
        String childName = "Tritha";
        treeHelper.addChildToTree(mother, childName, GenderType.Female, familyRootNode);
        Person p = (Person) children2.children.get(0);
        Assert.assertNotNull(children2.children);
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
    public  void getFamilyShouldReturnFamilyObjectForUnmarriedChild(){
        children2.addChild(children3);
        String childName ="Vish";
        Object obj= treeHelper.getParentFamily(childName,familyRootNode);
        Assert.assertEquals(children2,obj);
    }

    @Test
    public void getRelationShouldReturnCorrectSibling() throws CustomException {
        ArrayList expected = new ArrayList();
        expected.add(children3.bornChild);
        expected.add(children5);
        children2.addChild(children3);
        children2.addChild(children4);
        children2.addChild(children5);
        String relationName ="Siblings";
        ArrayList result =treeHelper.getResultForGivenRelation(familyRootNode,children4.getName(),relationName);
        Assert.assertEquals(expected,result);
    }

    @Test
    public void getRelationShouldReturnOneDaughterIfNotMultiple() throws CustomException {
        ArrayList expected = new ArrayList();
        expected.add(children1);
        String relationName ="Daughter";
        ArrayList result =treeHelper.getResultForGivenRelation(familyRootNode,familyRootNode.bornChild.getName(),relationName);
        Assert.assertEquals(expected,result);
    }
    @Test
    public void getRelationShouldReturnCorrectListOfDaughter() throws CustomException {
        children2.addChild(children3);
        children2.addChild(children4);
        children2.addChild(children5);
        ArrayList expected = new ArrayList();
        expected.add(children4);
        expected.add(children5);
        String relationName ="Daughter";
        ArrayList result =treeHelper.getResultForGivenRelation(familyRootNode,children2.spouse.getName(),relationName);
        Assert.assertEquals(expected,result);
    }

    @Test
    public void getRelationShouldReturnOneSonIfNotMultiple() throws CustomException {
        ArrayList expected = new ArrayList();
        expected.add(children2.bornChild);
        String relationName ="Son";
        ArrayList result =treeHelper.getResultForGivenRelation(familyRootNode,familyRootNode.bornChild.getName(),relationName);
        Assert.assertEquals(expected,result);
    }

    @Test
    public void getRelationShouldReturnCorrectListOfSon() throws CustomException {
        children2.addChild(children3);
        children2.addChild(children4);
        children2.addChild(children5);
        children2.addChild(children6);
        ArrayList expected = new ArrayList();
        expected.add(children3.bornChild);
        expected.add(children6);
        String relationName ="Son";
        ArrayList result =treeHelper.getResultForGivenRelation(familyRootNode,children2.spouse.getName(),relationName);
        Assert.assertEquals(expected,result);
    }
}