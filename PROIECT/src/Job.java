import java.util.ArrayList;

public class Job {
    private String name;
    private String company;
    private boolean on;
    private Constraint absolvire, experience, mean;
    private ArrayList<User> candidati;
    private int noPositions;
    private double salary;

    // Constructor
    public Job(String name, String company, boolean on, Constraint absolvire,
               Constraint experience, Constraint mean, int noPositions,
               double salary) {
        this.absolvire = absolvire;
        this.name = name;
        this.company = company;
        this.on = on;
        this.experience = experience;
        this.mean = mean;
        this.noPositions = noPositions;
        this.salary = salary;
        candidati = new ArrayList<>();
    }

    // Metoda ce permite unui user sa plice la job
    public void apply(User user) {
        // Daca job-ul nu este deschis, se iese din aplicatie
        if(!isOn())
            return;

        // Se cauta un recruiter pentru a face evaluarea user-ului
        Company company = Application.getInstance()
                .getCompany(getCompany());

        Recruiter recruiter = company.getRecruiter(user);

        recruiter.evaluate(this, user);

        // Daca user-ul nu este inca observator al acestei companii, acesta este adaugat in companie
        if(!company.contains(user))
            company.addObserver(user);
    }

    // Metoda ce verifica daca sunt indeplinite cerintele minimale
    public boolean meatsRequirments(User user) {

        return absolvire.isAccepted(user.getGraduationYear()) &&
                mean.isAccepted(user.meanGPA()) &&
                experience.isAccepted(user.getTotalExperience());
    }

    // Metoda ce adauga un user in lista de candidati
    public void add(User user) {
        candidati.add(user);
    }

    // Setters
    public void setMean(Constraint mean) {
        this.mean = mean;
    }

    public void setAbsolvire(Constraint absolvire) {
        this.absolvire = absolvire;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public void setExperience(Constraint experience) {
        this.experience = experience;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setNoPositions(int noPositions) {
        this.noPositions = noPositions;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    // Metoda ce inchide aplicarea la job
    // Prin implementarea design-ului Observer se trimite o notificare de
    // nchidere a job-ului
    public void close() {
        this.on = false;
        Application app = Application.getInstance();
        app.getCompany(getCompany()).notifyAllObservers(
                new Notification("Job-ul " + getName() + " de la compania "
                        + getCompany() + " la care ati aplicat" +
                        " s-a inchis"), this);
    }

    // Metoda ce verifica daca job-ul mai este sau nu deschis pentru aplicare
    public boolean isOn() {
        return on;
    }

    // Getters
    public ArrayList<User> getCandidati() {
        return candidati;
    }

    public Constraint getAbsolvire() {
        return absolvire;
    }

    public Constraint getExperience() {
        return experience;
    }

    public Constraint getMean() {
        return mean;
    }

    public double getSalary() {
        return salary;
    }

    public int getNoPositions() {
        return noPositions;
    }

    public String getCompany() {
        return company;
    }

    public User getUser(String name) {
        for (User user:
             candidati) {
            if (user.getInformation().getNume().equals(name))
                return user;
        }
        return null;
    }

    public String getName() {
        return name;
    }
}
