package com.shivali.familytree;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class InputProcessor {
    public void processInput(String fileName) {
        try(Stream<String> stream = Files.lines(Paths.get(fileName))){
            stream.forEach(System.out::println);
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }
}
