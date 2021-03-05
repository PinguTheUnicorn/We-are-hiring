public class Management extends Department {
    // Constructor
    public Management() {
        super();
    }

    // Metoda ce calculeaza budgetul necesar departamentului IT
    // Taxele sunt de 16%, asa ca pentru fiecare salariu se va adauga 1.16 din
    // salariu la budgetul total
    @Override
    public double getTotalSalaryBudget() {
        double totalSalary = 0.0;

        for (Employee employee:
             getEmployees()) {
            totalSalary += employee.getSalariu() * 1.16;
        }

        return totalSalary;
    }
}
