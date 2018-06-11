package clinic;

import clinic.dto.AnalysisResult;
import clinic.util.Tuple;

import java.util.List;
import java.util.Map;

import static clinic.util.Util.mapValues;
import static java.util.stream.Collectors.toList;

public class StatisticsAnalyzer {
    public List<AnalysisResult> analyzeStatistics(Map<AnalysisResult, Double> haveDiagnosis, Map<AnalysisResult, Double> doNotHaveDiagnosis) {
        // return analyzes that more patients who have diagnosis than do not have
        return haveDiagnosis.entrySet()
                .stream()
                .map(Tuple::of)
                .map(t -> Tuple.of(
                        t.getV1(),
                        Tuple.of(t.getV2(),
                                doNotHaveDiagnosis.getOrDefault(t.getV1(), 0D))))
                .map(mapValues(t -> t.getV1() > t.getV2()))
                .filter(Tuple::getV2)
                .map(Tuple::getV1)
                .collect(toList());
    }
}
