package be.pxl.ja.cluedo.commands;

import java.util.List;

import be.pxl.ja.cluedo.commands.interfaces.Command;
import be.pxl.ja.cluedo.models.Weapon;

public class WeaponsCommand implements Command<List<Weapon>> {

    @Override
    public void execute(List<Weapon> weapons) {
        System.out.println();
        weapons.stream()
                .forEach(weapon -> System.out.println(weapon.getName()));
        System.out.println();
    }
}