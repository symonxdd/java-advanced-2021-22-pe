package be.pxl.ja.cluedo.models;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import be.pxl.ja.cluedo.exceptions.CluedoException;

public class AccuseInfo {

    private List<Weapon> weapons;
    private List<Suspect> suspects;
    private Envelope<Part> envelope;
    private List<Part> accusedParts;
    private List<Part> wrongAccusations;
    private Random random;

    public AccuseInfo(Room accusedCurrentRoom, String accusedSuspectName, String accusedWeaponName,
            List<Weapon> weapons,
            List<Suspect> suspects, Mansion mansion, Random random) {

        accusedParts = new ArrayList<>();
        wrongAccusations = new ArrayList<>();

        this.weapons = weapons;
        this.envelope = mansion.getEnvelope();
        this.suspects = suspects;
        this.random = random;
        accusedParts.add(checkIfWeaponExists(accusedWeaponName.toLowerCase()));
        accusedParts.add(checkIfSuspectExists(accusedSuspectName.toLowerCase()));
        accusedParts.add(accusedCurrentRoom);
    }

    public Random getRandom() {
        return this.random;
    }

    public Envelope<Part> getEnvelope() {
        return this.envelope;
    }

    public List<Part> getWrongAccusations() {
        return this.wrongAccusations;
    }

    /**
     * Check if all accusations are correct. If true, the game ends.
     * If false, a list of clues are returned.
     * 
     */
    public boolean isAllCorrect() {
        int correctAccusationsCounter = 0;
        for (int i = 0; i < 3; i++) {
            if (envelope.isSecret(accusedParts.get(i))) {
                correctAccusationsCounter++;
            } else {
                wrongAccusations.add(accusedParts.get(i));
            }
        }
        return correctAccusationsCounter == 3;
    }

    /**
     * Check if the Weapon exists and return an Optional<Weapon> if it does,
     * else a CluedoException is thrown
     * 
     * @param roomNameToUnlock The Room to unlock
     * @param mansion          The Mansion in which the check is done
     */
    private Weapon checkIfWeaponExists(String accusedWeaponName) {
        Optional<Weapon> optionalWeapon = weapons.stream()
                .filter(weapon -> weapon.getName().toLowerCase().equals(accusedWeaponName))
                .findFirst();

        if (!optionalWeapon.isPresent()) {
            throw new CluedoException("This weapon doesn't exist");
        }
        return optionalWeapon.get();
    }

    /**
     * Check if the Suspect exists and return an Optional<Suspect> if it does,
     * else a CluedoException is thrown
     * 
     * @param roomNameToUnlock The Room to unlock
     * @param mansion          The Mansion in which the check is done
     */
    private Suspect checkIfSuspectExists(String accusedSuspectName) {
        Optional<Suspect> optionalSuspect = suspects.stream()
                .filter(suspect -> suspect.getName().trim().toLowerCase().equals(accusedSuspectName))
                .findFirst();

        if (!optionalSuspect.isPresent()) {
            throw new CluedoException("This suspect doesn't exist");
        }
        return optionalSuspect.get();
    }
}
