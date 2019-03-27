package com.shivali.familytree;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class TreeHelper {

    void addChildToTree(String motherName, String childName, GenderType childGender, Family rootFamily) throws CustomException {
        Family familyNode = searchFamilyOf(motherName, rootFamily);
        if (familyNode != null) {
            String motherInFamily = familyNode.getMother().getName();
            if (motherInFamily.equals(motherName)) {
                familyNode.addChild(new Person(childName, childGender));
            } else {
                throw new CustomException(Constants.CHILD_ADD_FAILURE);
            }
        } else {
            throw new CustomException(Constants.PERSON_NOT_FOUND);
        }
    }

    Family searchFamilyOf(String personName, Family root) {
        if (root == null) return null;
        if (root.marriedToBornChild.getName().equals(personName) || root.bornChild.getName().equals(personName))
            return root;
        if (root.children == null) return null;
        for (Object child : root.children) {
            if (child instanceof Family) {
                Family result = searchFamilyOf(personName, (Family) child);
                if (result != null) {
                    return result;
                }
            }
        }
        return null;
    }

    Family getParentFamily(String personName, Family root) {
        if (root == null) return null;
        if (root.children == null) return null;
        for (Object child : root.children) {
            if (child instanceof Family) {
                final Family family = (Family) child;
                if (family.contains(personName))
                    return root;
                Family result = getParentFamily(personName, family);
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
        return getPersonForGivenRelationship(familyRootNode, personName, relationName);
    }

    private ArrayList getPersonForGivenRelationship(Family familyRootNode, String personName, String relationName) throws CustomException {
        switch (relationName) {
            case "Siblings":
                return getSiblings(familyRootNode, personName);
            case "Daughter":
                return getDaughterOrSon(familyRootNode, personName, GenderType.Female);
            case "Son":
                return getDaughterOrSon(familyRootNode, personName, GenderType.Male);
            case "Paternal-Uncle":
                return getPaternalAuntOrUncle(familyRootNode, personName, GenderType.Male);
            case "Paternal-Aunt":
                return getPaternalAuntOrUncle(familyRootNode, personName, GenderType.Female);
            case "Maternal-Uncle":
                return getMaternalAuntOrUncle(familyRootNode, personName, GenderType.Male);
            case "Maternal-Aunt":
                return getMaternalAuntOrUncle(familyRootNode, personName, GenderType.Female);
            case "Sister-in-law":
                return getSisterInLaw(familyRootNode, personName);
            case "Brother-in-law":
                return getBrotherInLaw(familyRootNode, personName);
            default:
                break;
        }
        return null;
    }

    private ArrayList getBrotherInLaw(Family familyRootNode, String personName) {
        Person spouse;
        ArrayList spouseSiblings, siblingsHusband = new ArrayList();
        ArrayList<Family> personSiblingsFamilies;
        Family parentOfPerson = getParentFamily(personName, familyRootNode);
        ArrayList bornChildren = parentOfPerson.getBornChildren();
        if (!(checkIfGivenPersonIsBornChild(bornChildren, personName))) {
            Object personFamily = searchFamilyOf(personName, familyRootNode);
            if (personFamily instanceof Family) {
                if (((Family) personFamily).bornChild.getName().equalsIgnoreCase(personName)) {
                    spouse = ((Family) personFamily).marriedToBornChild;
                } else {
                    spouse = ((Family) personFamily).bornChild;
                }
                spouseSiblings = getSiblings(familyRootNode, spouse.getName());
                return getchildDependingOnGender(GenderType.Male, spouseSiblings);
            }
            return null;
        } else {
            personSiblingsFamilies = (getSiblingsFamily(familyRootNode, personName));
            for (Family child : personSiblingsFamilies) {
                Person husband = child.marriedToBornChild;
                siblingsHusband.add(husband);
            }
            return siblingsHusband;
        }
    }

    private ArrayList getSisterInLaw(Family familyRootNode, String personName) {
        Person spouse;
        ArrayList<Family> personSiblingsFamilies;
        ArrayList spouseSiblings, wifeOfSibling = new ArrayList();
        Family parentOfPerson = (Family) getParentFamily(personName, familyRootNode);
        ArrayList bornChildren = parentOfPerson.getBornChildren();
        if (!(checkIfGivenPersonIsBornChild(bornChildren, personName))) {
            Object personFamily = searchFamilyOf(personName, familyRootNode);
            if (personFamily instanceof Family) {
                if (((Family) personFamily).bornChild.getName().equalsIgnoreCase(personName)) {
                    spouse = ((Family) personFamily).marriedToBornChild;
                } else {
                    spouse = ((Family) personFamily).bornChild;
                }
                spouseSiblings = getSiblings(familyRootNode, spouse.getName());
                return getchildDependingOnGender(GenderType.Female, spouseSiblings);
            }
            return null;
        } else {
            personSiblingsFamilies = (getSiblingsFamily(familyRootNode, personName));
            for (Family family : personSiblingsFamilies) {
                Person wife = family.marriedToBornChild;
                wifeOfSibling.add(wife);
            }
            return wifeOfSibling;
        }
    }

    private ArrayList getSiblingsFamily(Family familyRootNode, String personName) {
        ArrayList siblings = new ArrayList<Person>();
        Family parentfamilyOfGivenPerson = (Family) getParentFamily(personName, familyRootNode);
        ArrayList children = parentfamilyOfGivenPerson.children;
        for (Object child : children) {
            if (child instanceof Family) {
                if (((Family) child).bornChild.getName().equals(personName) || ((Family) child).marriedToBornChild.getName().equals(personName))
                    ;
                else siblings.add(child);
            }
        }
        return siblings;
    }

    private boolean checkIfGivenPersonIsBornChild(ArrayList<Person> bornChildren, String personName) {
        for (Person child : bornChildren) {
            if (personName.equals(child.getName())) return true;
        }
        return false;
    }


    private ArrayList getDaughterOrSon(Family familyRootNode, String personName, GenderType genderType) throws CustomException {
        Object childsFamily = searchFamilyOf(personName, familyRootNode);
        if (childsFamily instanceof Family) {
            ArrayList children = ((Family) childsFamily).getBornChildren();
            if (genderType.equals(GenderType.Female)) {
                return getchildDependingOnGender(GenderType.Female, children);
            }
            return getchildDependingOnGender(GenderType.Male, children);
        } else {
            throw new CustomException("PERSON_NOT_FOUND");
        }
    }

    private ArrayList getchildDependingOnGender(GenderType genderType, ArrayList children) {
        final List<Person> childList = (List<Person>) children.stream().filter(t -> ((Person) t).getGender().equals(genderType)).collect(Collectors.toList());
        return (ArrayList) childList;
    }

    private ArrayList getSiblings(Family familyRootNode, String personName) {
        ArrayList siblings = new ArrayList<Person>();
        Family parentfamilyOfGivenPerson = (Family) getParentFamily(personName, familyRootNode);
        ArrayList children = parentfamilyOfGivenPerson.getBornChildren();
        for (Object child : children) {
            if (!((Person) child).getName().equals(personName)) siblings.add(child);
        }
        return siblings;
    }

    private ArrayList getPaternalAuntOrUncle(Family familyRootNode, String personName, GenderType genderType) {
        Family parentOfPerson = (Family) getParentFamily(personName, familyRootNode);
        ArrayList siblingsOfFather;
        if (parentOfPerson.bornChild.getGender().equals(GenderType.Male)) {
            siblingsOfFather = getSiblings(familyRootNode, parentOfPerson.bornChild.getName());
            if (genderType.equals(GenderType.Male)) {
                return getchildDependingOnGender(GenderType.Male, siblingsOfFather);
            }
            return getchildDependingOnGender(GenderType.Female, siblingsOfFather);
        }
        return null;
    }

    private ArrayList getMaternalAuntOrUncle(Family familyRootNode, String personName, GenderType genderType) {
        Family parentOfPerson = (Family) getParentFamily(personName, familyRootNode);
        ArrayList siblingsOfMother;
        if (parentOfPerson.bornChild.getGender().equals(GenderType.Female)) {
            siblingsOfMother = getSiblings(familyRootNode, parentOfPerson.bornChild.getName());
            if (genderType.equals(GenderType.Male)) {
                return getchildDependingOnGender(GenderType.Male, siblingsOfMother);
            }
            return getchildDependingOnGender(GenderType.Female, siblingsOfMother);
        }
        return null;
    }
}

