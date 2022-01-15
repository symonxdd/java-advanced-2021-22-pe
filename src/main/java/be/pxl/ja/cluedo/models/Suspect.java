package be.pxl.ja.cluedo.models;

public class Suspect extends Part {

    private String title;
    private String occupation;
    private String nationality;
    private int age;

    public Suspect(String name) {
        super(name);
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getOccupation() {
        return this.occupation;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

    public String getNationality() {
        return this.nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public int getAge() {
        return this.age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return getTitle() + " " + getName();
    }
}
