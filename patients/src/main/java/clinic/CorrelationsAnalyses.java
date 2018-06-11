package clinic;

import clinic.dto.AnalysisResult;
import clinic.dto.Diagnosis;
import clinic.dto.Patient;
import clinic.util.Tuple;

import java.util.Collection;
import java.util.List;

import static clinic.util.Util.mapValues;
import static java.util.stream.Collectors.toList;

public class CorrelationsAnalyses {

    private DiagnosisEvaluator evaluator;

    public void setEvaluator(DiagnosisEvaluator evaluator) {
        this.evaluator = evaluator;
    }

    public List<Tuple<Diagnosis, List<AnalysisResult>>> correlationMatrix(List<Patient> patients) {
        //Check each diagnosis we have
        return patients.stream()
                .map(Patient::getDiagnoses)
                .flatMap(Collection::stream)
                .map(Tuple::dup)
                .map(mapValues(diagnose -> evaluator.evaluate(patients, diagnose)))
                .collect(toList());
    }


}
