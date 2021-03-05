/* Clasa ce modeleaza o data
   Implementata pentru a usura unele operatii viitoare */

public class MyDate implements Comparable<MyDate> {
    private int day;
    private int month;
    private int year;

    // Setters
    public void setDay(String day) {
        this.day = Integer.parseInt(day);
    }

    public void setMonth(String month) {
        this.month = Integer.parseInt(month);
    }

    public void setYear(String year) {
        this.year = Integer.parseInt(year);
    }

    // Getters
    public int getDay() {
        return day;
    }

    public int getMonth() {
        return month;
    }

    public int getYear() {
        return year;
    }

    // Metoda pentru a usura compararea a doua obiece de tip MyDate
    @Override
    public int compareTo(MyDate o) {
        if(this.year != o.year)
            return this.year - o.year;
        else
            if(this.month != o.month)
                return this.month - o.month;

        return this.day - o.day;
    }
}
