package fpexercice.students;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

public class Exercise {
    public Set<String> getNationalitySet(List<Student> students) {
        return students
                .stream()
                .map(Student::getNationality)
                .collect(Collectors.toSet());
    }

    public Student getYoungestFemale(List<Student> students) {
        return students
                .stream()
                .filter(s -> s.getGender().equals("Female"))
                .min(Comparator.comparing(Student::getAge))
                .orElseGet(() -> {
                    throw new RuntimeException("No female students");
                });
    }

    public Student getYoungestMale(List<Student> students) {
        return students
                .stream()
                .filter(s -> s.getGender().equals("Male"))
                .min(Comparator.comparing(Student::getAge))
                .orElseGet(() -> {
                    throw new RuntimeException("No male students");
                });
    }

    public Student getOldestMale(List<Student> students) {
        return students
                .stream()
                .filter(s -> s.getGender().equals("Male"))
                .max(Comparator.comparing(Student::getAge))
                .orElseGet(() -> {
                    throw new RuntimeException("No male students");
                });
    }

    public List<List<Student>> getStudentsWithSameFirstName(List<Student> students) {
        return students.stream()
                .collect(Collectors.groupingBy(Student::getFirstName))
                .entrySet()
                .stream()
                .filter(e -> e.getValue().size() > 1)
                .map(Map.Entry::getValue)
                .collect(toList());
    }
}
