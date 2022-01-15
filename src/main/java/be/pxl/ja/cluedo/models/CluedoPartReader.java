package be.pxl.ja.cluedo.models;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import be.pxl.ja.cluedo.models.interfaces.CluedoReader;
import be.pxl.ja.cluedo.exceptions.CluedoException;

public class CluedoPartReader implements CluedoReader {

    private List<Room> rooms;
    private List<Weapon> weapons;
    private List<Suspect> suspects;

    public CluedoPartReader() {
        rooms = new ArrayList<>();
        weapons = new ArrayList<>();
        suspects = new ArrayList<>();
    }

    public List<Room> getRooms() {
        if (isListEmpty(rooms)) {
            throw new CluedoException("Cannot call this method.\nDid you call the readFile() method first?");
        }
        return rooms;
    }

    public List<Weapon> getWeapons() {
        if (isListEmpty(weapons)) {
            throw new CluedoException("Cannot call this method.\nDid you call the readFile() method first?");
        }
        return weapons;
    }

    public List<Suspect> getSuspects() {
        if (isListEmpty(suspects)) {
            throw new CluedoException("Cannot call this method.\nDid you call the readFile() method first?");
        }
        return suspects;
    }

    private boolean isListEmpty(List<? extends Part> part) {
        return part == null;
    }

    @Override
    public void readFile(String file) {
        try (BufferedReader reader = Files.newBufferedReader(Paths.get(file))) {
            String line = null;
            PartType partNameEnum = null;
            String[] lineParts = null;

            while ((line = reader.readLine()) != null) {
                String lineFirstChar = line.substring(0, 1);

                if (lineFirstChar.equals("#")) {
                    String partName = line.substring(1, line.length() - 1);
                    partNameEnum = PartType.valueOf(partName);
                    line = reader.readLine();
                }

                switch (partNameEnum) {
                    case ROOM:
                        rooms.add(new Room(line));
                        break;
                    case WEAPON:
                        weapons.add(new Weapon(line));
                        break;
                    case SUSPECT:
                        lineParts = line.split(";");
                        Suspect newSuspect;

                        String suspectNameAndPotentialTitle = lineParts[0];
                        if (suspectNameAndPotentialTitle.indexOf(".") == -1) {
                            // suspect has no title
                            newSuspect = new Suspect(lineParts[0]);
                            newSuspect.setTitle("");
                        } else {
                            // suspect has a title
                            String suspectTitle = suspectNameAndPotentialTitle
                                    .substring(0, suspectNameAndPotentialTitle.indexOf(".") + 1);

                            String suspectName = suspectNameAndPotentialTitle
                                    .substring(suspectNameAndPotentialTitle.indexOf(".") + 1);

                            newSuspect = new Suspect(suspectName);
                            newSuspect.setTitle(suspectTitle);
                        }

                        newSuspect.setAge(Integer.parseInt(lineParts[1]));
                        newSuspect.setNationality(lineParts[2]);
                        newSuspect.setOccupation(lineParts[3]);

                        suspects.add(newSuspect);
                        break;
                }
            }

            Collections.shuffle(rooms);

        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
    }
}
