import java.util.ArrayList;

public class Company implements Subject{
    private final String name;
    private final Manager manager;
    private ArrayList<Job> jobs;
    private ArrayList<Employee> angajati;
    private ArrayList<Department> departments;
    private ArrayList<Recruiter> recruiters;
    private ArrayList<User> observers;

    // Constructor
    public Company(String name, Manager manager) {
        this.name = name;
        this.manager = manager;
        jobs = new ArrayList<>();
        angajati = new ArrayList<>();
        departments = new ArrayList<>();
        recruiters = new ArrayList<>();
        observers = new ArrayList<>();
    }

    // Metode de adaugare
    public void add(Department department) {
        departments.add(department);
    }

    public void add(Recruiter recruiter) {
        recruiters.add(recruiter);
        for (Department department:
             departments) {
            if (department instanceof IT)
                department.add(recruiter);
        }
    }

    public void add(Employee employee, Department department) {
        angajati.add(employee);
        department.add(employee);
    }

    public void add(Job job) {
        jobs.add(job);
        notifyAllObservers(new Notification("Am adaugat un" +
                        " adaugat un nou job\n" + "Semnat, " + job.getCompany()),
                job);
    }

    // Metode de stergere
    public void remove(Employee employee) {
        angajati.remove(employee);
        for (Department department:
             departments) {
            if (department.getEmployees().contains(employee))
                department.remove(employee);
        }
    }

    public void remove(Department department) {
        ArrayList<Employee> employees = department.getEmployees();
        for (Employee employee:
             employees) {
            remove(employee);
        }

        departments.remove(department);
    }

    public void remove(Recruiter recruiter) {
        recruiters.remove(recruiter);
        for (Department department:
             departments) {
            if (department instanceof IT)
                department.remove(recruiter);
        }
    }

    // Metode de modificare a departamentelor
    public void move(Department source, Department destination) {
        ArrayList<Employee> employees = source.getEmployees();
        destination.addAll(employees);
        remove(source);
    }

    // Metoda de mutare a unui angajat in alt departament
    public void move(Employee employee, Department newDepartment) {
        departments.removeIf(department -> department.getEmployees()
                .contains(employee));
        newDepartment.add(employee);
    }

    // Metoda ce verifica existenta anumitor date
    public boolean contains(Department department) {
        return departments.contains(department);
    }

    public boolean contains(Employee employee) {
        return angajati.contains(employee);
    }

    public boolean contains(Recruiter recruiter) {
        return recruiters.contains(recruiter);
    }

    public boolean contains(User observator) {
        return observers.contains(observator);
    }

    public Recruiter getRecruiter(User user) {
        boolean single = true;
        int degreeMax = 0;
        Recruiter recruiter = recruiters.get(0);

        for (Recruiter recruiter1:
             recruiters) {
            int degree = recruiter1.getDegreeInFriends(user);
            if (degree > degreeMax) {
                degreeMax = degree;
                recruiter = recruiter1;
                single = true;
            } if (degree == degreeMax)
                single = false;
        }

        if(!single) {
            ArrayList<Recruiter> newRecruters = new ArrayList<>(recruiters);
            newRecruters.sort((o1,o2)->(int)(o1.getRating()-o2.getRating())*-1);
            recruiter = newRecruters.get(0);
        }
        return recruiter;
    }

    // Getters
    public String getName() {
        return name;
    }

    public Manager getManager() {
        return manager;
    }

    public ArrayList<Department> getDepartments() {
        return departments;
    }

    public ArrayList<Recruiter> getRecruiters() {
        return recruiters;
    }

    public ArrayList<Job> getJobs() {
        ArrayList<Job> availableJobs = new ArrayList<>();

        for (Job job:
             jobs) {
            if(job.isOn())
                availableJobs.add(job);
        }

        return availableJobs;
    }

    // Metode ce ajut la implementarea design-ului Observer
    @Override
    public void addObserver(User user) {
        observers.add(user);
    }

    @Override
    public void removeObserver(User c) {
        observers.remove(c);
    }

    // Metoda ce notifica toti observerii legat de o schimbare
    @Override
    public void notifyAllObservers(Notification notification, Job job) {
        // se verifica ce fel de notificare se doreste trimisa
        // si se trimite la observatorii necesari
        if (job.getCandidati().isEmpty())
            notifyNewJob(notification);
        else if (job.isOn())
            notifyJobReject(notification, job);
        else
            notifyJobCLoses(notification, job);
    }

    // Metoda auxiliara ce trimite notificare utilizatorilor ce urmaresc un job
    // ca acesta a fost inchis
    private void notifyJobCLoses(Notification notification, Job job) {
        for (User user: observers) {
            if (job.getCandidati().contains(user))
                user.update(notification);
        }
    }

    // Metoda auxiliara ce trimite notificare utilizatorilor ce urmaesc un job
    // ca au fost respinsi
    private void notifyJobReject(Notification notification, Job job) {
        for (User user:
             observers) {
            if (job.getCandidati().contains(user))
                user.update(notification);
        }
    }

    // Metoda auxiliara ce trimite nnotificare tuturor observatorilor companiei
    // ca s-a adaugat un job nou
    private void notifyNewJob(Notification notification) {
        for (User user:
             observers) {
            user.update(notification);
        }
    }
}
