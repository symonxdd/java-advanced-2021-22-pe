package be.pxl.ja.cluedo.models;

import java.io.IOException;
import java.util.Scanner;

public class Cluedo {

    public static void main(String[] args) {
        try {
            Scanner keyboard = new Scanner(System.in);

            System.out.print("Enter your name: ");
            String name = keyboard.nextLine();
            System.out.printf("Hello, %s%n", name);

            GameEngine<Part> gameEngine = new GameEngine<>(keyboard);
            gameEngine.initialize(name);
            gameEngine.start();
            gameEngine.printLocation();

            while (!gameEngine.isMurderSolved()) {
                System.out.print("> ");
                gameEngine.executeCommand(keyboard.nextLine());
            }
        } catch (Exception e) {
            // IOException gaf error dat die nergens wordt opgegooid in de try
            System.out.println("A problem occurred.");
            System.out.println(e.getMessage());
        }
    }
}