package clinic.dto;

import java.util.Objects;

public class AnalysisResult {
    private final String name;
    private final boolean resultPositive;

    public AnalysisResult(String name, boolean resultPositive) {
        this.name = name;
        this.resultPositive = resultPositive;
    }

    public String getName() {
        return name;
    }

    public boolean isResultPositive() {
        return resultPositive;
    }

    @Override
    public String toString() {
        return "AnalysisResult{" +
                "name='" + name + '\'' +
                ", resultPositive=" + resultPositive +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AnalysisResult analysisResult = (AnalysisResult) o;
        return resultPositive == analysisResult.resultPositive &&
                Objects.equals(name, analysisResult.name);
    }

    @Override
    public int hashCode() {

        return Objects.hash(name, resultPositive);
    }
}
