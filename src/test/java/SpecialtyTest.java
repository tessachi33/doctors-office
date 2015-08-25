import org.junit.*;
import static org.junit.Assert.*;
import java.util.Arrays;

public class SpecialtyTest {
  @Rule
  public DatabaseRule database = new DatabaseRule();

  @Test
  public void all_emptyAtFirst() {
    assertEquals(Specialty.all().size(), 0);
  }

  @Test
  public void equals_returnTrueIfSpecialtiesAreTheSame() {
    Specialty firstSpecialty = new Specialty("Eye");
    Specialty secondSpecialty = new Specialty("Eye");
    assertTrue(firstSpecialty.equals(secondSpecialty));
  }

  @Test
  public void save_savesIntoDatabase_true() {
    Specialty mySpecialty = new Specialty("Foot");
    mySpecialty.save();
    assertTrue(Specialty.all().get(0).equals(mySpecialty));
  }

  @Test
  public void find_findSpecialtyInDatabase_true() {
    Specialty mySpecialty = new Specialty("Dentist");
    mySpecialty.save();
    Specialty savedSpecialty = Specialty.find(mySpecialty.getId());
    assertTrue(mySpecialty.equals(mySpecialty));
  }
}
