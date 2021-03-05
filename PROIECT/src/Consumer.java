import java.util.*;

public abstract class Consumer {
    private Resume resume;
    private ArrayList<Consumer> friends;

    public Consumer(Information information, Education education) throws
            ResumeIncompleteException{
        resume = new Resume(new Resume.ResumeBuilder(information, education));
        friends = new ArrayList<>();
    }

    static class Resume{
        private Information information;
        private ArrayList<Education> educations;
        private ArrayList<Experience> experiences;

        // Constructor ce aplica design-ul Builder
        private Resume(ResumeBuilder builder) {
            this.information = builder.information;
            this.educations = builder.educations;
            this.experiences = builder.experiences;
        }

        // Metoda de modificare a informatiei
        public void set(Information information) {
            this.information = information;
        }

        // Metoda de adaugare a unei noi education
        public void add(Education education) {
            educations.add(education);
            Collections.sort(educations);
        }

        // Metoda de adaugare a unui noi experiente
        public void add(Experience experience) {
            experiences.add(experience);
            Collections.sort(experiences);
        }

        // Getters
        public Information getInformation() {
            return information;
        }

        public ArrayList<Education> getEducations() {
            return educations;
        }

        public ArrayList<Experience> getExperiences() {
            return experiences;
        }

        // Clasa ce ajuta implementarea design-ului Builder
        static class ResumeBuilder {
            private final Information information;
            private ArrayList<Education> educations;
            private ArrayList<Experience> experiences;

            // Constructor ce arunca exceptie daca nu sunt date argumentele necesare
            public ResumeBuilder(Information information, Education education)
                    throws ResumeIncompleteException{
                if (information == null)
                    throw new ResumeIncompleteException("Lipseste information");
                if (education == null)
                    throw new ResumeIncompleteException("Lipseste educatia");
                this.information = information;
                educations = new ArrayList<>();
                educations.add(education);
                experiences = new ArrayList<>();
            }

            // Metoda pentru adaugarea experientei
            public ResumeBuilder experience(Experience experience) {
                experiences.add(experience);
                return this;
            }

            // Metoda pentru adaugarea educatiei
            public ResumeBuilder education(Education education) {
                educations.add(education);
                return this;
            }

            // Metoda de build ce intoarce produsul final(obiectul Resume)
            public Resume build() {
                return new Resume(this);
            }
        }
    }

    public void add(Education education) {
        resume.add(education);
    }

    public void add(Experience experience) {
        resume.add(experience);
    }

    public void add(Consumer consumer) {
        friends.add(consumer);
    }

    // Metoda ce modifica informatia unui consumer
    public void set(Information information) {
        resume.set(information);
    }

    // Getters pentru a ususra operatiile viitoare
    public ArrayList<Education> getEducation() {
        return resume.getEducations();
    }

    public Information getInformation() {
        return resume.getInformation();
    }

    public ArrayList<Experience> getExperience() {
        return resume.getExperiences();
    }

    // Metoda pentru a calcula gradul de prietenie fata de un alt consumer
    public int getDegreeInFriends(Consumer consumer) {
        /* Aplicandu-se algoritmul bfs, avem nevoie de o lista de consumer
           vizitati si o coada pentru a stoca ordinea consumerilor analizati*/
        int grade = 1;
        LinkedList<Consumer> queue = new LinkedList<>();
        ArrayList<Consumer> vizitat = new ArrayList<>();

        // variabila ce stocheaza cati consumeri mai trebuie verificati pana se
        // va trece la gradul urmator
        int noCurrentLevel = 1;

        // Variabila ce stocheaza numarul de consumeri pentru urmatorul nivel
        // de cautare
        int noNextLevel = 0;

        // Se adauga consumer-ul curent in coada si se noteaza vizitat
        vizitat.add(this);
        queue.add(this);

        // Se cauta cat timp exista consumeri nevizitati
        while(queue.size() != 0) {
            // Se ia consumer-ul curent
            Consumer curr = queue.poll();

            int i = 0;

            // Se verifica toti prietenii
            ListIterator<Consumer> it = curr.friends.listIterator();
            for(Consumer c = it.next(); it.hasNext();) {
                // Daca nu este vizitat, se verifica consumer-ul
                if(!vizitat.contains(c)) {
                    // Daca este cel cautat, se intoarce grad-ul curent
                    if(c == consumer)
                        return grade;

                    // Altfel, se adauga la vizitati si se pune in coada pentru
                    // a astepta sa fie verificati prietenii acestuia
                    vizitat.add(c);
                    queue.add(c);
                    i++; // se mareste numarul de prieteni nevizitati gasiti
                }
            }
            // Numarul de prieteni gasiti vor fi de pe nivelul urmator
            noNextLevel += i;

            // Scade numarul de consumeri vizitati pe nivelul curent
            noCurrentLevel--;

            // Daca nu mai exista consumeri pe nvelul curent creste gradul
            // Si se actualizeaza numarul de consumeri de verificat de pe
            // nivelul curent, respectiv cel urmator
            if(noCurrentLevel == 0) {
                noCurrentLevel = noNextLevel;
                noNextLevel = 0;
                grade++;
            }
        }

        return grade;
    }

    // Metoda de eliminare a unui consumer din prieteni
    public void remove(Consumer consumer) {
        friends.remove(consumer);
    }

    // Metoda ce intoarce lista de prieteni
    public ArrayList<Consumer> getFriends() {
        return friends;
    }

    // Metoda ce intoarce anul terminarii licentei
    public Integer getGraduationYear() {
        for (Education ed:
             getEducation()) {
            if(ed.getLevel().equals("college"))
                if(ed.getEnd() != null)
                    return ed.getEnd().getYear();
        }

        return null;
    }

    // Metoda ce intoarce media studiilor academice
    public Double meanGPA() {
        double medie = 0;

        // Se trece prin toate elementele de tip Education si se ia media
        for (Education ed:
             getEducation()) {
            medie += ed.getMean();
        }

        // Se face media mediilor
        medie /= getEducation().size();

        return medie;
    }

    // Se intoarcce numarul de ani de experienta pentru a usura unele operatii viitoare
    public int getTotalExperience() {
        int year = 0;

        for (Experience experience:
             getExperience()) {
            year += experience.getYears();
        }

        return year;
    }
}
