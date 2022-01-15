package be.pxl.ja.cluedo.models;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class Assembly<T extends Part> {
    
    List<T> parts = new ArrayList<>();

    public Assembly(List<T> parts) {
        this.parts = parts;
    }

    public int getNumberOfParts() {
        return parts.size();
    }

    public Optional<T> getPart(String name) {
        return parts.stream()
                .filter(part -> part.getName() == name)
                .findFirst();
    }

    public T getPart(int index) {
        return parts.get(index);
    }

    public List<T> getSortedParts() {
        return parts.stream()
                .sorted(Comparator.comparing(Part::getName)) // method reference
                .collect(Collectors.toList());
    }

    public List<T> getShuffledParts() {
        return parts;
    }
}