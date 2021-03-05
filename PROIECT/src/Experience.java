import java.util.StringTokenizer;

public class Experience implements Comparable<Experience>{
    private MyDate start;
    private MyDate end;
    private String companie;
    private String position;

    // Constructor
    public Experience(String startDate, String endDate, String companie,
                      String position) throws InvalidDatesException{
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
        if(end != null) {
            if(start.compareTo(end) >= 0)
                throw new InvalidDatesException("Datele de inceput si sfarsit" +
                        " nu sunt corespunzatoare\n");
        }

        this.companie = companie;
        this.position = position;
    }

    // Setters ce arunca o Exceptie de tip InvalidDatesException
    public void setStart(MyDate start) throws InvalidDatesException {
        if(start.compareTo(end) >= 0)
            throw new InvalidDatesException("Datele de inceput si sfarsit" +
                    " nu sunt corespunzatoare\n");
        this.start = start;
    }

    public void setEnd(MyDate end) throws InvalidDatesException{
        if(start.compareTo(end) >= 0)
            throw new InvalidDatesException("Datele de inceput si sfarsit" +
                    " nu sunt corespunzatoare\n");
        this.end = end;
    }

    // Setters
    public void setCompanie(String companie) {
        this.companie = companie;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    // Getters
    public MyDate getEnd() {
        return end;
    }

    public MyDate getStart() {
        return start;
    }

    public String getCompanie() {
        return companie;
    }

    public String getPosition() {
        return position;
    }

    // Metoda ce intoarce numarul de anii petrecuti in aceasta companie
    public int getYears() {
        if(end == null)
            return 0;

        int years = end.getYear() - start.getYear();
        if(end.getMonth() > start.getMonth())
            years++;

        return years;
    }

    // Metoad de compare ce vine prin implementarea interfetei Comparable
    @Override
    public int compareTo(Experience o) {
        // Daca se pot compara datele de final, se folosesc aceastea
        if(this.end != null && o.end != null) {
            MyDate d1 = this.end;
            MyDate d2 = o.end;

            if(d1.compareTo(d2) != 0)
                return d1.compareTo(d2) * -1;


        }

        // Altfel, se compara crescator numele companiilor
        return getCompanie().compareTo(o.getCompanie());
    }
}
