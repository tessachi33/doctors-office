import java.util.List;
import org.sql2o.*;

public class Patient {

  public String name;
  public int doctor_id;
  public String birthday;

  public getName() {
    return name;
  }
  public getDoctor_id() {
    return doctor_id;
  }
  public getBirthday() {
    return birthday;
  }
  //constructor method, initializes an instance of Task
  public Patient(String name, int doctor_id, String birthday) {
    this.name = name;
    this.doctor_id = doctor_id;
    this.birthday = birthday;
  }
  
