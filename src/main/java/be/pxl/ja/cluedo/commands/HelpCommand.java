package be.pxl.ja.cluedo.commands;

import be.pxl.ja.cluedo.commands.interfaces.Command;

public class HelpCommand implements Command<String> {

    @Override
    public void execute(String input) {
        System.out.println();
        System.out.println("You can use following commands:");
        System.out.println("SUSPECTS - gives you an overview of all possible suspects");
        System.out.println("ROOMS - gives you an overview of all rooms");
        System.out.println("WEAPONS - gives you an overview of all weapons");
        System.out.println("CLUE <clue> - stores information about finding a clue");
        System.out.println("GOTO <room> - your player goes to the given room");
        System.out.println(
                "ACCUSE <suspect> <weapon> - you accuse the suspect to have murdered Dr. Black  with the weapon in the current room");
        System.out.println("DESCRIBE - gives you information of the current room");
        System.out.println("UNLOCK - solve the puzzle to unlock the room");
        System.out.println();
    }
}