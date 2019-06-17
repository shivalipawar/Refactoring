package com.shivali.familytree;

import java.util.ArrayList;
import java.util.stream.Collectors;

import static com.shivali.familytree.Constants.PERSON_NOT_FOUND;

public class TreeHelper {

    void addChildToTree(String motherName, String childName, GenderType childGender, Family rootFamily) throws CustomException {
        Family familyNode = searchFamilyOf(motherName, rootFamily);
        if (familyNode != null) {
            String motherInFamily = familyNode.getMother().getName();
            if (motherInFamily.equals(motherName)) {
                familyNode.addChild(new Person(childName, childGender));
                System.out.println(Constants.CHILD_ADD_SUCCESS);
            } else {
                System.out.println(Constants.CHILD_ADD_FAILURE);
                throw new CustomException(Constants.CHILD_ADD_FAILURE);
            }
        } else {
            System.out.println(PERSON_NOT_FOUND);
            throw new CustomException(PERSON_NOT_FOUND);
        }
    }

    public void displayTree(Family rootFamily) {
        if (rootFamily == null || rootFamily.children == null) return;
        else {
            for (Object child : rootFamily.children) {
                if (child instanceof Family) {
                    System.out.println("Family " + ((Family) child).bornChild.getName() + " - " + ((Family) child).spouse.getName());
                    displayTree((Family) child);
                } else if (child instanceof Person) {
                    System.out.println("Child " + ((Person) child).getName());
                }
            }
        }
    }

    public static Family searchFamilyOf(String personName, Family root) throws CustomException {
        if (root.spouse.getName().equals(personName) || root.bornChild.getName().equals(personName))
            return root;
        for (Object child : root.children) {
            if (child instanceof Family) {
                Family result = searchFamilyOf(personName, (Family) child);
                if (result != null) {
                    return result;
                }
            }
        }
        throw new CustomException(PERSON_NOT_FOUND);
    }

    static Family getParentOf(String personName, Family root) {
        for (Object child : root.children) {
            if (child instanceof Family) {
                if (((Family) child).contains(personName))
                    return root;
                return getParentOf(personName, (Family) child);
            } else {
                final Person personChild = (Person) child;
                if (personName.equals(personChild.getName())) return root;
            }
        }
        return null;
    }

    public static Family getParentFamily(String personName, Family root) throws CustomException {
        if (root == null) throw new CustomException(PERSON_NOT_FOUND);
        Family result = getParentOf(personName, root);
        if (result == null) {
            throw new CustomException(PERSON_NOT_FOUND);
        }
        return result;
    }

    public static ArrayList<Family> getSiblingsFamily(Family familyRootNode, String personName) throws CustomException {
        ArrayList<Family> families = new ArrayList<>();
        Family parentfamilyOfGivenPerson = getParentFamily(personName, familyRootNode);
        ArrayList children = parentfamilyOfGivenPerson.children;
        for (Object child : children) {
            if (child instanceof Family) {
                if (!((Family) child).bornChild.getName().equals(personName) && !((Family) child).spouse.getName().equals(personName)) {
                    families.add((Family) child);
                }
            }
        }
        return families;
    }

    public static boolean isBornChildrenOf(Family family, String personName) {
        return !family.getBornChildren().stream().filter(c -> c.getName().equals(personName)).collect(Collectors.toList()).isEmpty();
    }

    public static ArrayList<Person> getchildForGender(GenderType genderType, ArrayList<Person> children) {
        return (ArrayList<Person>) children.stream().filter(t -> t.getGender().equals(genderType)).collect(Collectors.toList());
    }

    public static ArrayList getSiblings(Family familyRootNode, String personName) throws CustomException {
        ArrayList siblings = new ArrayList<Person>();
        Family parentfamilyOfGivenPerson = getParentFamily(personName, familyRootNode);
        ArrayList children = parentfamilyOfGivenPerson.getBornChildren();
        for (Object child : children) {
            if (!((Person) child).getName().equals(personName)) siblings.add(child);
        }
        return siblings;
    }
}
