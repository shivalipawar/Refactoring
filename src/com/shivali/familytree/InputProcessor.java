package com.shivali.familytree;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class InputProcessor {
    private List<String> formattedInput = new ArrayList<>();
    public List<String> processInput(String fileName) {
        try(BufferedReader br =  Files.newBufferedReader(Paths.get(fileName))){
            formattedInput = br.lines().collect(Collectors.toList());
        }
        catch(IOException e){
            e.printStackTrace();
        }
        formattedInput.forEach(System.out::println);
        return formattedInput;
    }
}
