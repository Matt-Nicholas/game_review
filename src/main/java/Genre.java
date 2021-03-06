import java.util.List;
import org.sql2o.*;
import java.util.ArrayList;

public class Genre{

  private String name;
  private int id;

  public Genre(String name){
    this.name = name;

  }
  public int getId(){
    return id;
  }

  // public String getName(){
  //   return name;
  // }


  public static List<Genre> all() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM genre_table;";
      return con.createQuery(sql).executeAndFetch(Genre.class);
    }
  }
  public static Genre findG(int id) {
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

  public Integer findGenre() {
      if(this.findMatch(this.name)) {
        this.save();
        return this.id;
      } else {
        return this.findGenreGivenName();
     }
   }

  public List<Integer> findGameId() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT game_id FROM genre_game_link where genre_id=:id";
    return con.createQuery(sql)
        .addParameter("id", id)
        .executeAndFetch(Integer.class);
    }
  }
  public Integer findGenreGivenName() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT id FROM genre_table where name=:name";
      return con.createQuery(sql)
          .addParameter("name", this.name)
          .executeAndFetchFirst(Integer.class);
  }
  }
  public String getName(){
    try(Connection con = DB.sql2o.open()){
      String sql = "SELECT name FROM genre_table WHERE id=:id";
      return con.createQuery(sql)
        .addParameter("id", this.id)
        .executeAndFetchFirst(String.class);
    }
  }
}
