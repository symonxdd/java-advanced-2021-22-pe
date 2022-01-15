package be.pxl.ja.cluedo.models;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import be.pxl.ja.cluedo.models.interfaces.CluedoReader;
import be.pxl.ja.cluedo.exceptions.CluedoException;

public class CluedoCrackTheCodeRiddleReader implements CluedoReader {

    private List<CrackTheCode> crackTheCodeRiddles;

    public CluedoCrackTheCodeRiddleReader() {
        crackTheCodeRiddles = new ArrayList<>();
    }

    public List<CrackTheCode> getCrackTheCodeRiddles() {
        if (isListEmpty(crackTheCodeRiddles)) {
            throw new CluedoException("Cannot call this method.\nDid you call the readFile() method first?");
        }
        return crackTheCodeRiddles;
    }

    private boolean isListEmpty(List<? extends Riddle> riddle) {
        return riddle == null;
    }

    @Override
    public void readFile(String file) {
        try (BufferedReader reader = Files.newBufferedReader(Paths.get(file))) {
            StringBuilder riddleDescription = new StringBuilder();
            String line = null;
            String riddleId = "";
            String riddleSolution = "";

            while ((line = reader.readLine()) != null) {
                String lineFirstChar = line.substring(0, 1);

                if (lineFirstChar.equals("#")) {
                    // we have a new Riddle
                    riddleId = line.substring(1);
                    line = reader.readLine();
                }

                if (!line.contains("ANSWER")) {
                    riddleDescription.append(line);
                    riddleDescription.append(System.lineSeparator());
                } else {
                    riddleSolution = line.substring(line.indexOf(":") + 2);

                    CrackTheCode crackTheCodeRiddle = mapToCrackTheCodeRiddle(riddleId,
                            riddleDescription.toString(),
                            riddleSolution);
                    crackTheCodeRiddles.add(crackTheCodeRiddle);

                    riddleDescription.setLength(0);
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
    }

    public CrackTheCode mapToCrackTheCodeRiddle(String riddleId, String riddleDescription, String riddleSolution) {
        CrackTheCode crackTheCode = new CrackTheCode();
        crackTheCode.setId(riddleId);
        crackTheCode.setDescription(riddleDescription);
        crackTheCode.setSolution(riddleSolution);

        return crackTheCode;
    }
}
