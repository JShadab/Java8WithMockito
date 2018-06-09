package fpexercice.students;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class StudentTest {

    @InjectMocks
    Student instance;
    @Mock
    Teacher teacher;

    @Before
    public void initMocks() {
        instance = new Student("", "", "", 0,"");
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void doHomework() {
        when(teacher.getHomework()).thenReturn("new task");
        instance.doHomework(teacher);
        verify(teacher).rateHomework("new task!");
    }
}