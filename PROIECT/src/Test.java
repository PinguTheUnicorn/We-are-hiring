import java.util.ArrayList;

public class Test {
    public static void main(String[] args) {
        try {
            Manager m1 = new Manager(new Information("Jeff", "Bezos",
                    "bigbossjeff@amazon.com", "077", "", "M", null, null),
                    new Education("12/12/1990", null, "Ceva", "College", 9.99), "Amazon", 19000);

            Manager m2 = new Manager(new Information("Sundar", "Pichai",
                    "bigbossjeff@amazon.com", "077", "", "M", null, null),
                    new Education("12/12/1990", null, "Ceva", "College", 9.99), "Amazon", 19000);

            Employee e1 = new Employee(new Information("Employee", "1",
                    "bigbossjeff@amazon.com", "077", "", "M", null, null),
                    new Education("12/12/1990", null, "Ceva", "College", 9.99), "Amazon", 1200);

            Employee e2 = new Employee(new Information("Employee", "2",
                    "bigbossjeff@amazon.com", "077", "", "M", null, null),
                    new Education("12/12/1990", null, "Ceva", "College", 9.99), "Amazon", 1200);

            Employee e3 = new Employee(new Information("Employee", "3",
                    "bigbossjeff@amazon.com", "077", "", "M", null, null),
                    new Education("12/12/1990", null, "Ceva", "College", 9.99), "Amazon", 1200);

            Employee e4 = new Employee(new Information("Employee", "4",
                    "bigbossjeff@amazon.com", "077", "", "M", null, null),
                    new Education("12/12/1990", null, "Ceva", "College", 9.99), "Amazon", 1200);

            Employee e5 = new Employee(new Information("Employee", "5",
                    "bigbossjeff@amazon.com", "077", "", "M", null, null),
                    new Education("12/12/1990", null, "Ceva", "College", 9.99), "Amazon", 1200);



            Employee e6 = new Employee(new Information("Employee", "5",
                    "bigbossjeff@amazon.com", "077", "", "M", null, null),
                    new Education("12/12/1990", null, "Ceva", "College", 9.99), "Google", 1200);

            Employee e7 = new Employee(new Information("Employee", "7",
                    "bigbossjeff@amazon.com", "077", "", "M", null, null),
                    new Education("12/12/1990", null, "Ceva", "College", 9.99), "Google", 1200);

            Employee e8 = new Employee(new Information("Employee", "8",
                    "bigbossjeff@amazon.com", "077", "", "M", null, null),
                    new Education("12/12/1990", null, "Ceva", "College", 9.99), "Google", 1200);

            Employee e9 = new Employee(new Information("Employee", "9",
                    "bigbossjeff@amazon.com", "077", "", "M", null, null),
                    new Education("12/12/1990", null, "Ceva", "College", 9.99), "Google", 1200);

            Employee e10 = new Employee(new Information("Employee", "10",
                    "bigbossjeff@amazon.com", "077", "", "M", null, null),
                    new Education("12/12/1990", null, "Ceva", "College", 9.99), "Google", 1200);




            User u1 = new User(new Information("User", "1",
                    "bigbossjeff@amazon.com", "077", "", "M", null, null),
                    new Education("12/12/1990", null, "Ceva", "College", 9.99));

            User u2 = new User(new Information("User", "2",
                    "bigbossjeff@amazon.com", "077", "", "M", null, null),
                    new Education("12/12/1990", null, "Ceva", "College", 9.99));

            User u3 = new User(new Information("User", "3",
                    "bigbossjeff@amazon.com", "077", "", "M", null, null),
                    new Education("12/12/1990", null, "Ceva", "College", 9.99));

            User u4 = new User(new Information("User", "4",
                    "bigbossjeff@amazon.com", "077", "", "M", null, null),
                    new Education("12/12/1990", null, "Ceva", "College", 9.99));


            Recruiter r1 = new Recruiter(new Information("Recruiter", "1",
                    "bigbossjeff@amazon.com", "077", "", "M", null, null),
                    new Education("12/12/1990", null, "Ceva", "College", 9.99), "Google", 1200);

            Recruiter r2 = new Recruiter(new Information("Recruiter", "2",
                    "bigbossjeff@amazon.com", "077", "", "M", null, null),
                    new Education("12/12/1990", null, "Ceva", "College", 9.99), "Google", 1200);

            Recruiter r3 = new Recruiter(new Information("Recruiter", "3",
                    "bigbossjeff@amazon.com", "077", "", "M", null, null),
                    new Education("12/12/1990", null, "Ceva", "College", 9.99), "Amazon", 1200);

            Recruiter r4 = new Recruiter(new Information("Recruiter", "4",
                    "bigbossjeff@amazon.com", "077", "", "M", null, null),
                    new Education("12/12/1990", null, "Ceva", "College", 9.99), "Amazon", 1200);


            Job j1 = new Job("SE Google", "Google", true, new Constraint(2002,
                    2020), new Constraint(2, 6), new Constraint(8, null), 1, 10000);

            Job j2 = new Job("SD Amazon", "Amazon", true, new Constraint(null,
                    null), new Constraint(0, 2), new Constraint(9, null), 1, 5000);

            Job j3 = new Job("SD Google", "Google", true, new Constraint(null,
                    null), new Constraint(0, 2), new Constraint(9, null), 1, 5000);

            Job j4 = new Job("SE Amazon", "Amazon", true, new Constraint(null,
                    null), new Constraint(0, 2), new Constraint(9, null), 1, 5000);

            Company amazon = new Company("Amazon", m1);
            amazon.add(DepartmentFactory.factory("IT"));
            amazon.add(DepartmentFactory.factory("Management"));
            amazon.add(DepartmentFactory.factory("Marketing"));
            amazon.add(DepartmentFactory.factory("Finance"));
            amazon.add(j2);
            amazon.add(j4);

            amazon.add(e1, amazon.getDepartments().get(0));
            amazon.add(e2, amazon.getDepartments().get(1));
            amazon.add(e3, amazon.getDepartments().get(2));
            amazon.add(e4, amazon.getDepartments().get(3));
            amazon.add(e5, amazon.getDepartments().get(2));

            Company google = new Company("Google", m2);
            google.add(DepartmentFactory.factory("IT"));
            google.add(DepartmentFactory.factory("Management"));
            google.add(DepartmentFactory.factory("Marketing"));
            google.add(DepartmentFactory.factory("Finance"));
            google.add(j1);
            google.add(j3);

            google.add(e6, google.getDepartments().get(0));
            google.add(e7, google.getDepartments().get(1));
            google.add(e8, google.getDepartments().get(2));
            google.add(e9, google.getDepartments().get(3));
            google.add(e10, google.getDepartments().get(2));


            Application app = Application.getInstance();
            app.add(amazon);
            app.add(google);
            app.add(u1);
            app.add(u2);
            app.add(u3);
            app.add(u4);

            u1.add(u2);
            u1.add(e3);

            u2.add(u1);
            u2.add(r1);
            u2.add(e7);

            u3.add(u4);
            u3.add(e3);

            u4.add(u3);
            u4.add(e10);

            e2.add(e10);
            e2.add(r3);

            e3.add(e6);
            e3.add(u3);

            e6.add(e3);
            e6.add(r4);

            e10.add(e2);
            e10.add(u4);

            e7.add(u2);

            r1.add(u2);

            r2.add(e3);

            r3.add(e2);

            r4.add(e6);

            j1. apply(u1);
            j1.apply(u2);
            j1.apply(u3);
            j1.apply(u4);

            j2.apply(u1);
            j2.apply(u2);
            j2.apply(u3);
            j2.apply(u4);

            j3.apply(u1);
            j3.apply(u2);
            j3.apply(u3);
            j3.apply(u4);

            j4.apply(u1);
            j4.apply(u2);
            j4.apply(u3);
            j4.apply(u4);

            j1.close();
            j2.close();
            j3.close();
            j4.close();

            System.out.println(app.getJobs(u1.companies));
        } catch (InvalidDatesException e1) {
            e1.printStackTrace();
            System.out.println("Data incorecta");
        } catch (ResumeIncompleteException e2) {
            e2.printStackTrace();
            System.out.println("Resume incomplet");
        }
    }
}
