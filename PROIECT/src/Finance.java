public class Finance extends Department {
    // Constructor
    public Finance() {
        super();
    }

    // Metoda ce calculeaza budgetul necesar departamentului IT
    // Suma adaugata la budget va depinde de anii de experienta a fiecarui angajat
    @Override
    public double getTotalSalaryBudget() {
        double totalSalary = 0.0;

        for (Employee employee:
             getEmployees()) {
            double taxe = 1.16;
            if (employee.getTotalExperience() < 1)
                taxe = 1;

            totalSalary += employee.getSalariu() * taxe;
        }

        return totalSalary;
    }
}
