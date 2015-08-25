import org.sql2o.*;

public class DB {
  public static Sql2o sql2o = new Sql20("jdbc:postgresql://localhost:5432/to_do", null, null);
}
