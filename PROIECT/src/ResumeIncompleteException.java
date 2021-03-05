// Exceptie creata pentru cazul in care un
// Resume nu a fost creat cu datele necesare

public class ResumeIncompleteException extends Exception{
    public ResumeIncompleteException(String mesaj) {
        super(mesaj);
    }
}
