package be.pxl.ja.cluedo.models;

import java.util.List;
import java.util.Optional;

import be.pxl.ja.cluedo.exceptions.CluedoException;

public class ClueInfo {

    private List<Weapon> weapons;
    private List<Suspect> suspects;
    private List<Room> rooms;

    public ClueInfo(String clueName, List<Weapon> weapons, List<Suspect> suspects, List<Room> rooms) {

        // System.out.println();
        // System.out.println(clueName);
        // System.out.println();

        this.weapons = weapons;
        this.suspects = suspects;
        this.rooms = rooms;
        checkIfClueExists(clueName.toLowerCase());
    }

    private void checkIfClueExists(String clueName) {
        checkIfWeaponExists(clueName);
        checkIfSuspectExists(clueName);
        checkIfRoomExists(clueName);
    }

    private Weapon checkIfWeaponExists(String clueName) {
        Optional<Weapon> optionalWeapon = weapons.stream()
                .filter(weapon -> weapon.getName().trim().toLowerCase().equals(clueName))
                .findFirst();

        if (!optionalWeapon.isPresent()) {
            throw new CluedoException("This weapon doesn't exist");
        }
        Weapon weapon = optionalWeapon.get();
        weapon.setName(weapon.getName() + " 0");
        return optionalWeapon.get();
    }

    private Suspect checkIfSuspectExists(String clueName) {
        Optional<Suspect> optionalSuspect = suspects.stream()
                .filter(suspect -> suspect.getName().trim().toLowerCase().equals(clueName))
                .findFirst();

        if (!optionalSuspect.isPresent()) {
            throw new CluedoException("This suspect doesn't exist");
        }
        Suspect suspect = optionalSuspect.get();
        suspect.setName(suspect.getName() + " 0");
        return optionalSuspect.get();
    }

    private Room checkIfRoomExists(String clueName) {
        Optional<Room> optionalRoom = rooms.stream()
                .filter(room -> room.getName().trim().toLowerCase().equals(clueName))
                .findFirst();

        if (!optionalRoom.isPresent()) {
            throw new CluedoException("This room doesn't exist");
        }
        Room room = optionalRoom.get();
        room.setName(room.getName() + " 0");
        return optionalRoom.get();
    }
}