package be.pxl.ja.cluedo.util;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import be.pxl.ja.cluedo.models.CluedoPartReader;

public class CluedoPartReaderTest {

    private static final String CLUEDO_CONFIG_PATH = "src/main/resources/cluedo.txt";
    private static final int DEFAULT_WEAPONS_AND_SUSPECTS_SIZE = 6;
    private static final int DEFAULT_ROOMS_SIZE = 9;
    private CluedoPartReader cluedoPartReader;

    @BeforeEach
    public void init() {

        cluedoPartReader = new CluedoPartReader();
        cluedoPartReader.readFile(CLUEDO_CONFIG_PATH);
    }

    @Test
    public void getWeaponsReturnsListOfWeaponsOfCorrectSize() {
        assertEquals(cluedoPartReader.getWeapons().size(), DEFAULT_WEAPONS_AND_SUSPECTS_SIZE);
    }

    @Test
    public void getSuspectsReturnsListOfSuspectsOfCorrectSize() {
        assertEquals(cluedoPartReader.getSuspects().size(), DEFAULT_WEAPONS_AND_SUSPECTS_SIZE);
    }

    @Test
    public void getRoomsReturnsListOfRoomsOfCorrectSize() {
        assertEquals(cluedoPartReader.getRooms().size(), DEFAULT_ROOMS_SIZE);
    }
}