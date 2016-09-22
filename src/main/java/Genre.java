import java.util.List;
import org.sql2o.*;

public class Genre{

  private String name;
  private int id;

  public Genre(String name){
    this.name = name;

  }
  public int getId(){
    return id;
  }

  public String getName(){
    return name;
  }


  public static List<Genre> all() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM genre_table;";
      return con.createQuery(sql).executeAndFetch(Genre.class);
    }
  }
  public static Genre find(int id) {
      try(Connection con = DB.sql2o.open()) {
        String sql = "SELECT * FROM genre_table WHERE id=:id";
        Genre name = con.createQuery(sql)
          .addParameter("id", id)
          .executeAndFetchFirst(Genre.class);
        return name;
      }
    }

  public void save(){
      try(Connection con = DB.sql2o.open()) {
        String sql = "INSERT INTO genre_table(name) VALUES (:name)";
        this.id = (int) con.createQuery(sql, true)
          .addParameter("name", this.name)
          .executeUpdate()
          .getKey();
      }
  }

  public static boolean findMatch(String _test) {
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT name FROM genre_table";
      List<String> allGenres = con.createQuery(sql)
        .executeAndFetch(String.class);
      if(allGenres.contains(_test)) {
        return false;
      }
      return true;
    }
  }

  // public Integer findGenre() {
  //     if(this.findMatch(this.name)) {
  //       this.save();
  //       return this.id;
  //     } else {
  //       try(Connection con = DB.sql2o.open()) {
  //         String sql = "SELECT id FROM genre_table name=:name";
  //         return con.createQuery(sql)
  //             .addParameter("name", this.name)
  //             .executeAndFetch(Integer.class);
  //     }
  //    }
  //  }

  public List<Integer> findGames(int id) {
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM genre_game_link game_id=:id";
    return con.createQuery(sql)
        .addParameter("id", id)
        .executeAndFetch(Integer.class);
    }
  }
}
