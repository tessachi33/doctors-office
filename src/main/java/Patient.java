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
  public Patient(String name, String birthday, int doctor_id) {
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
  //Overides equals method to check if two objects are equal
  @Override
  public boolean equals(Object otherPatient){
    if(!(otherPatient instanceof Patient)) {
      return false;
    } else {
      Patient newPatient = (Patient) otherPatient;
      return this.getName().equals(newPatient.getName()) &&
        this.getBirthday().equals(newPatient.getBirthday()) &&
        this.getDoctor_id() == newPatient.getDoctor_id();

    }
  }
}
