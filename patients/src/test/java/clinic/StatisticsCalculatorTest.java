package clinic;

import clinic.dto.AnalysisResult;
import clinic.dto.Patient;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.Map;

import static org.junit.Assert.*;

public class StatisticsCalculatorTest {
    StatisticsCalculator instance;

    @Before
    public void setUp() {
        instance = new StatisticsCalculator();
    }

    @Test
    public void collectStatistics() {
        Map<AnalysisResult, Double> result = instance.collectStatistics(
                Collections.singletonList(new Patient(null,
                        null,
                        Arrays.asList(
                                new AnalysisResult("a", true),
                                new AnalysisResult("a", true),
                                new AnalysisResult("a", true),
                                new AnalysisResult("a", false)
                        )
                ))
        );

        assertEquals(2, result.size());
        assertEquals(0.75, result.get(new AnalysisResult("a", true)), .001);
        assertEquals(0.25, result.get(new AnalysisResult("a", false)), .001);
    }
}