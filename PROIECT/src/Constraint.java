public class Constraint {
    private final Integer inferiorLimit;
    private final Integer superiorLimit;

    // Constructor
    public Constraint(Integer inferior, Integer superior) {
        inferiorLimit = inferior;
        superiorLimit = superior;
    }

    // Metode ce verifica daca o valoare trece constrangerile

    public boolean isAccepted(int value) {
        boolean inferior = true;
        boolean superior = true;
        if (inferiorLimit != null)
            inferior = value > inferiorLimit;
        if (superiorLimit != null)
            superior = value < superiorLimit;
        return inferior && superior;
    }

    public boolean isAccepted(double value) {
        boolean inferior = true;
        boolean superior = true;
        if (inferiorLimit != null)
            inferior = value > inferiorLimit;
        if (superiorLimit != null)
            superior = value < superiorLimit;
        return inferior && superior;
    }
}
