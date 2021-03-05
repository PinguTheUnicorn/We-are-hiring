import java.util.ArrayList;

public class Information {
    private String nume;
    private String prenume;
    private String email;
    private String telefon;
    private String birthday;
    private String sex;
    private ArrayList<String> language;
    private ArrayList<String> languageLevel;

    // Constructor
    public Information(String nume, String prenume, String email,
                       String telefon, String birthday, String sex,
                       ArrayList<String> language, ArrayList<String> languageLevel) {
        this.nume = nume;
        this.prenume = prenume;
        this.email = email;
        this.telefon = telefon;
        this.birthday = birthday;
        this.sex = sex;
        this.language = language;
        this.languageLevel = languageLevel;
    }

    // Setters
    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    public void setNume(String nume) {
        this.nume = nume;
    }

    public void setPrenume(String prenume) {
        this.prenume = prenume;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public void setTelefon(String telefon) {
        this.telefon = telefon;
    }

    // Metoda de adaugare a unei noi limbi
    public void add(String language, String level) {
        this.language.add(language);
        this.languageLevel.add(level);
    }

    // Getters
    public ArrayList<String> getLanguage() {
        return language;
    }

    public ArrayList<String> getLanguageLevel() {
        return languageLevel;
    }

    public String getBirthday() {
        return birthday;
    }

    public String getEmail() {
        return email;
    }

    public String getNume() {
        return nume;
    }

    public String getPrenume() {
        return prenume;
    }

    public String getSex() {
        return sex;
    }

    public String getTelefon() {
        return telefon;
    }

    @Override
    public String toString() {
        return "Information{" +
                "nume='" + nume + '\'' +
                ", prenume='" + prenume + '\'' +
                ", email='" + email + '\'' +
                ", telefon='" + telefon + '\'' +
                ", birthday='" + birthday + '\'' +
                ", sex='" + sex + '\'' +
                ", language=" + language +
                ", languageLevel=" + languageLevel +
                '}';
    }
}
