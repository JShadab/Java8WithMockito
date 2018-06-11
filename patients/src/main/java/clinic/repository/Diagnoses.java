package clinic.repository;

import clinic.dto.Diagnosis;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Diagnoses {
    private final static List<Diagnosis> all;

    public static List<Diagnosis> getAll() {
        return all;
    }

    static {
        all = Stream.of(
                "Lost limb",
                "Kidney failure",
                "Lung cancer",
                "I'm the pirate"
        )
                .map(Diagnosis::new)
                .collect(Collectors.toList());
    }
}
