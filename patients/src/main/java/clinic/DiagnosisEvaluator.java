package clinic;

import clinic.dto.AnalysisResult;
import clinic.dto.Diagnosis;
import clinic.dto.Patient;
import clinic.util.Tuple;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import static clinic.util.Util.mapValues;

public class DiagnosisEvaluator {
    private StatisticsCalculator statisticsCalculator;
    private PatientSplitter patientSplitter;
    private StatisticsAnalyzer statisticsAnalyzer;

    public void setStatisticsCalculator(StatisticsCalculator statisticsCalculator) {
        this.statisticsCalculator = statisticsCalculator;
    }

    public void setPatientSplitter(PatientSplitter patientSplitter) {
        this.patientSplitter = patientSplitter;
    }

    public void setStatisticsAnalyzer(StatisticsAnalyzer statisticsAnalyzer) {
        this.statisticsAnalyzer = statisticsAnalyzer;
    }

    public List<AnalysisResult> evaluate(List<Patient> patients, Diagnosis diagnosis) {
        // Split patients depends on do they hava diagnosis
        Map<Boolean, List<Patient>> patientGroups = patientSplitter.splitPatients(patients, diagnosis);

        //Calculate statistics
        Map<Boolean, Map<AnalysisResult, Double>> stats = patientGroups.entrySet()
                .stream()
                .map(Tuple::of)
                .map(mapValues(
                        statisticsCalculator::collectStatistics
                )).collect(Tuple.toMapCollector());

        // Map contains percentage of patients having diagnosis also have analysis
        Map<AnalysisResult, Double> haveDiagnosis = stats.getOrDefault(true, Collections.emptyMap());
        // Map contains percentage of patients does not having diagnosis but have have analysis
        Map<AnalysisResult, Double> doNotHaveDiagnosis = stats.getOrDefault(false, Collections.emptyMap());

        //Analize statistics
        return statisticsAnalyzer.analyzeStatistics(haveDiagnosis, doNotHaveDiagnosis);


    }

}
