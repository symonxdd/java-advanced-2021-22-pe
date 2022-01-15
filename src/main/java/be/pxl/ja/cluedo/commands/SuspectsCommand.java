package be.pxl.ja.cluedo.commands;

import java.util.List;

import be.pxl.ja.cluedo.commands.interfaces.Command;
import be.pxl.ja.cluedo.models.Suspect;

public class SuspectsCommand implements Command<List<Suspect>> {

    @Override
    public void execute(List<Suspect> suspects) {
        StringBuilder builder = new StringBuilder();

        System.out.println();
        suspects.stream()
                .forEach(suspect -> {
                    builder.append(suspect.getTitle());
                    builder.append(suspect.getName() + ", ");
                    builder.append(suspect.getAge() + " years old");
                    builder.append(System.lineSeparator());
                });
        System.out.println(builder.toString());
    }
}