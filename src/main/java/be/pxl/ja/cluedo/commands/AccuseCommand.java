package be.pxl.ja.cluedo.commands;

import java.util.List;

import be.pxl.ja.cluedo.commands.interfaces.Command;
import be.pxl.ja.cluedo.exceptions.CluedoException;
import be.pxl.ja.cluedo.models.AccuseInfo;
import be.pxl.ja.cluedo.models.Part;

public class AccuseCommand implements Command<AccuseInfo> {

    @Override
    public void execute(AccuseInfo accuseInfo) {

        if (!accuseInfo.isAllCorrect()) {
            List<Part> clues = accuseInfo.getWrongAccusations();

            Part randomClue = clues.stream().skip(accuseInfo.getRandom().nextInt(clues.size())).findFirst().get();

            throw new CluedoException("It was not: " + randomClue.getName());
        }
    }
}
