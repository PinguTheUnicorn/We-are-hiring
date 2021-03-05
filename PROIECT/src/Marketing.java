public class Marketing extends Department{
    // Constructor
    public Marketing() {
        super();
    }

    // Metoda ce calculeaza budgetul necesar departamentului IT
    // Suma adaugata la budgetul  va depinde de fiecare salariu asa cum a fost
    // precizat in cerinta
    @Override
    public double getTotalSalaryBudget() {
        double totalSalary = 0.0;

        for (Employee employee:
             getEmployees()) {
            double taxe = 1.16;
            if (employee.getSalariu() > 5000)
                taxe = 1.1;
            else if (employee.getSalariu() < 3000)
                taxe = 1;

            totalSalary += employee.getSalariu() * taxe;
        }

        return totalSalary;
    }
}
