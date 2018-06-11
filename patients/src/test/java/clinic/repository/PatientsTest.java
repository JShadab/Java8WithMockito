package clinic.repository;

import clinic.dto.AnalysisResult;
import clinic.dto.Diagnosis;
import clinic.dto.Patient;
import org.junit.Test;

import java.util.List;
import java.util.Set;

import static org.junit.Assert.*;

public class PatientsTest {

    @Test
    public void getAll() {
        List<Patient> all = Patients.generatePatients(20);
        all.forEach(System.out::println);
        assertEquals(20, all.size());
    }

    @Test
    public void getDiagnoses() {
        Set<Diagnosis> diagnoses = Patients.getDiagnoses();
        assertEquals(2, diagnoses.size());
    }
    @Test
    public void getAnalyses() {
        List<AnalysisResult> analyses = Patients.getAnalyses();
        assertEquals(3, analyses.size());
    }
}