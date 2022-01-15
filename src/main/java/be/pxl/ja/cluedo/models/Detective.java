package be.pxl.ja.cluedo.models;

import java.util.Optional;

import be.pxl.ja.cluedo.exceptions.CluedoException;

public class Detective {

    private final String name;
    private Room currentRoom;

    public Detective(String name) {
        this.name = name;
    }

    /**
     * Get the current Room.
     *
     * @return the current Room the Detective is in.
     */
    public Room getCurrentRoom() {
        return currentRoom;
        // Changed from Optional<Room> to Room return type
        // because a Detective always resides in some Room
    }

    /**
     * Move this Detective to another Room.
     *
     */
    public void moveTo(Room otherRoom) throws CluedoException {
        if (currentRoom != null) {
            if (getCurrentRoom().toString().toLowerCase().equals(otherRoom.toString().toLowerCase())) {
                throw new CluedoException("This move is not allowed.");
            }
        }
        currentRoom = otherRoom;
    }
}
