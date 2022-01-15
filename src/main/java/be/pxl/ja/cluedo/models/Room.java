package be.pxl.ja.cluedo.models;

import java.util.Optional;

import be.pxl.ja.cluedo.exceptions.RoomLockedException;

public class Room extends Part {

    private Weapon weapon;
    private Suspect suspect;
    private boolean crimeScene;
    private boolean locked;

    public Room(String name) {
        super(name);
        locked = true;
    }

    public String describe() throws RoomLockedException {
        StringBuilder builder = new StringBuilder();

        if (isLocked()) {
            throw new RoomLockedException("Can not describe if Room is locked.");
        }

        if (crimeScene) {
            builder.append("There is blood in this room.");

        } else {
            builder.append("There is no blood in this room.");
        }

        if (getWeapon().isPresent()) {
            builder.append(getWeapon().get().getName());
        }

        if (getSuspect().isPresent()) {
            builder.append(getSuspect().get().getName());
        }

        return builder.toString();
    }

    public Optional<Suspect> getSuspect() {
        return Optional.ofNullable(suspect);
    }

    public Optional<Weapon> getWeapon() {
        return Optional.ofNullable(weapon);
    }

    public boolean isLocked() {
        return locked;
    }

    public void unlock() {
        locked = false;
    }

    public void setCrimeScene(boolean crimeScene) {
        this.crimeScene = crimeScene;
    }

    public void removeSuspect() {
        setSuspect(null);
    }

    public void setSuspect(Suspect suspect) {
        this.suspect = suspect;
    }

    public void setWeapon(Weapon weapon) {
        this.weapon = weapon;
    }
}
