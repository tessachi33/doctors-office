import java.util.List;
import org.sql2o.*;

public class Patient {

  public String name;
  public int doctor_id;
  public String birthday;

  public String getName() {
    return name;
  }
  public int getDoctor_id() {
    return doctor_id;
  }
  public String getBirthday() {
    return birthday;
  }
  //constructor method, initializes an instance of Task
  public Patient(String name, int doctor_id, String birthday) {
    this.name = name;
    this.doctor_id = doctor_id;
    this.birthday = birthday;
  }
  //returns all instances of Patient objects in the list
  public static List<Patient> all() {
    String sql = "SELECT name, doctor_id, birthday FROM patients";
    try(Connection con = DB.sql2o.open()) {
      return con.createQuery(sql).executeAndFetch(Patient.class);
    }
  }
}
