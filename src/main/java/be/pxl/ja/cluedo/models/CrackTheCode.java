package be.pxl.ja.cluedo.models;

public class CrackTheCode extends Riddle {

    private String id;
    private String description;
    private String solution;

    public CrackTheCode() {
        super();
    }

    public CrackTheCode(String id, String description, String solution) {
        this();
        this.id = id;
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

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
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