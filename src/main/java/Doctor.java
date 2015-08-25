import java.util.List;
import org.sql2o.*;

public class Doctor {

  private int id;
  private String name;
  private int specialty_id;

  public String getName() {
    return name;
  }
  public int getSpecialty_id() {
    return specialty_id;
  }
  public int getId() {
    return id;
  }
  //constructor method, initializes an instance of Doctor
  public Doctor(String name, int specialty_id) {
    this.name = name;
    this.specialty_id = specialty_id;
  }
  //returns all instances of Doctor objects in the list
  public static List<Doctor> all() {
    String sql = "SELECT name, specialty_id FROM doctors";
    try(Connection con = DB.sql2o.open()) {
      return con.createQuery(sql).executeAndFetch(Doctor.class);
    }
  }
  //Overides equals method to check if two objects are equal
  @Override
  public boolean equals(Object otherDoctor){
    if(!(otherDoctor instanceof Doctor)) {
      return false;
    } else {
      Doctor newDoctor = (Doctor) otherDoctor;
      return this.getName().equals(newDoctor.getName()) &&
        this.getSpecialty_id() == newDoctor.getSpecialty_id();
    }
  }
  //saves a Doctor into the db and saves the id of where the Doctor was saved
  public void save() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "INSERT INTO doctors (name, specialty_id) VALUES (:name, :specialty_id)";
      this.id = (int) con.createQuery(sql, true)
      .addParameter("name", this.name)
      .addParameter("specialty_id", this.specialty_id)
      .executeUpdate()
      .getKey();
    }
  }
  //finds a Doctor object in the fb using the id, returns the Doctor object
  public static Doctor find(int id) {
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM doctors WHERE id=:id";
      Doctor Doctor = con.createQuery(sql)
        .addParameter("id", id)
        .executeAndFetchFirst(Doctor.class);
        return Doctor;
    }
  }
}
