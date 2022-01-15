package be.pxl.ja.cluedo.models;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import be.pxl.ja.cluedo.exceptions.CluedoException;
import be.pxl.ja.cluedo.models.interfaces.CluedoReader;

public class CluedoAnagramRiddleReader implements CluedoReader {

    private List<Anagram> anagrams;

    public CluedoAnagramRiddleReader() {
        anagrams = new ArrayList<>();
    }

    public List<Anagram> getAnagrams() {
        if (isListEmpty(anagrams)) {
            throw new CluedoException("Cannot call this method.\nDid you call the readFile() method first?");
        }
        return anagrams;
    }

    private boolean isListEmpty(List<? extends Riddle> riddle) {
        return riddle == null;
    }

    @Override
    public void readFile(String file) {
        try (BufferedReader reader = Files.newBufferedReader(Paths.get(file))) {
            String line = null;

            while ((line = reader.readLine()) != null) {
                Anagram newAnagram = mapLineToAnagram(line);
                anagrams.add(newAnagram);
            }
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
    }

    public Anagram mapLineToAnagram(String line) {
        String[] split = line.split(";");

        Anagram anagram = new Anagram();
        anagram.setAnagram(split[0]);
        anagram.setDescription(split[1]);
        anagram.setSolution(split[2]);

        return anagram;
    }
}
