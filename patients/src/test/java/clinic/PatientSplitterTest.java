package clinic;

import clinic.dto.Diagnosis;
import clinic.dto.Patient;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import java.util.*;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class PatientSplitterTest {

    PatientSplitter instance;
    @Mock
    Diagnosis diagnosis;

    @Before
    public void setUp() {
        instance = new PatientSplitter();
        initMocks(this);
    }

    @Test
    public void splitPatients() {
        Patient patient1 = mock(Patient.class);
        Patient patient2 = mock(Patient.class);
        when(patient1.getDiagnoses()).thenReturn(new HashSet<>(Collections.singletonList(diagnosis)));
        when(patient2.getDiagnoses()).thenReturn(Collections.emptySet());

        Map<Boolean, List<Patient>> result = instance.splitPatients(Arrays.asList(
                patient1,
                patient2),
                diagnosis);

        assertEquals(2, result.size());
        assertEquals(Collections.singletonList(patient1), result.get(true));
        assertEquals(Collections.singletonList(patient2), result.get(false));
    }
}