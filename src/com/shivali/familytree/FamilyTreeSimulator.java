package com.shivali.familytree;

public class FamilyTreeSimulator {
    public static void main(String[] args) {
        createTree();
        String fileName= "C:\\Users\\Shivali\\IdeaProjects\\FamilyTree\\resource\\input";
        InputProcessor inputProcessor = new InputProcessor();
        inputProcessor.processInput(fileName);
    }

    private static void createTree() {
    }
}
