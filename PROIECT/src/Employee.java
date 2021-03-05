public class Employee extends Consumer {
    private String companie;
    private double salariu;

    // Constructor
    public Employee(Information information, Education education,
                    String companie, double salariu) throws ResumeIncompleteException{
        super(information, education);
        this.companie = companie;
    }

    // Setters
    public void setCompanie(String companie) {
        this.companie = companie;
    }

    public void setSalariu(double salariu) {
        this.salariu = salariu;
    }

    // Getters
    public String getCompany() {
        return companie;
    }

    public double getSalariu() {
        return salariu;
    }
}
