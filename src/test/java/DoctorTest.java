import org.junit.*;
import static org.junit.Assert.*;
import java.util.Arrays;

public class DoctorTest {
  @Rule
  public DatabaseRule database = new DatabaseRule();

  @Test
  public void all_emptyAtFirst() {
    assertEquals(Doctor.all().size(), 0);
  }

  @Test
  public void equals_returnTrueIfNamesAreTheSame() {
    Doctor firstDoctor = new Doctor("Dr. Jack", 1);
    Doctor secondDoctor = new Doctor("Dr. Jack", 1);
    assertTrue(firstDoctor.equals(secondDoctor));
  }

  @Test
  public void save_savesIntoDatabase_true() {
    Doctor myDoctor = new Doctor("Dr.Jack", 1);
    myDoctor.save();
    assertTrue(Doctor.all().get(0).equals(myDoctor));
  }

  @Test
  public void find_findPatientInDatabase_true() {
    Doctor myDoctor = new Doctor("Jack", 1);
    myDoctor.save();
    Doctor savedDoctor = Doctor.find(myDoctor.getId());
    assertTrue(myDoctor.equals(savedDoctor));
  }
}
