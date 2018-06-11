package clinic.dto;

import java.util.Objects;

public class Diagnosis {
    private final String diseaseName;

    public Diagnosis(String diseaseName) {
        this.diseaseName = diseaseName;
    }

    public String getDiseaseName() {
        return diseaseName;
    }

    @Override
    public String toString() {
        return "Diagnosis{" +
                "diseaseName='" + diseaseName + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Diagnosis diagnosis = (Diagnosis) o;
        return Objects.equals(diseaseName, diagnosis.diseaseName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(diseaseName);
    }
}
