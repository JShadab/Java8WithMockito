package clinic;

import clinic.dto.Diagnosis;
import clinic.dto.Patient;

import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.groupingBy;

public class PatientSplitter {
    public Map<Boolean, List<Patient>> splitPatients(List<Patient> patients, Diagnosis diagnosis) {
        //We split patients into two group
        //who have this diagnosis and who don't
        return patients.stream()
                .collect(
                        groupingBy(patient ->
                                patient.getDiagnoses()
                                        .contains(diagnosis)));
    }
}
