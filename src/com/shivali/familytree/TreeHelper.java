package com.shivali.familytree;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class TreeHelper {

    Family addChildToTree(String motherName, String childName, GenderType childGender, Family rootFamily) throws CustomException {
        Family familyNode = searchFamilyOf(motherName, rootFamily);
        if (familyNode != null) {
            String motherInFamily = familyNode.getMother().getName();
            if (motherInFamily.equals(motherName)) {
                familyNode.addChild(new Person(childName, childGender));
                System.out.println(Constants.CHILD_ADD_SUCCESS);
                return rootFamily;
            } else {
                System.out.println(Constants.CHILD_ADD_FAILURE);
                throw new CustomException(Constants.CHILD_ADD_FAILURE);
            }
        } else {
            System.out.println(Constants.PERSON_NOT_FOUND);
            throw new CustomException(Constants.PERSON_NOT_FOUND);
        }
    }

    public void displayTree(Family rootFamily) {
        if(rootFamily == null || rootFamily.children==null) return;
        else{
            for (Object child : rootFamily.children){
                if (child instanceof Family) {
                    System.out.println("Family "+((Family) child).bornChild.getName()+" - "+((Family) child).spouse.getName());
                    displayTree((Family) child);
                }else if(child instanceof Person){
                    System.out.println("Child "+((Person) child).getName());
                }
            }
        }
    }

    public static Family searchFamilyOf(String personName, Family root) {
        if (root == null || root.children == null) return null;
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
        return null;
    }

    public static Family getParentFamily(String personName, Family root) {
        if (root == null || root.children == null) return null;
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


//    public ArrayList getResultForGivenRelation(Family familyRootNode, String personName, String relationName) throws CustomException {
//        return getPersonForGivenRelationship(familyRootNode, personName, relationName);
//    }

//    private ArrayList getPersonForGivenRelationship(Family familyRootNode, String personName, String relationName) throws CustomException {
//        switch (relationName) {
//            case "Sibling":
//                return getSiblings(familyRootNode, personName);
//            case "Daughter":
//                //return getDaughterOrSon(familyRootNode, personName, GenderType.Female);
//            case "Son":
//                //return getDaughterOrSon(familyRootNode, personName, GenderType.Male);
//            case "Paternal-Uncle":
//                //return getPaternalAuntOrUncle(familyRootNode, personName, GenderType.Male);
//            case "Paternal-Aunt":
//                //return getPaternalAuntOrUncle(familyRootNode, personName, GenderType.Female);
//            case "Maternal-Uncle":
////                return getMaternalAuntOrUncle(familyRootNode, personName, GenderType.Male);
//            case "Maternal-Aunt":
////                return getMaternalAuntOrUncle(familyRootNode, personName, GenderType.Female);
//            case "Sister-in-law":
////                return getSisterInLaw(familyRootNode, personName);
//            case "Brother-in-law":
////                return getBrotherInLaw(familyRootNode, personName);
//            default:
//                break;
//        }
//        return null;
//    }

//    private ArrayList getBrotherInLaw(Family familyRootNode, String personName) {
//        Person spouse;
//        ArrayList spouseSiblings, siblingsHusband = new ArrayList();
//        ArrayList<Family> personSiblingsFamilies;
//        Family parentOfPerson = getParentFamily(personName, familyRootNode);
//        ArrayList bornChildren = parentOfPerson.getBornChildren();
//        if (!(isBornChildren(bornChildren, personName))) {
//            Object personFamily = searchFamilyOf(personName, familyRootNode);
//            if (personFamily instanceof Family) {
//                if (((Family) personFamily).bornChild.getName().equalsIgnoreCase(personName)) {
//                    spouse = ((Family) personFamily).spouse;
//                } else {
//                    spouse = ((Family) personFamily).bornChild;
//                }
//                spouseSiblings = getSiblings(familyRootNode, spouse.getName());
//                return getchildDependingOnGender(GenderType.Male, spouseSiblings);
//            }
//            return null;
//        } else {
//            personSiblingsFamilies = (getSiblingsFamily(familyRootNode, personName));
//            for (Family child : personSiblingsFamilies) {
//                Person husband = child.spouse;
//                siblingsHusband.add(husband);
//            }
//            return siblingsHusband;
//        }
//    }

//    private ArrayList getSisterInLaw(Family familyRootNode, String personName) {
//        Person spouse;
//        ArrayList<Family> personSiblingsFamilies;
//        ArrayList spouseSiblings, wifeOfSibling = new ArrayList();
//        Family parentOfPerson = (Family) getParentFamily(personName, familyRootNode);
//        ArrayList bornChildren = parentOfPerson.getBornChildren();
//        if (!(isBornChildren(bornChildren, personName))) {
//            Object personFamily = searchFamilyOf(personName, familyRootNode);
//            if (personFamily instanceof Family) {
//                if (((Family) personFamily).bornChild.getName().equalsIgnoreCase(personName)) {
//                    spouse = ((Family) personFamily).spouse;
//                } else {
//                    spouse = ((Family) personFamily).bornChild;
//                }
//                spouseSiblings = getSiblings(familyRootNode, spouse.getName());
//                return getchildDependingOnGender(GenderType.Female, spouseSiblings);
//            }
//            return null;
//        } else {
//            personSiblingsFamilies = (getSiblingsFamily(familyRootNode, personName));
//            for (Family family : personSiblingsFamilies) {
//                Person wife = family.spouse;
//                wifeOfSibling.add(wife);
//            }
//            return wifeOfSibling;
//        }
//    }

    public static ArrayList<Family> getSiblingsFamily(Family familyRootNode, String personName) {
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

     public static boolean isBornChildren(ArrayList<Person> bornChildren, String personName) {
        for (Person child : bornChildren) {
            if (personName.equals(child.getName())) return true;
        }
        return false;
    }


//    private ArrayList getDaughterOrSon(Family familyRootNode, String personName, GenderType genderType) throws CustomException {
//        Object childsFamily = searchFamilyOf(personName, familyRootNode);
//        if (childsFamily instanceof Family) {
//            ArrayList children = ((Family) childsFamily).getBornChildren();
//            if (genderType.equals(GenderType.Female)) {
//                return getchildDependingOnGender(GenderType.Female, children);
//            }
//            return getchildDependingOnGender(GenderType.Male, children);
//        } else {
//            throw new CustomException("PERSON_NOT_FOUND");
//        }
//    }

    public static ArrayList<Person> getchildDependingOnGender(GenderType genderType, ArrayList<Person> children) {
        return (ArrayList<Person>) children.stream().filter(t -> t.getGender().equals(genderType)).collect(Collectors.toList());
    }

    public static ArrayList getSiblings(Family familyRootNode, String personName) {
        ArrayList siblings = new ArrayList<Person>();
        Family parentfamilyOfGivenPerson = getParentFamily(personName, familyRootNode);
        ArrayList children = parentfamilyOfGivenPerson.getBornChildren();
        for (Object child : children) {
            if (!((Person) child).getName().equals(personName)) siblings.add(child);
        }
        return siblings;
    }

//    private ArrayList getPaternalAuntOrUncle(Family familyRootNode, String personName, GenderType genderType) {
//        Family parentOfPerson = getParentFamily(personName, familyRootNode);
//        ArrayList siblingsOfFather;
//        if (parentOfPerson.bornChild.getGender().equals(GenderType.Male)) {
//            siblingsOfFather = getSiblings(familyRootNode, parentOfPerson.bornChild.getName());
//            if (genderType.equals(GenderType.Male)) {
//                return getchildDependingOnGender(GenderType.Male, siblingsOfFather);
//            }
//            return getchildDependingOnGender(GenderType.Female, siblingsOfFather);
//        }
//        return null;
//    }

//    private ArrayList getMaternalAuntOrUncle(Family familyRootNode, String personName, GenderType genderType) {
//        Family parentOfPerson = getParentFamily(personName, familyRootNode);
//        ArrayList siblingsOfMother;
//        if (parentOfPerson.bornChild.getGender().equals(GenderType.Female)) {
//            siblingsOfMother = getSiblings(familyRootNode, parentOfPerson.bornChild.getName());
//            if (genderType.equals(GenderType.Male)) {
//                return getchildDependingOnGender(GenderType.Male, siblingsOfMother);
//            }
//            return getchildDependingOnGender(GenderType.Female, siblingsOfMother);
//        }
//        return null;
//    }
}
