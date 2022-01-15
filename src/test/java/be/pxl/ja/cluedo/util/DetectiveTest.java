package be.pxl.ja.cluedo.util;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import be.pxl.ja.cluedo.models.CluedoPartReader;
import be.pxl.ja.cluedo.models.Detective;
import be.pxl.ja.cluedo.models.Mansion;
import be.pxl.ja.cluedo.models.Room;

public class DetectiveTest {

    private static final String CLUEDO_CONFIG_PATH = "src/main/resources/cluedo.txt";
    private CluedoPartReader cluedoPartReader;
    private Detective detective;
    private Mansion mansion;

    @BeforeEach
    public void init() {
        cluedoPartReader = new CluedoPartReader();
        cluedoPartReader.readFile(CLUEDO_CONFIG_PATH);

        detective = new Detective("Symon");
        mansion = new Mansion(cluedoPartReader.getRooms());

        Room startingHallRoom = mansion.getShuffledRooms().stream()
                .filter(room -> room.getName().equals(Mansion.HALL))
                .findFirst().get();

        detective.moveTo(startingHallRoom);
    }

    @Test
    public void startingRoomOfDetectiveIsHall() {
        Room startingHallRoom = mansion.getShuffledRooms().stream()
                .filter(room -> room.getName().equals(Mansion.HALL))
                .findFirst().get();

        assertEquals(detective.getCurrentRoom(), startingHallRoom);
    }

    @Test
    public void moveToMovesTheDetectiveToAnotherRoom() {
        Room studyRoom = mansion.getShuffledRooms().stream()
                .filter(room -> room.getName().equals("Study"))
                .findFirst().get();

        detective.moveTo(studyRoom);
        assertEquals(detective.getCurrentRoom(), studyRoom);
    }
}