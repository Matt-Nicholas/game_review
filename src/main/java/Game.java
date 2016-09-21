import java.util.List;
import java.util.ArrayList;
import org.sql2o.*;

public class Game {
  private String title;
  private int id;

  public Game(String title) {
    this.title = title;
  }

  public String getTitle() {
    return title;
  }

  public static List<Game> all() {
    String sql = "SELECT id, title FROM games";
    try(Connection con = DB.sql2o.open()) {
      return con.createQuery(sql).executeAndFetch(Game.class);
    }
  }

  public int getId() {
    return id;
  }

 public static Game find(int id) {
     try(Connection con = DB.sql2o.open()) {
       String sql = "SELECT * FROM games where id=:id";
       Game game = con.createQuery(sql)
         .addParameter("id", id)
         .executeAndFetchFirst(Game.class);
       return game;
     }
   }

 public List<Review> getReviews() {
   try(Connection con = DB.sql2o.open()) {
     String sql = "SELECT * FROM tasks where gameId=:id";
     return con.createQuery(sql)
       .addParameter("id", this.id)
       .executeAndFetch(Review.class);
   }
 }

 @Override
 public boolean equals(Object otherGame) {
   if (!(otherGame instanceof Game)) {
     return false;
   } else {
     Game newGame = (Game) otherGame;
     return this.getTitle().equals(newGame.getTitle()) &&
            this.getId() == newGame.getId();
   }
 }

  public void save() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "INSERT INTO games(title) VALUES (:title)";
      this.id = (int) con.createQuery(sql, true)
        .addParameter("title", this.title)
        .executeUpdate()
        .getKey();
    }
  }

}
