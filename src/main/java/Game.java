import java.util.List;
import java.util.ArrayList;
import org.sql2o.*;

public class Game {
  private String title;
  private int id;
  private String summary;
  private int genre_id;
  private int system_id;
  // private int cost;

  public Game(String title) {
    this.title = title;

  }

  public String getTitle() {
    return title;
  }
  // public int getSystemId() {
  //   return system_id;
  // }
  // public int getGenreId() {
  //   return genre_id;
  // }
  public int getId() {
    return id;
  }

  public static List<Game> all() {
    String sql = "SELECT id, title FROM game_table";
    try(Connection con = DB.sql2o.open()) {
      return con.createQuery(sql).executeAndFetch(Game.class);
    }
  }



 public static Game find(int id) {
     try(Connection con = DB.sql2o.open()) {
       String sql = "SELECT * FROM game_table where id=:id";
       Game game = con.createQuery(sql)
         .addParameter("id", id)
         .executeAndFetchFirst(Game.class);
       return game;
     }
   }

 public List<Review> getReviews() {
   try(Connection con = DB.sql2o.open()) {
     String sql = "SELECT * FROM review_table where game_id=:id";
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
      String sql = "INSERT INTO game_table(title) VALUES (:title)";
      this.id = (int) con.createQuery(sql, true)
      .addParameter("title", this.title)
      .executeUpdate()
      .getKey();
    }
  }

  public void createSystemLink(int _id) {
    try(Connection con = DB.sql2o.open()) {
      String sql = "INSERT INTO system_game_link(system_id, game_id) VALUES (:system_id, :game_id)";
      con.createQuery(sql)
        .addParameter("system_id", _id)
        .addParameter("game_id", this.id)
        .executeUpdate();
    }
  }

  public void createGenreLink(int _id) {
    try(Connection con = DB.sql2o.open()) {
      String sql = "INSERT INTO genre_game_link(genre_id, game_id) VALUES (:genre_id, :game_id)";
      con.createQuery(sql)
        .addParameter("genre_id", _id)
        .addParameter("game_id", this.id)
        .executeUpdate();
    }
  }

  public List<Integer> getGameSystems() {
    try (Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM system_game_link WHERE game_id=:id";
    return con.createQuery(sql)
      .addParameter("id",this.id)
      .executeAndFetch(Integer.class);
    }
  }
  // public List<Game> findGames(List<Integer> _list) {
  //   List<Game> games = new List<Game>();
  //   for(int gameId : _list){
  //     games.add(Game.find(gameId));
  //   }
  //   return games;
  // }
}
