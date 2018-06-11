package clinic;

import clinic.dto.AnalysisResult;
import clinic.dto.Patient;
import clinic.util.Tuple;
import clinic.util.Util;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static clinic.util.Util.mapValues;
import static java.util.stream.Collectors.groupingBy;

public class StatisticsCalculator {
    Map<AnalysisResult, Double> collectStatistics(List<Patient> patients) {
        // Calculate percentage of patients who have said analysis result to total
        // patients who perform this medical check
        return patients.stream()
                .flatMap(patient -> patient.getAnalyses().stream())
                .collect(groupingBy(AnalysisResult::getName, Collectors.toList()))
                .entrySet()
                .stream()
                .map(Tuple::of)
                .map(mapValues(this::collect))
                .map(Tuple::promoteOption2)
                .flatMap(Util::optionToStream)
                .flatMap(x ->
                        Stream.of(
                                Tuple.of(
                                        new AnalysisResult(x.getV1(), true),
                                        x.getV2().positivePercents()),
                                Tuple.of(
                                        new AnalysisResult(x.getV1(), false),
                                        x.getV2().negativePercents())
                        )
                )
                .collect(Tuple.toMapCollector());
    }

    private Optional<Percentage> collect(List<AnalysisResult> analyzes) {
        return analyzes.stream()
                .map(AnalysisResult::isResultPositive)
                .map(Percentage::new)
                .reduce(Percentage::add);
    }

    private static class Percentage {
        private final int positiveCount;
        private final int negativeCount;

        public Percentage(boolean isPositive) {
            if (isPositive) {
                positiveCount = 1;
                negativeCount = 0;
            } else {
                positiveCount = 0;
                negativeCount = 1;
            }
        }

        private Percentage(int positiveCount, int negativeCount) {
            this.positiveCount = positiveCount;
            this.negativeCount = negativeCount;
        }

        public double positivePercents() {
            return ((double) positiveCount) / (positiveCount + negativeCount);
        }

        public double negativePercents() {
            return ((double) negativeCount) / (positiveCount + negativeCount);
        }

        public Percentage add(Percentage other) {
            return new Percentage(this.positiveCount + other.positiveCount,
                    this.negativeCount + other.negativeCount);
        }
    }

}
