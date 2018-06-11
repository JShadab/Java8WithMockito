package clinic;

import clinic.dto.AnalysisResult;
import clinic.dto.Diagnosis;
import clinic.dto.Patient;
import clinic.util.Tuple;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import static org.junit.Assert.assertSame;
import static org.mockito.Matchers.same;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class DiagnosisEvaluatorTest {
    @InjectMocks
    DiagnosisEvaluator instance;
    @Mock
    StatisticsCalculator statisticsCalculator;
    @Mock
    PatientSplitter patientSplitter;
    @Mock
    StatisticsAnalyzer statisticsAnalyzer;
    @Mock
    Diagnosis diagnosis;

    @Before
    public void setUp() {
        instance = new DiagnosisEvaluator();
        initMocks(this);
    }

    @Test
    public void evaluate() {
        List<Patient> patients = mock(List.class);
        List<Patient> patientsPositive = mock(List.class);
        List<Patient> patientsNegative = mock(List.class);
        Map<AnalysisResult, Double> positiveMap = mock(Map.class);
        Map<AnalysisResult, Double> negativeMap = mock(Map.class);
        List<AnalysisResult> analyzeResult = mock(List.class);
        when(patientSplitter.splitPatients(same(patients), same(diagnosis)))
                .thenReturn(
                        Stream.of(
                                Tuple.of(true, patientsPositive),
                                Tuple.of(false, patientsNegative)
                        ).collect(Tuple.toMapCollector()));
        when(statisticsCalculator.collectStatistics(same(patientsPositive)))
                .thenReturn(positiveMap);
        when(statisticsCalculator.collectStatistics(same(patientsNegative)))
                .thenReturn(
                        negativeMap);
        when(statisticsAnalyzer.analyzeStatistics(positiveMap, negativeMap))
                .thenReturn(analyzeResult);

        List<AnalysisResult> result = instance.evaluate(patients, diagnosis);

        assertSame(analyzeResult, result);
    }
}