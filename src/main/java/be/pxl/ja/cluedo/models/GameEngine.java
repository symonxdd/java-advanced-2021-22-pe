package be.pxl.ja.cluedo.models;

import be.pxl.ja.cluedo.commands.*;
import be.pxl.ja.cluedo.exceptions.CluedoException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class GameEngine<T extends Part> {

    private static final Random RANDOM = new Random();

    // TODO: voor deadline, deze lijn aanpassen naar cluedo.txt (het origineel)
    private static final int DEFAULT_WEAPONS_AND_SUSPECTS_SIZE = 2; // default is 6
    private static final String CLUEDO_CONFIG_PATH = "src/main/resources/cluedo_testing.txt";
    private static final String ANAGRAM_RIDDLE_PATH = "src/main/resources/anagrams.txt";
    private static final String CRACK_THE_CODE_RIDDLE_PATH = "src/main/resources/crackthecode.txt";
    private Detective detective;
    private Mansion mansion;
    private CluedoPartReader cluedoPartReader;
    private CluedoAnagramRiddleReader cluedoAnagramRiddleReader;
    private CluedoCrackTheCodeRiddleReader cluedoCrackTheCodeRiddleReader;
    private final Scanner scanner;
    private boolean murderSolved;
    private String commandFirstParamater = "";
    private String commandSecondParamater = "";
    private SuspectsCommand suspectsCommand = new SuspectsCommand();
    private DescribeCommand describeCommand = new DescribeCommand();
    private UnlockCommand unlockCommand = new UnlockCommand();
    private AccuseCommand accuseCommand = new AccuseCommand();
    private WeaponsCommand weaponsCommand = new WeaponsCommand();
    private RoomsCommand roomsCommand = new RoomsCommand();
    private HelpCommand helpCommand = new HelpCommand();
    private List<Room> rooms = new ArrayList<>();
    private List<Weapon> weapons = new ArrayList<>();
    private List<Suspect> suspects = new ArrayList<>();
    private List<Riddle> riddles = new ArrayList<>();

    public GameEngine(Scanner scanner) {
        this.scanner = scanner;
    }

    public void initialize(String playerName) {

        cluedoPartReader = new CluedoPartReader();
        cluedoPartReader.readFile(CLUEDO_CONFIG_PATH);
        weapons = cluedoPartReader.getWeapons();
        suspects = cluedoPartReader.getSuspects();
        rooms = cluedoPartReader.getRooms();

        cluedoAnagramRiddleReader = new CluedoAnagramRiddleReader();
        cluedoAnagramRiddleReader.readFile(ANAGRAM_RIDDLE_PATH);
        riddles.addAll(cluedoAnagramRiddleReader.getAnagrams());

        cluedoCrackTheCodeRiddleReader = new CluedoCrackTheCodeRiddleReader();
        cluedoCrackTheCodeRiddleReader.readFile(CRACK_THE_CODE_RIDDLE_PATH);
        riddles.addAll(cluedoCrackTheCodeRiddleReader.getCrackTheCodeRiddles());

        murderSolved = false;

        detective = new Detective(playerName);
        mansion = new Mansion(rooms);

        Room startingHallRoom = rooms.stream()
                .filter(room -> room.getName().equals(Mansion.HALL))
                .findFirst().get();

        startingHallRoom.unlock();
        detective.moveTo(startingHallRoom);

        Weapon randomWeapon = weapons.stream().skip(RANDOM.nextInt(weapons.size())).findFirst().get();
        Suspect randomSuspect = suspects.stream().skip(RANDOM.nextInt(suspects.size())).findFirst().get();

        Room randomRoom = mansion.getShuffledRooms().stream().skip(RANDOM.nextInt(mansion.getShuffledRooms().size()))
                .findFirst().get();
        randomRoom.setCrimeScene(true);
        randomRoom.setSuspect(randomSuspect);
        randomRoom.setWeapon(randomWeapon);

        mansion.getEnvelope().addSecret(randomRoom);
        mansion.getEnvelope().addSecret(randomWeapon);
        mansion.getEnvelope().addSecret(randomSuspect);

        List<Room> restOfRooms = mansion.getShuffledRooms().stream()
                .filter(room -> room.getName() != randomRoom.getName())
                .collect(Collectors.toList());

        List<Weapon> restOfWeapons = weapons.stream()
                .filter(weapon -> weapon.getName() != randomWeapon.getName())
                .collect(Collectors.toList());

        List<Suspect> restOfSuspects = suspects.stream()
                .filter(suspect -> suspect.getName() != randomSuspect.getName())
                .collect(Collectors.toList());

        IntStream.range(0, DEFAULT_WEAPONS_AND_SUSPECTS_SIZE - 1)
                .forEach(index -> {
                    restOfRooms.get(index).setWeapon(restOfWeapons.get(index));
                    restOfRooms.get(index).setSuspect(restOfSuspects.get(index));
                });
    }

    public void start() {
        System.out.println("Who murdered Dr. Black? Where did the crime took place, and which weapon was used?");
        System.out.println("Type 'help' for information...");
    }

    /**
     * Handle the command.
     * 
     * @param command a command: goto, clue, describe,...
     */
    public void executeCommand(String command) {

        try {
            String commandSplit[] = command.split(" ");
            command = commandSplit[0];

            commandFirstParamater = commandSplit[1].toString().substring(0, 1).toUpperCase()
                    + commandSplit[1].substring(1).toLowerCase();
            commandSecondParamater = commandSplit[2].toString().substring(0, 1).toUpperCase()
                    + commandSplit[2].substring(1).toLowerCase();
        } catch (Exception e) {
            // TODO: we printen best iets? is zeker goede conventie
            // en wordt sws gevraagd van leerkrachten

            // Zorgt ervoor dat geen error geeft wanneer het command ingegeven wordt
        }

        switch (command.toLowerCase()) {
            case "help":
                helpCommand.execute(null);
                break;
            case "suspects":
                suspectsCommand.execute(suspects);
                break;
            case "rooms":
                roomsCommand.execute(mansion.getSortedRooms());
                break;
            case "weapons":
                weaponsCommand.execute(weapons);
                break;
            case "describe":
                describeCommand.execute(detective.getCurrentRoom());
                break;
            case "clue":
                try {
                    new ClueInfo(commandFirstParamater, weapons, suspects, rooms);
                } catch (CluedoException e) {
                    System.out.println(e.getMessage());
                }
                break;
            case "goto":
                Optional<Room> optionalRoom = mansion.getShuffledRooms().stream()
                        .filter(room -> room.getName().equals(commandFirstParamater))
                        .findFirst();

                if (optionalRoom.isPresent()) {
                    Room room = optionalRoom.get();
                    try {
                        if (room.isLocked()) {
                            System.out.println(
                                    "This Room is locked. Issue the UNLOCK <room> command to try to unlock it.");
                            printLocation();
                        } else {
                            detective.moveTo(room);
                            printLocation();
                        }

                    } catch (CluedoException e) {
                        System.out.println(e.getMessage());
                    }
                } else {
                    System.out.println("This room doesn't exist");
                }
                break;
            case "unlock":
                try {
                    if (commandFirstParamater.isEmpty()) {
                        throw new CluedoException("Please specify a parameter (Room to unlock)");
                    }
                    RoomUnlockInfo roomUnlockInfo = new RoomUnlockInfo(commandFirstParamater,
                            mansion, RANDOM, riddles, scanner);
                    unlockCommand.execute(roomUnlockInfo);
                    printLocation();
                } catch (CluedoException e) {
                    System.out.println(e.getMessage());
                }
                break;
            case "accuse":
                try {
                    if (commandFirstParamater.equals("") || commandSecondParamater.equals("")) {
                        throw new CluedoException("Please specify 2 parameters (<suspect> <weapon>)");
                    }

                    AccuseInfo accuseInfo = new AccuseInfo(detective.getCurrentRoom(), commandFirstParamater,
                            commandSecondParamater, weapons, suspects, mansion, RANDOM);
                    accuseCommand.execute(accuseInfo);

                    System.out.println("The game ended. You've won, great job!");
                    murderSolved = true;
                } catch (CluedoException e) {
                    System.out.println(e.getMessage());
                }
                break;
            default:
                System.out.println("Command doesn't exist. Run 'help' for a list of available commands.");
                break;
        }
    }

    public void printLocation() {
        System.out.println("You are in the " + detective.getCurrentRoom());
    }

    public boolean isMurderSolved() {
        return murderSolved;
    }
}