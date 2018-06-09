package fpexercice.students;

public class Student {
    private final String firstName;
    private final String lastName;
    private final String gender;
    private final int age;
    private final String nationality;

    public Student(String firstName, String lastName, String gender, int age, String nationality) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.age = age;
        this.nationality = nationality;
    }

    void doHomework(Teacher teacher) {
        String homework = teacher.getHomework();
        teacher.rateHomework(homework+"!");
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getGender() {
        return gender;
    }

    public int getAge() {
        return age;
    }

    public String getNationality() {
        return nationality;
    }
}

