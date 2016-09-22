import java.util.List;
import org.sql2o.*;

public class GameSystem{

  private String name;
  private int id;

  public GameSystem(String name){
    this.name = name;

  }
  public int getId(){
    return id;
  }

  // public String getName(){
  //   return name;
  // }


  public static List<GameSystem> all() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM system_table;";
      return con.createQuery(sql).executeAndFetch(GameSystem.class);
    }
  }
  public static GameSystem find(int id) {
      try(Connection con = DB.sql2o.open()) {
        String sql = "SELECT * FROM system_table WHERE id=:id";
        GameSystem name = con.createQuery(sql)
          .addParameter("id", id)
          .executeAndFetchFirst(GameSystem.class);
        return name;
      }
    }

  public void save(){
      try(Connection con = DB.sql2o.open()) {
        String sql = "INSERT INTO system_table(name) VALUES (:name)";
        this.id = (int) con.createQuery(sql, true)
          .addParameter("name", this.name)
          .executeUpdate()
          .getKey();
      }
  }

  public static boolean findMatch(String _test) {
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT name FROM system_table";
      List<String> allGameSystems = con.createQuery(sql)
        .executeAndFetch(String.class);
      if(allGameSystems.contains(_test)) {
        return false;
      }
      return true;
    }
  }

  public Integer findSystem() {
      if(this.findMatch(this.name)) {
        this.save();
        return this.id;
      } else {
        return this.findSystemGivenName();
      }
    }

  public List<Integer> findGameId(int id) {
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT game_id FROM system_game_link where system_id=:id";
      return con.createQuery(sql)
          .addParameter("id", id)
          .executeAndFetch(Integer.class);
    }
  }

  public Integer findSystemGivenName() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT id FROM system_table where name=:name";
      return con.createQuery(sql)
          .addParameter("name", this.name)
          .executeAndFetchFirst(Integer.class);
  }
  }

  public String getName(){
    try(Connection con = DB.sql2o.open()){
      String sql = "SELECT name FROM system_table WHERE id=:id";
      return con.createQuery(sql)
        .addParameter("id", this.id)
        .executeAndFetchFirst(String.class);
    }
  }
}
