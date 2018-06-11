package clinic.repository;

import clinic.dto.AnalysisResult;
import clinic.dto.Diagnosis;
import clinic.dto.Patient;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Patients {
    public static List<Patient> generatePatients(int size) {
        return allIntegers()
                .mapToObj(i -> generateName())
                .distinct()
                .map(patientName ->
                        {
                            Set<Diagnosis> diseases = getDiagnoses();
                            List<AnalysisResult> analise = getAnalyses();
                            return new Patient(
                                    patientName,
                                    diseases,
                                    analise);
                        }
                )
                .limit(size)
                .collect(Collectors.toList());
    }

    public static List<AnalysisResult> getAnalyses() {
        return allIntegers()
                .mapToObj(i -> getRandom(Analyzes.getDiseaseNames()))
                .distinct()
                .map(name -> new AnalysisResult(name, getResult()))
                .limit(3)
                .collect(Collectors.toList());
    }

    public static Set<Diagnosis> getDiagnoses() {
        return allIntegers()
                .mapToObj(i -> getRandom(Diagnoses.getAll()))
                .distinct()
                .limit(2)
                .collect(Collectors.toSet());
    }

    private static IntStream allIntegers() {
        return IntStream.iterate(0, x -> x + 1);
    }

    private static String generateName() {
        String firstName = getRandom(NameSingleton.getFirstNames());
        String lastName = getRandom(NameSingleton.getLastNames());
        return firstName + " " + lastName;
    }

    private static <T> T getRandom(List<T> strings) {
        return strings.get(getRandom().nextInt(strings.size()));
    }

    private static boolean getResult() {
        return getRandom().nextBoolean();
    }


    public static Random getRandom() {
        return Singleton.random;
    }

    private static class Singleton {
        static final Random random = new Random();

    }

    private static class NameSingleton {
        private static final List<String> names = Arrays.asList(
                "Marilyn Monroe",
                "Abraham Lincoln",
                "John Kennedy",
                "Martin Luther",
                "Nelson Mandela",
                "Queen Elizabeth",
                "Winston Churchill",
                "Donald Trump",
                "Bill Gates",
                "Muhammad Ali",
                "Mahatma Gandhi",
                "Margaret Thatcher",
                "Christopher Columbus",
                "Charles Darwin",
                "Elvis Presley",
                "Albert Einstein",
                "Paul McCartney",
                "Queen Victoria",
                "Pope Francis"
        );
        private static final List<String> firstNames = names
                .stream()
                .map(x -> x.split(" "))
                .map(x -> x[0])
                .collect(Collectors.toList());
        private static final List<String> lastNames = names
                .stream()
                .map(x -> x.split(" "))
                .map(x -> x[1])
                .collect(Collectors.toList());


        public static List<String> getFirstNames() {
            return firstNames;
        }

        public static List<String> getLastNames() {
            return lastNames;
        }
    }
}
