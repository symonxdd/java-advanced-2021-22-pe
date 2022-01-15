package be.pxl.ja.cluedo.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import be.pxl.ja.cluedo.exceptions.RoomLockedException;
import be.pxl.ja.cluedo.models.Room;
import be.pxl.ja.cluedo.models.Suspect;
import be.pxl.ja.cluedo.models.Weapon;

public class RoomTest {

    private Room room;

    @BeforeEach
    public void init() {
        room = new Room("Study");
    }

    @Test
    public void aRoomIsByDefaultLocked() {
        assertEquals(room.isLocked(), true);
    }

    @Test
    public void unlockUnlocksTheRoom() {
        room.unlock();
        assertEquals(room.isLocked(), false);
    }

    @Test
    public void removeSuspectRemovesTheCurrentSuspectInTheRoom() {
        room.setSuspect(new Suspect("Miss Scarlet"));
        room.removeSuspect();
        assertEquals(room.getSuspect(), Optional.empty());
    }

    @Test
    public void describeThrowsRoomLockedExceptionIfCalledOnALockedRoom() {
        assertThrows(RoomLockedException.class, () -> {
            room.describe();
        });
    }

    @Test
    public void describeReturnsCorrectDescriptionOfTheRoomIfRoomIsUnlockedAndOnlyNameIsSet()
            throws RoomLockedException {
        room.unlock();
        assertEquals(room.describe(), "There is no blood in this room.");
    }

    @Test
    public void describeReturnsCorrectDescriptionOfTheRoomIfRoomIsUnlockedAndCrimeSceneIsSetAndWeaponAndSuspect()
            throws RoomLockedException {
        room.unlock();
        room.setCrimeScene(true);
        room.setWeapon(new Weapon("Knife"));
        room.setSuspect(new Suspect("Miss Scarlet"));

        StringBuilder builder = new StringBuilder();
        builder.append("There is blood in this room.");
        builder.append("Knife");
        builder.append("Miss Scarlet");

        assertEquals(room.describe(), builder.toString());
    }
}