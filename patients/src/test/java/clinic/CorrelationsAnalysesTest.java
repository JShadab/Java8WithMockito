package clinic;

import clinic.dto.AnalysisResult;
import clinic.dto.Diagnosis;
import clinic.dto.Patient;
import clinic.util.Tuple;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class CorrelationsAnalysesTest {

    @InjectMocks
    CorrelationsAnalyses instance;

    @Mock
    DiagnosisEvaluator evaluator;
    @Mock
    private Patient patient;
    @Mock
    private Diagnosis diagnosis;
    @Mock
    private AnalysisResult analysisResult;

    @Before
    public void setUp() {
        instance = new CorrelationsAnalyses();
        initMocks(this);
    }

    @Test
    public void correlationMatrix() {
        when(patient.getDiagnoses()).thenReturn(new HashSet<>(Collections.singletonList(diagnosis)));
        when(evaluator.evaluate(any(), any())).thenReturn(Collections.singletonList(analysisResult));

        List<Tuple<Diagnosis, List<AnalysisResult>>> result = instance.correlationMatrix(Collections.singletonList(patient));

        assertEquals(1, result.size());
        assertSame(1, result.get(0).getV2().size());
        assertSame(analysisResult, result.get(0).getV2().get(0));
        assertSame(diagnosis, result.get(0).getV1());
    }
}