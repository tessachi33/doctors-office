import org.junit.*;
import static org.junit.Assert.*;
import java.util.Arrays;

public class PatientTest {
  @Rule
  public DatabaseRule database = new DatabaseRule();

  @Test
  public void all_emptyAtFirst() {
    assertEquals(Patient.all().size(), 0);
  }

  @Test
  public void equals_returnTrueIfNamesAreTheSame() {
    Patient firstPatient = new Patient("Jack", "feb", 1);
    Patient secondPatient = new Patient("Jack", "feb", 1);
    assertTrue(firstPatient.equals(secondPatient));
  }

  @Test
  public void save_savesIntoDatabase_true() {
    Patient myPatient = new Patient("Jack", "feb", 1);
    myPatient.save();
    assertTrue(Patient.all().get(0).equals(myPatient));
  }

  @Test
  public void find_findPatientInDatabase_true() {
    Patient myPatient = new Patient("Jack", "feb", 1);
    myPatient.save();
    Patient savedPatient = Patient.find(myPatient.getId());
    assertTrue(myPatient.equals(savedPatient));
  }
}
