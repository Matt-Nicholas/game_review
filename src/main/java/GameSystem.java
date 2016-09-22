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

  public String getName(){
    return name;
  }


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

  // public Integer findSystem() {
  //     if(this.findMatch(this.name)) {
  //       this.save();
  //       return this.id;
  //     } else {
  //       try(Connection con = DB.sql2o.open()) {
  //         String sql = "SELECT id FROM system_table name=:name";
  //         return con.createQuery(sql)
  //             .addParameter("name", this.name)
  //             .executeAndFetch(Integer.class);
  //     }
  //     }
  //   }

  // public List<Integer> findGames(int id) {
  //   try(Connection con = DB.sql2o.open()) {
  //     String sql = "SELECT system_id FROM system_game_link game_id=:id";
  //     return con.createQuery(sql)
  //         .addParameter("id", id)
  //         .executeAndFetch();
  //   }
  // }
}
