package be.pxl.ja.cluedo.models;

import java.util.ArrayList;
import java.util.List;

public class Envelope<T extends Part> {

    private List<T> secrets;

    public Envelope() {
        secrets = new ArrayList<>();
    }

    public void addSecret(T secret) {
        secrets.add(secret);
    }

    public void removeSecret(T secret) {
        secrets.remove(secret);
    }

    public boolean isSecret(T secret) {
        return secrets.contains(secret);
    }
}
