package fpexercice.students;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.*;

public class ExerciseTest {

    @InjectMocks
    Exercise instance;

    @Before
    public void initMocks() {
        instance = new Exercise();
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void getNationalitySet() {
        List<Student> students = getStudentsList();

        Set<String> nationalities = instance.getNationalitySet(students);

        assertEquals(3, nationalities.size());
        assertTrue(nationalities.contains("French"));
        assertTrue(nationalities.contains("English"));
        assertTrue(nationalities.contains("Deutche"));
    }

    @Test
    public void getYoungestFemale() {
        List<Student> students = getStudentsList();
        Student student = instance.getYoungestFemale(students);
        assertEquals("Alice", student.getFirstName());
        assertEquals("Doe", student.getLastName());
    }

    @Test
    public void getYoungestMale() {
        List<Student> students = getStudentsList();
        Student student = instance.getYoungestMale(students);
        assertEquals("Bob", student.getFirstName());
        assertEquals("Doe", student.getLastName());
    }

    @Test
    public void getOldestMale() {
        List<Student> students = getStudentsList();
        Student student = instance.getOldestMale(students);
        assertEquals("Winston", student.getFirstName());
        assertEquals("Churchill", student.getLastName());
    }

    @Test
    public void getStudentsWithSameFirstName() {
        List<Student> students = getStudentsList();

        List<List<Student>> studentsWithSameFirstName = instance.getStudentsWithSameFirstName(students);
        //Only one name is holded by more than one student. It is 'Alice'
        assertEquals(1, studentsWithSameFirstName.size());
        List<Student> allAlices = studentsWithSameFirstName.get(0);
        assertEquals(2, allAlices.size());
        assertTrue(
                allAlices.stream()
                        .allMatch(student -> student.getFirstName().equals("Alice"))
        );
        assertTrue(allAlices
                .stream()
                .map(Student::getLastName)
                .anyMatch("Smith"::equals));
        assertTrue(allAlices
                .stream()
                .map(Student::getLastName)
                .anyMatch("Doe"::equals));
    }

    private static List<Student> getStudentsList() {
        return Arrays.asList(
                new Student("Alice", "Smith", "Female", 17, "French"),
                new Student("Bob", "Doe", "Male", 18, "English"),
                new Student("Eve", "Jons", "Female", 19, "Deutche"),
                new Student("Alice", "Doe", "Female", 16, "French"),
                new Student("Winston", "Churchill", "Male", 55, "English")
        );
    }
}