package clinic.repository;

import java.util.Arrays;
import java.util.List;

public class Analyzes {
    private static final List<String> diseaseNames;

    public static List<String> getDiseaseNames() {
        return diseaseNames;
    }

    static {
        diseaseNames = Arrays.asList(
                "High blood pressure",
                "Blood leakage",
                "Have a parrot",
                "Smoking pipe",
                "Blood in urine",
                "Some other #1",
                "Some other #2",
                "Some other #3"
        );
    }
}
