package com.shivali.familytree;

import com.shivali.familytree.relationships.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
//TODO : Diff result in same scenarios of Aria and Atya.Need to discuss.
//TODO : Due to object creation at begin its causing issue check for Aria.
public class FamilyTreeSimulator {
    public static void main(String[] args) {
        Family root = createTree();
        TreeHelper treeHelper = new TreeHelper();
        //TODO Make sure filepath is correct
        String fileName= "C:\\Users\\Shivali\\IdeaProjects\\FamilyTree\\resource\\input";
        InputProcessor inputProcessor = new InputProcessor();
        List<String> inputFromUser = inputProcessor.processInput(fileName);
        for (String line1:inputFromUser){
            String words[] = line1.split(" ");
//            System.out.println("array of words is ");
//            for (String wrd:words) {
//                System.out.print(wrd+" ");
//            }
            if(words[0].equalsIgnoreCase(Constants.ADD_CHILD)){
                GenderType genderType = (words[3].equalsIgnoreCase("Female")) ? GenderType.Female : GenderType.Male;
                try {
                    root = treeHelper.addChildToTree(words[1],words[2],genderType,root);
                } catch (CustomException e) {
                    e.printStackTrace();
                }
            }else{
                //TODO : Root should be passed to createRelationMap and remove unwanted constructor from all relation classes
                Map<String,IRelationShip> relations = createRelationMap(root);
                String personName = words[1];
                String relation = words[2];
//                System.out.println(" person name and relation is"+personName+" "+relation);
                IRelationShip relationObj = relations.get(relation);
                try {
                    List<Person> resultOfRelation = relationObj.getPersons(personName);
                    if (resultOfRelation.size() != 0) {
                        for (Person p : resultOfRelation) {
                            System.out.print(p.getName() + " ");
                        }
                        System.out.println();
                    }
                    else{
                        System.out.println("None");
                    }
                } catch (CustomException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    private static Map<String, IRelationShip> createRelationMap(Family root) {
        Map<String, IRelationShip> relations = new HashMap<>();
        relations.put(Constants.SIBLINGS,new Sibling(root));
        relations.put(Constants.DAUGHTER,new Daughter(root));
        relations.put(Constants.SON,new Son(root));
        relations.put(Constants.SISTER_IN_LAW,new SisterInLaw(root));
        relations.put(Constants.BROTHER_IN_LAW,new BrotherInLaw(root));
        relations.put(Constants.MATERNAL_AUNT,new MaternalAunt(root));
        relations.put(Constants.PATERNAL_AUNT,new PaternalAunt(root));
        relations.put(Constants.MATERNAL_UNCLE,new MaternalUncle(root));
        relations.put(Constants.PATERNAL_UNCLE,new PaternalUncle(root));
        return relations;
    }

    private static Family createTree() {
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
        level3PersonChild3 = new Person("Lavnya", GenderType.Female);
        level3PersonChild4 = new Person("Vasa", GenderType.Male);
        level3PersonChild5 = new Person("Kriya", GenderType.Male);
        level3PersonChild6 = new Person("Krithi", GenderType.Female);

        level1Family1.addChild(level2Family1);
        level1Family1.addChild(level2PersonChild1);
        level1Family1.addChild(level2PersonChild2);

        level1Family2.addChild(level2PersonChild3);
        level1Family2.addChild(level2PersonChild4);

        level1Family3.addChild(level2Family2);
        level1Family3.addChild(level2PersonChild5);

        level1Family4.addChild(level2Family3);
        level1Family4.addChild(level2Family4);
        level1Family4.addChild(level2PersonChild6);

        level2Family1.addChild(level3PersonChild1);

        level2Family2.addChild(level3PersonChild2);
        level2Family2.addChild(level3PersonChild3);

        level2Family3.addChild(level3PersonChild4);

        level2Family4.addChild(level3PersonChild5);
        level2Family4.addChild(level3PersonChild6);

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
