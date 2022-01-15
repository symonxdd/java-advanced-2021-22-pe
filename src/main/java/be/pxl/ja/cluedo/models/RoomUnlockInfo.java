package be.pxl.ja.cluedo.models;

import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.Scanner;

import be.pxl.ja.cluedo.exceptions.CluedoException;

public class RoomUnlockInfo {

    private Room roomToUnlock;
    private Random random;
    private List<Riddle> riddles;
    private Scanner scanner;

    public RoomUnlockInfo(String roomNameToUnlock, Mansion mansion, Random random, List<Riddle> riddles,
            Scanner scanner) {
        this.roomToUnlock = checkIfRoomExists(roomNameToUnlock, mansion);
        this.random = random;
        this.riddles = riddles;
        this.scanner = scanner;
    }

    public Scanner getScanner() {
        return this.scanner;
    }

    public Room getRoomToUnlock() {
        return this.roomToUnlock;
    }

    public Random getRandom() {
        return this.random;
    }

    public List<Riddle> getRiddles() {
        return this.riddles;
    }

    /**
     * Check if the Room exists and return an Optional<Room> if it does,
     * else a CluedoException is thrown
     * 
     * @param roomNameToUnlock The Room to unlock
     * @param mansion          The Mansion in which the check is done
     */
    private Room checkIfRoomExists(String roomNameToUnlock, Mansion mansion) {
        Optional<Room> optionalRoom = mansion.getShuffledRooms().stream()
                .filter(room -> room.getName().equals(roomNameToUnlock))
                .findFirst();

        if (!optionalRoom.isPresent()) {
            throw new CluedoException("This room doesn't exist");
        }
        return optionalRoom.get();
    }
}
