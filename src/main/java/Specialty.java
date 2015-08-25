import java.util.List;
import org.sql2o.*;

public class Specialty {

     private int id;
     private String name;

     public String getName() {
     return name;
     }
     public int getId() {
     return id;
     }
   //constructor method, initializes an instance of Doctor
   public Specialty(String name) {
    this.name = name;
  }
  // returns all instances of Specialty objects in the list
    public static List<Specialty> all() {
     String sql = "SELECT name FROM specialties";
      try(Connection con = DB.sql2o.open()) {
       return con.createQuery(sql).executeAndFetch(Specialty.class);
    }
  }
  //Overides equals method to check if two objects are equal
  @Override
  public boolean equals(Object otherSpecialty) {
    if(!(otherSpecialty instanceof Specialty)) {
      return false;
    } else {
      Specialty newSpecialty = (Specialty) otherSpecialty;
      return this.getName().equals(newSpecialty.getName());
    }
  }
  //saves a specialty into the db and saves the id of where the specialty was saved
  public void save() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "INSERT INTO specialties (name) VALUES (:name)";
      this.id = (int) con.createQuery(sql, true)
      .addParameter("name", this.name)
      .executeUpdate()
      .getKey();
    }
  }
  //finds a specialty object in the fb using the id, returns the specialty object
  public static Specialty find(int id) {
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM specialties WHERE id=:id";
      Specialty specialty = con.createQuery(sql)
        .addParameter("id", id)
        .executeAndFetchFirst(Specialty.class);
        return specialty;
    }
  }
}
