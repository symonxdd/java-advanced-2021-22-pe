package be.pxl.ja.cluedo.commands;

import be.pxl.ja.cluedo.commands.interfaces.Command;
import be.pxl.ja.cluedo.models.Room;
import be.pxl.ja.cluedo.exceptions.RoomLockedException;

public class DescribeCommand implements Command<Room> {

    @Override
    public void execute(Room room) {
        System.out.println();
        try {
            room.describe();
        } catch (RoomLockedException e) {
            // TODO: meld bij de demo opname

            // wij doen niets, aangezien het zo in de opgave staat (p.5)
            // maar we moeten het in een try steken, want de RoomLockedException is
            // een Checked exception, en dit wordt ook gevraagd in de opgave
        }
        System.out.println();
    }
}