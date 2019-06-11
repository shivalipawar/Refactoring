package com.shivali.familytree;

import com.shivali.familytree.relationships.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FamilyTreeSimulator {
    public static void main(String[] args) {
        Family root = createTree();
        TreeHelper treeHelper = new TreeHelper();
        //TODO Root should be passed to createRelationMap and remove unwanted constructor from all relation classes
        Map<String,IRelationShip> relations = createRelationMap();
        //TODO Make sure filepath is correct
        String fileName= "C:\\Projects\\IdeaProj\\family-tree\\resource\\input";
        InputProcessor inputProcessor = new InputProcessor();
        List<String> inputFromUser = inputProcessor.processInput(fileName);
        for (String line1:inputFromUser){
            String words[] = line1.split(" ");
            if(words[0].equalsIgnoreCase(Constants.ADD_CHILD)){
                GenderType genderType = (words[3].equalsIgnoreCase("Female")) ? GenderType.Female : GenderType.Male;
                try {
                    treeHelper.addChildToTree(words[1],words[2],genderType,root);
                } catch (CustomException e) {
                    e.printStackTrace();
                }
            }else{
                String personName = words[1];
                String relation = words[2];
                IRelationShip relationObj = relations.get(relation);
            }
        }

    }

    private static Map<String, IRelationShip> createRelationMap() {
        Map<String, IRelationShip> relations = new HashMap<>();
        relations.put(Constants.SIBLINGS,new Sibling());
        relations.put(Constants.DAUGHTER,new Daughter());
        relations.put(Constants.SON,new Son());
        relations.put(Constants.SISTER_IN_LAW,new SisterInLaw());
        relations.put(Constants.BROTHER_IN_LAW,new BrotherInLaw());
        relations.put(Constants.MATERNAL_AUNT,new MaternalAunt());
        relations.put(Constants.PATERNAL_AUNT,new PaternalAunt());
        relations.put(Constants.MATERNAL_UNCLE,new MaternalUncle());
        relations.put(Constants.PATERNAL_UNCLE,new PaternalUncle());
        return relations;
    }

    private static Family createTree() {

        return null;
    }
}
