public class Recruiter extends Employee {
    private double rating = 5;

    // Constructor
    public Recruiter(Information information, Education education, String comany,
                     double salary) throws ResumeIncompleteException{
        super(information, education, comany, salary);

    }

    // Metoda evaluate
    public int evaluate(Job job, User user) {
        double score = rating * user.getTotalScore();
        rating += 0.1;

        // Daca sunt respectate cerintele minimale, este trimis un request pentru acest user
        if (job.meatsRequirments(user)) {
            Company company = Application.getInstance().getCompany(job.getCompany());

            Manager manager = company.getManager();

            manager.add(new Request(job, user, this, score));

            job.add(user);
        }

        // Se intoarce score-ul obtinut
        return (int)score;
    }

    public double getRating() {
        return rating;
    }
}
