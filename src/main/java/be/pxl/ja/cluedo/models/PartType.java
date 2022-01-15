package be.pxl.ja.cluedo.models;

public enum PartType {

    ROOM("ROOM"),
    WEAPON("WEAPON"),
    SUSPECT("SUSPECT");

    private String partName;

    PartType(String partName) {
        this.partName = partName;
    }

    public String getPartName() {
        return partName;
    }
}