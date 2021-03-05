import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class Manager extends Employee {
    private LinkedList<Request> cererei;

    // Constructor
    public Manager(Information information, Education education, String companie,
                   double salary) throws ResumeIncompleteException{
        super(information, education, companie, salary);
        cererei = new LinkedList<>();
    }

    // Metoda pentru a adauga o cerere
    public void add(Request cerere) {
        cererei.add(cerere);
    }

    // Metoda de a elimina o cerere
    public void remove(Request request) {
        cererei.remove(request);
    }

    // Metoda de procesare a cererilor unui job
    public void process(Job job) throws ResumeIncompleteException{
        // Se iau toate cererile legate de acest job
        ArrayList<Request> cereriJob = new ArrayList<>();
        for (Request cerere:
             cererei) {
            if(cerere.getKey() == job) {
                cereriJob.add(cerere);
                remove(cerere);
            }
        }

        // Sunt sortate in functie de score
        cereriJob.sort((o1, o2) -> o2.getScore().compareTo(o1.getScore()));

        // Se ia compania manager-ului
        Company company = Application.getInstance().getCompany(getCompany());
        int accepted = 0;
        Iterator it = cereriJob.iterator();

        // Cat timp mai sunt locuri pentru job si request-uri, se cauta useri
        // pentru a se fi angajati
        while (accepted < job.getNoPositions() && it.hasNext()) {
            // Se ia request-ul, dupa se preia user-ul
            Request request = (Request) it.next();
            User user = (User) request.getValue1();

            // Daca user-ul nu a fost angajat inca(i.e. mai exista in aplicatie)
            // Se angajeaza
            if (Application.getInstance().getUsers().contains(user)) {
                accepted++; // Creste numarul de angajati acceptati
                user.removeAll();
                user.add(getCompany());

                // Este eliminat din lista de observeri a tuturor companiilor in
                // care este adaugat
                ArrayList<Company> forObservatorElimination = Application.getInstance()
                        .getCompanies();

                for (Company company1:
                        forObservatorElimination) {
                    if (company1.contains(user))
                        company1.removeObserver(user);
                }

                // Este convertit la employee
                Employee employee = user.convert();
                Application.getInstance().remove(user);

                // Ii este adaugata o noua experienta de munca la compania curenta
                Experience experience;
                LocalDate localDate = LocalDate.now();
                DateTimeFormatter format = DateTimeFormatter.ofPattern("dd/MM/yyyy");

                try {
                    experience = new Experience(localDate.format(format),
                            null, getCompany(), job.getName());
                    employee.add(experience);
                } catch (InvalidDatesException e) {
                    e.printStackTrace();
                }

                // Este adaugat in departamentul job-ului respectiv
                for (Department department :
                        company.getDepartments()) {
                    if (department.getJobs().contains(job)) {
                        company.add(employee, department);
                    }
                }
            }
        }

        // Se verifica daca mai exista request-uri pentru job-ul curent
        if(hasRequests(job)) {
            // In caz afirmativ, se trimite o notificare de respingere si sunt
            // eliminate toate cererile
            company.notifyAllObservers(new Notification("Ati fost respins de" +
                    " la job-ul " + job.getName() + " din compania " +
                    getCompany()), job);
            cererei.removeIf(request -> request.getKey() == job);
        }

        // Job-ul se inchide
        job.close();

    }

    // Metoda ce verifica daca mai exista request-uri pentru un anumit job
    public boolean hasRequests(Job job) {
        for (Request request:
             cererei) {
            if (request.getKey() == job)
                return true;
        }

        return false;
    }
}
