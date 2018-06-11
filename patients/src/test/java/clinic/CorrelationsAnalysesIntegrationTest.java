package clinic;

import clinic.dto.AnalysisResult;
import clinic.dto.Diagnosis;
import clinic.repository.Patients;
import clinic.util.Tuple;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertNotNull;

public class CorrelationsAnalysesIntegrationTest {

    CorrelationsAnalyses instance;

    @Before
    public void setUp() {
        instance = new CorrelationsAnalyses();
        DiagnosisEvaluator evaluator = new DiagnosisEvaluator();
        instance.setEvaluator(evaluator);
        evaluator.setStatisticsCalculator(new StatisticsCalculator());
        evaluator.setPatientSplitter(new PatientSplitter());
        evaluator.setStatisticsAnalyzer(new StatisticsAnalyzer());
    }

    @Test
    public void correlationMatrix() {
        List<Tuple<Diagnosis, List<AnalysisResult>>> list = instance.correlationMatrix(
                Patients.generatePatients(20)
        );
        assertNotNull(list);
    }
}