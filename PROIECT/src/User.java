import java.util.ArrayList;

public class User extends Consumer implements Observer{
    ArrayList<String> companies;

    // Constructor
    public User(Information information, Education education) throws ResumeIncompleteException{
        super(information, education);
        companies = new ArrayList<>();
    }

    // Metoda ce elimina toate companiile din lista de preferinte
    public void removeAll() {
        companies.clear();
    }

    // Metoda ce converteste un user la employee, arunca exceptie de tip
    // ResumeIncompliteException
    public Employee convert() throws ResumeIncompleteException{
        Application app = Application.getInstance();
        Job job = null;
        // Se cauta job-ul de care a fost angajat user-ul
        for (Company company1:
                app.getCompanies()) {
            for (Job job1:
                 company1.getJobs()) {
                if (job1.getUser(getInformation().getNume()) != null)
                    job = job1;
            }
        }

        // Se creaza un obiect employee cu datele user-ului
        Employee convertit = new Employee(getInformation(), getEducation().get(0),
                companies.get(0), job.getSalary());

        // Se salveeaza lista de prieteni, educatie si experienta
        for (Consumer friend:
             getFriends()) {
            convertit.add(friend);
        }

        for (Education ed:
             getEducation()) {
            if (!convertit.getExperience().contains(ed))
                convertit.add(ed);
        }

        for (Experience exp:
             getExperience()) {
            convertit.add(exp);
        }

        return convertit;
    }

    // Se intoarce score-ul total al user-ului
    public Double getTotalScore() {
        return getTotalExperience() * 1.5 + meanGPA();
    }

    // Metoda de adaugare a unei companii
    public void add(String company) {
        companies.add(company);

        // User-ul se adauga la lista de observatori a companiei
        Company company1 = Application.getInstance().getCompany(company);
        company1.addObserver(this);
    }

    // Metoda de eliminare a unei companii din lista
    public void remove(String company) {
        companies.remove(company);

        // User-ul este scos din lista de observeri
        Company company1 = Application.getInstance().getCompany(company);
        company1.removeObserver(this);
    }

    // Metoda de update ce afiseaza mesajul notificarii
    // Parte a design pattern-ului Observer
    @Override
    public void update(Notification notification) {
        System.out.println("Utilizatorul " + getInformation().getNume() + " a primit notificarea\n");
    }
}
