// Clasa ce implementeaza design pattern-ul Factory

public class DepartmentFactory {
    // Metoda specifica pattern-ului
    public static Department factory(String departmentName) {
        // Se verifica tipul obiectului dorit si se alege instantierea
        if (departmentName.equals("IT"))
            return new IT();
        if (departmentName.equals("Finance"))
            return new Finance();
        if (departmentName.equals("Marketing"))
            return new Marketing();
        if (departmentName.equals("Management"))
            return new Management();
        return null;
    }
}
