package clinic.dto;

import java.util.List;
import java.util.Set;

public class Patient {

    private final String name;

    private final Set<Diagnosis> diagnoses;

    private final List<AnalysisResult> analyses;

    public Patient(String name, Set<Diagnosis> diagnoses, List<AnalysisResult> analyses) {
        this.name = name;
        this.diagnoses = diagnoses;
        this.analyses = analyses;
    }

    public String getName() {
        return name;
    }

    public Set<Diagnosis> getDiagnoses() {
        return diagnoses;
    }

    public List<AnalysisResult> getAnalyses() {
        return analyses;
    }

    @Override
    public String toString() {
        return "Patient{" +
                "name='" + name + '\'' +
                ", diagnoses=" + diagnoses +
                ", analyses=" + analyses +
                '}';
    }
}
