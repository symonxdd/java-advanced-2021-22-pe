package be.pxl.ja.cluedo.commands;

import java.util.List;
import java.util.Random;
import java.util.Scanner;

import be.pxl.ja.cluedo.commands.interfaces.Command;
import be.pxl.ja.cluedo.models.Anagram;
import be.pxl.ja.cluedo.models.CrackTheCode;
import be.pxl.ja.cluedo.models.Riddle;
import be.pxl.ja.cluedo.models.Room;
import be.pxl.ja.cluedo.models.RoomUnlockInfo;

public class UnlockCommand implements Command<RoomUnlockInfo> {

    @Override
    public void execute(RoomUnlockInfo roomUnlockInfo) {

        Room roomToUnlock = roomUnlockInfo.getRoomToUnlock();
        Random random = roomUnlockInfo.getRandom();
        List<Riddle> riddles = roomUnlockInfo.getRiddles();
        Scanner scanner = roomUnlockInfo.getScanner();

        Riddle randomRiddle = riddles.stream().skip(random.nextInt(riddles.size())).findFirst().get();
        String answer;

        switch (randomRiddle.getClass().getSimpleName()) {
            case "Anagram":
                Anagram anagram = (Anagram) randomRiddle;

                System.out.println("\nRiddle Category: Anagram");
                System.out.println("Anagram: " + anagram.getAnagram());
                System.out.println("Description: " + anagram.getDescription() + "\n");

                System.out.print("Your Answer > ");
                answer = scanner.nextLine();

                anagram.getSolution();
                if (anagram.test(answer)) {
                    roomToUnlock.unlock();
                    System.out.println("\n" + roomToUnlock.getName() + " has been unlocked.\n");
                } else {
                    System.out.println("\nWrong answer, try again.\n");
                }
                break;
            case "CrackTheCode":
                CrackTheCode crackTheCode = (CrackTheCode) randomRiddle;

                System.out.println("\nRiddle Category: CrackTheCode");
                System.out.println("\nCrack The Code ID: " + crackTheCode.getId() + "\n");
                System.out.println(crackTheCode.getDescription());

                System.out.print("Your Answer > ");
                answer = scanner.nextLine();

                crackTheCode.getSolution();
                if (crackTheCode.test(answer)) {
                    roomToUnlock.unlock();
                    System.out.println("\n" + roomToUnlock.getName() + " has been unlocked.\n");
                } else {
                    System.out.println("\nWrong answer, try again.\n");
                }
                break;
        }
    }
}