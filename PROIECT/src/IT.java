public class IT extends Department{
    // Constructor
    public IT() {
        super();
    }

    // Metoda ce calculeaza budgetul necesar departamentului IT
    // Deoarece acestia nu platesc taxe, budgetul va reprezenta suma tuturor
    // salariilor
    @Override
    public double getTotalSalaryBudget() {
        double totalSalary  = 0.0;

        for (Employee employee:
             getEmployees()) {
            totalSalary += employee.getSalariu();
        }

        return totalSalary;
    }
}
