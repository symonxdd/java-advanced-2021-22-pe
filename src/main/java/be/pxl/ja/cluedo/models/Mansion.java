package be.pxl.ja.cluedo.models;

import java.util.List;
import java.util.Optional;

import be.pxl.ja.cluedo.exceptions.CluedoException;

public class Mansion {

    private Assembly<Room> rooms;
    public static final String HALL = "Hall";
    private Envelope<Part> envelope;

    public Mansion(List<Room> rooms) {
        this.rooms = new Assembly<Room>(rooms);
        envelope = new Envelope<>();
    }

    public Room getHall() throws CluedoException {
        Room potentialRoom = getSortedRooms().stream()
                .filter(room -> room.getName() == HALL)
                .findFirst().get();

        if (potentialRoom == null) {
            throw new CluedoException("Every mansion must have a hall.");
        }
        return potentialRoom;
    }

    public int getNumberOfRooms() {
        return getSortedRooms().size();
    }

    public Optional<Room> getRoom(String name) {
        return getSortedRooms().stream()
                .filter(room -> room.getName() == name)
                .findFirst();
    }

    public Room getRoom(int index) {
        return rooms.getPart(index);
    }

    /**
     * Returns a sorted list of all the Rooms in the Mansion
     * This was stated in the excercise
     * 
     * @param command a command: goto, clue, describe,...
     */
    public List<Room> getSortedRooms() {
        return rooms.getSortedParts();
    }

    /**
     * Returns a shuffled list of all the Rooms in the Mansion
     * This was not stated in the excercise
     * But I added it for code maintainability and readability
     * 
     * @param command a command: goto, clue, describe,...
     */
    public List<Room> getShuffledRooms() {
        return rooms.getShuffledParts();
    }

    public Envelope<Part> getEnvelope() {
        return envelope;
    }
}
