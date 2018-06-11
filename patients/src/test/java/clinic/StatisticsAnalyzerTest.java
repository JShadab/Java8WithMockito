package clinic;

import clinic.dto.AnalysisResult;
import clinic.util.Tuple;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import static org.junit.Assert.assertEquals;
import static org.mockito.MockitoAnnotations.initMocks;

public class StatisticsAnalyzerTest {

    StatisticsAnalyzer instance;
    @Mock
    private AnalysisResult analysisResult1P;
    @Mock
    private AnalysisResult analysisResult1N;

    @Before
    public void setUp() {
        instance = new StatisticsAnalyzer();
        initMocks(this);
    }

    @Test
    public void analyzeStatistics() {
        Map<AnalysisResult, Double> haveDiagnosis = Stream.of(
                Tuple.of(analysisResult1P, .6),
                Tuple.of(analysisResult1N, .4)
        )
                .collect(Tuple.toMapCollector());
        Map<AnalysisResult, Double> doNotHaveDiagnosis = Stream.of(
                Tuple.of(analysisResult1P, .4),
                Tuple.of(analysisResult1N, .6)
        )
                .collect(Tuple.toMapCollector());
        List<AnalysisResult> analyses = instance.analyzeStatistics(haveDiagnosis, doNotHaveDiagnosis);

        assertEquals(1, analyses.size());
        assertEquals(analysisResult1P, analyses.get(0));
    }
}