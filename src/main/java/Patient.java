import java.util.List;
import org.sql2o.*;

public class Patient {

  private int id;
  private String name;
  private int doctor_id;
  private String birthday;

  public String getName() {
    return name;
  }
  public int getDoctor_id() {
    return doctor_id;
  }
  public String getBirthday() {
    return birthday;
  }
  public int getId() {
    return id;
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
  //saves a Patient into the db and saves the id of where the Patient was saved
  public void save() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "INSERT INTO patients (name, birthday, doctor_id) VALUES (:name, :birthday, :doctor_id)";
      this.id = (int) con.createQuery(sql, true)
      .addParameter("name", this.name)
      .addParameter("birthday", this.birthday)
      .addParameter("doctor_id", this.doctor_id)
      .executeUpdate()
      .getKey();
    }
  }
  //finds a Patient object in the fb using the id, returns the Patient object
  public static Patient find(int id) {
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM patients WHERE id=:id";
      Patient patient = con.createQuery(sql)
        .addParameter("id", id)
        .executeAndFetchFirst(Patient.class);
        return patient;
    }
  }


  public void updateDoctor_id(int doctor_id) {
    try(Connection con = DB.sql2o.open()) {
      String sql = "UPDATE patients SET doctor_id = :doctor_id WHERE id = :id";
      con.createQuery(sql)
        .addParameter("doctor_id", doctor_id)
        .addParameter("id", id)
        .executeUpdate();
      }
    }
  public void updateName(String name) {
    try(Connection con = DB.sql2o.open()) {
      String sql = "UPDATE patients SET name = :name WHERE id = :id";
      con.createQuery(sql)
        .addParameter("name", name)
        .addParameter("id", id)
        .executeUpdate();
      }
    }
  public void updateBirthday(String name) {
    try(Connection con = DB.sql2o.open()) {
      String sql = "UPDATE patients SET birthday = :birthday WHERE id = :id";
      con.createQuery(sql)
        .addParameter("birthday", birthday)
        .addParameter("id", id)
        .executeUpdate();
        }
    }
  public void delete() {
    try(Connection con = DB.sql2o.open()) {
    String sql = "DELETE FROM tasks WHERE id = :id;";
    con.createQuery(sql)
      .addParameter("id", id)
      .executeUpdate();
  }
}
}
