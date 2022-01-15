package be.pxl.ja.cluedo.commands;

import java.util.List;

import be.pxl.ja.cluedo.commands.interfaces.Command;
import be.pxl.ja.cluedo.models.Room;

public class RoomsCommand implements Command<List<Room>> {

    @Override
    public void execute(List<Room> rooms) {
        System.out.println();
        rooms.stream()
                .forEach(room -> System.out.println(room.getName()));
        System.out.println();
    }
}