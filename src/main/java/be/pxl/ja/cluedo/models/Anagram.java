package be.pxl.ja.cluedo.models;

public class Anagram extends Riddle {

    private String anagram;
    private String description;
    private String solution;

    public Anagram() {
        super();
    }

    public Anagram(String anagram, String description, String solution) {
        this();
        this.anagram = anagram;
        this.description = description;
        this.solution = solution;
    }

    /**
     * Test a Predicate
     *
     * @return true if answer is true, else false
     */
    @Override
    public boolean test(String answer) {
        return answer.equals(getSolution());
    }

    public String getAnagram() {
        return this.anagram;
    }

    public void setAnagram(String anagram) {
        this.anagram = anagram;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSolution() {
        return this.solution;
    }

    public void setSolution(String solution) {
        this.solution = solution;
    }
}