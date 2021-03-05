import java.util.ArrayList;
import java.util.Collection;

public abstract class Department {
    private ArrayList<Employee> employees;
    private ArrayList<Job> jobs;

    // Constructor
    public Department() {
        employees = new ArrayList<>();
        jobs = new ArrayList<>();
    }

    public abstract double getTotalSalaryBudget();

    // Metoda ce intoarce toate job-urile atribuite acestui departament
    public ArrayList<Job> getJobs() {
        return jobs;
    }

    // Metoda ce intoarce toti angajatii din acest departament
    public ArrayList<Employee> getEmployees() {
        return employees;
    }

    // Metode de add
    public void add(Employee employee) {
        employees.add(employee);
    }

    public void addAll(Collection<Employee> employees) {
        this.employees.removeAll(employees);
    }

    public void add(Job job) {
        jobs.add(job);
        Application app = Application.getInstance();
        for (Company company:
                app.getCompanies()) {
            if (company.contains(this))
                company.add(job);
        }
    }

    // Metoda de remove
    public void remove(Employee employee) {
        employees.remove(employee);
    }
}
