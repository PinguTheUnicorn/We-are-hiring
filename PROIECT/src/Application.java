import java.util.ArrayList;
import java.util.List;

public class Application {
    private static Application app = null;
    private ArrayList<Company> companies;
    private ArrayList<User> users;

    private Application() {
        companies = new ArrayList<>();
        users = new ArrayList<>();
    }

    // Metoda specifica design-ului Singleton
    public static Application getInstance() {
        if (app == null)
            app = new Application();
        return app;
    }

    public ArrayList<Company> getCompanies() {
        return companies;
    }

    public Company getCompany(String name) {
        for (Company company:
             companies) {
            if(company.getName().equals(name)) {
                return company;
            }
        }

        return null;
    }

    public void add(Company company) {
        companies.add(company);
    }

    public void add(User user) {
        users.add(user);
    }

    public boolean remove(Company company) {
        if(companies.contains(company)) {
            companies.remove(company);
            return true;
        }
            return false;
    }

    public boolean remove(User user) {
        if(users.contains(user)) {
            users.remove(user);
            return true;
        }
        return false;
    }

    /* Metoda ce intoarce toate job-urile valide din aplicatie
       Se cauta in lista de companii si daca aceastea se gasesc printre
       companiile date, atunci se vor prelua job-urile valabile din
       respectiva companie */
    public ArrayList<Job> getJobs(List<String> companies) {
        ArrayList<Job> jobs = new ArrayList<>();

        for (Company company:
             this.companies) {
            if(companies.contains(company.getName())) {
                jobs.addAll(company.getJobs());
            }
        }

        return jobs;
    }

    // Metoda adaugata pentru a usura unele operatii
    // Intoarce toti userii din aplicatie
    public ArrayList<User> getUsers() {
        return users;
    }

    @Override
    public String toString() {
        return "Your Application";
    }
}
