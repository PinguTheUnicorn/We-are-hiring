import java.util.StringTokenizer;

public class Education implements Comparable<Education>{
    private MyDate start;
    private MyDate end;
    private String institution;
    private String level;
    private double mean;

    // Constructor
    public Education(String startDate, String endDate, String institution,
                     String level, double mean) throws InvalidDatesException {
        StringTokenizer token = new StringTokenizer(startDate, " .\n\\/");
        start = new MyDate();

        start.setDay(token.nextToken());
        start.setMonth(token.nextToken());
        start.setYear(token.nextToken());

        end = new MyDate();

        // Daca a fost data o data de final, se adauga
        if(endDate != null){
            token = new StringTokenizer(endDate, " .\n\\/");
            end.setDay(token.nextToken());
            end.setMonth(token.nextToken());
            end.setYear(token.nextToken());
        }

        // Se verifica daca datele sunt in ordine, altfel se arunca o Exceptie
        if(endDate != null) {
            if(end.compareTo(start) <= 0)
                throw new InvalidDatesException("Datele de inceput si sfarsit nu" +
                        "sunt corespunzatoare\n");
        }
        this.institution = institution;
        this.level = level;
        this.mean = mean;
    }

    // Setters ce arunca o Exceptie de tip InvalidDatesException
    public void setEnd(MyDate end) throws InvalidDatesException {
        if(end.compareTo(start) <= 0)
            throw new InvalidDatesException("Datele de inceput si sfarsit nu" +
                    "sunt corespunzatoare\n");
        this.end = end;
    }

    public void setStart(MyDate start) throws InvalidDatesException {
        if(end.compareTo(start) <= 0)
            throw new InvalidDatesException("Datele de inceput si sfarsit nu" +
                    "sunt corespunzatoare\n");
        this.start = start;
    }

    // Setters
    public void setInstitution(String institution) {
        this.institution = institution;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public void setMean(double mean) {
        this.mean = mean;
    }

    public MyDate getStart() {
        return start;
    }

    // Getters
    public MyDate getEnd() {
        return end;
    }

    public double getMean() {
        return mean;
    }

    public String getInstitution() {
        return institution;
    }

    public String getLevel() {
        return level;
    }

    // Metoad de compare ce vine prin implementarea interfetei Comparable
    @Override
    public int compareTo(Education o) {
        // Daca se pot compara datele de final, se folosesc aceastea
        if(this.end != null && o.end != null) {
            MyDate d1 = this.end;
            MyDate d2 = o.end;

            if(d1.compareTo(d2) != 0)
                return d1.compareTo(d2) * -1;

            double m1 = this.mean;
            double m2 = o.mean;
            return (int)(m1 - m2) * -1;
        }

        // Daca nu, se compara datele de start
        MyDate d1 = this.start;
        MyDate d2 = o.start;

        return d1.compareTo(d2) * -1;
    }
}
