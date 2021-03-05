// Exceptie creata pentru cazul in care Datele claselor Education sau
// Experience nu sunt conforme

public class InvalidDatesException extends Exception {
    public InvalidDatesException(String e) {
        super(e);
    }
}
