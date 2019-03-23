package com.shivali.familytree;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class TreeHelper {

    void addChildToTree(String motherName, String childName, GenderType childGender, Family rootFamily) throws CustomException {
        Object familyNodeWithRequiredPerson = searchPerson(motherName, rootFamily);
        if (familyNodeWithRequiredPerson instanceof Family) {
            Person mother = ((Family) familyNodeWithRequiredPerson).getMother((Family) familyNodeWithRequiredPerson);
            if (mother.getName().equals(motherName)) {
                ((Family) familyNodeWithRequiredPerson).addChild(new Person(childName, childGender));
            } else if (!(mother.getName().equals(motherName))) {
                throw new CustomException("CHILD_ADDITION_FAILED ");
            }
        } else {
            throw new CustomException("PERSON_NOT_FOUND");
        }
    }

    public Family searchMothersFamily(String motherName, Family root) {
        Family temp = root;
        if (temp == null) return null;

        if (temp.getMother(temp).getName().equals(motherName)) return temp;
        if (temp.children == null) return null;
        final List<Family> childFamilies = (List<Family>) temp.children.stream().filter(t -> t instanceof Family).map(t -> (Family) t).collect(Collectors.toList());
        for (Family f : childFamilies) {
            Family result = searchMothersFamily(motherName, f);
            if (result != null) {
                return result;
            }
        }
        return null;
    }

    public Object searchPerson(String personName, Family root) {
        Family temp = root;
        Person p;
        if (temp == null) return null;

        if (temp.spouse.getName().equals(personName) || temp.bornChild.getName().equals(personName)) return temp;
        if (temp.children == null) return null;
        for (Object child : temp.children) {
            if (child instanceof Family) {
                Family result = (Family) searchPerson(personName, (Family) child);
                if (result != null) {
                    return result;
                }
            } else {
                p = (Person) child;
                if (p.getName().equals(personName)) return child;
            }
        }
        return null;
    }

    Object getParentFamily(String personName, Family root) {
        if (root == null) return null;
        if (root.children == null) return null;
        for (Object child : root.children) {
            if (child instanceof Family) {
                final Family family = (Family) child;
                if (family.contains(personName))
                    return root;
                Family result = (Family) getParentFamily(personName, family);
                if (result != null) {
                    return result;
                    }
            } else {
                final Person personChild = (Person) child;
                if (personName.equals(personChild.getName())) return root;
            }
        }
        return null;
    }

    public ArrayList getResultForGivenRelation(Family familyRootNode, String personName, String relationName) throws CustomException {
        ArrayList relationResult = new ArrayList<>();
        Family parentfamilyOfGivenPerson = (Family) getParentFamily(personName,familyRootNode);
        if(parentfamilyOfGivenPerson != null){
            relationResult = getPersonForGivenRelationship(parentfamilyOfGivenPerson,personName,relationName,familyRootNode);
        }
        else{
            relationResult = getPersonForGivenRelationship(null,personName,relationName,familyRootNode);
        }
        return relationResult;
    }

//    private void displayOutput(ArrayList relationResult) {
//        for(Object relationObj : relationResult){
//            System.out.print(relationObj);
//        }
//    }

    private ArrayList getPersonForGivenRelationship(Family parentfamilyOfGivenPerson, String personName, String relationName, Family familyRootNode) throws CustomException {
        switch(relationName){
            case "Siblings" :
                return getSiblings(parentfamilyOfGivenPerson,personName);
            case "Daughter" :
                return getDaughterOrSon(familyRootNode,personName,GenderType.Female);
            case "Son" :
                return getDaughterOrSon(familyRootNode,personName,GenderType.Male);
            default:
                break;
        }
        return null;
    }

    private ArrayList getDaughterOrSon(Family familyRootNode, String personName, GenderType genderType) throws CustomException {
        Object childsFamily = searchPerson(personName,familyRootNode);
        if(childsFamily instanceof Family) {
            ArrayList children = familyRootNode.getBornChildren((Family) childsFamily);
            if (genderType.equals(GenderType.Female)) {
                return getchildDependingOnGender(GenderType.Female, children);
            }
            return getchildDependingOnGender(GenderType.Male, children);
        }else{
            throw new CustomException("PERSON_NOT_FOUND");
        }
    }

    private ArrayList getchildDependingOnGender(GenderType genderType, ArrayList children) {
        final List<Person> childList = (List<Person>) children.stream().filter(t -> ((Person)t).getGender().equals(genderType)).collect(Collectors.toList());
        return (ArrayList) childList;
    }

    private ArrayList getSiblings(Family parentfamilyOfGivenPerson, String personName) {
        ArrayList siblings = new ArrayList<Person>();
        ArrayList children = parentfamilyOfGivenPerson.getBornChildren(parentfamilyOfGivenPerson);
        for(Object child : children){
           if(((Person)child).getName()!=personName)siblings.add(child);
        }
        return siblings;
    }
}

