package be.pxl.ja.cluedo.models;

import java.util.Objects;

public abstract class Part {

    private String name;

    public Part(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    // TODO: ben niet zeker of de default implementatie correct is
    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Part)) {
            return false;
        }
        Part part = (Part) o;
        return Objects.equals(name, part.name);
    }

    // TODO: ben niet zeker of de default implementatie correct is
    @Override
    public int hashCode() {
        return Objects.hashCode(name);
    }

    @Override
    public String toString() {
        return getName();
    }
}