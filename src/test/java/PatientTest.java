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
}
