package be.pxl.ja.cluedo.util;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Scanner;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.github.stefanbirkner.systemlambda.SystemLambda.*;

import be.pxl.ja.cluedo.models.Detective;
import be.pxl.ja.cluedo.models.GameEngine;
import be.pxl.ja.cluedo.models.Part;
import be.pxl.ja.cluedo.models.Room;

public class GameEngineExecuteCommandTest {

    private static final String PLAYER_NAME = "Symon";
    private Detective detective;
    private Scanner keyboard;
    private GameEngine<Part> gameEngine;

    @BeforeEach
    public void init() {
        keyboard = new Scanner(System.in);
        gameEngine = new GameEngine<>(keyboard);
        gameEngine.initialize(PLAYER_NAME);
    }

    @Test
    public void printLocationGivesCorrectRoomLocation() throws Exception {
        detective = new Detective(PLAYER_NAME);
        detective.moveTo(new Room("Hall"));

        String text = tapSystemOut(() -> {
            gameEngine.printLocation();
        });

        assertEquals("You are in the " + detective.getCurrentRoom(), text.trim());
    }
}