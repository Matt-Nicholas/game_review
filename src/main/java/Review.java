import java.time.LocalDateTime;
import org.sql2o.*;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

public class Review {
  private int id;
  private int game_id;
  private String comment;
  private int rating;
  private String created_at = "04/20/1969";


  public Review(String comment, int gameId) {
    this.game_id = gameId;
    this.comment = comment;
    // this.rating = 5;
    // LocalDate date = LocalDate.now();
    // created_at = date.format(formatter);

  }

  public int getId() {
    return id;
  }

  public int getGameId() {
    return game_id;
  }

  public String getComment() {
    return comment;
  }

  public int getRating(){
    return rating;
  }

  public String getCreatedAt() {
    return created_at;
  }

  public static Review find(int id) {
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM review_table WHERE id=:id";
      Review review = con.createQuery(sql)
        .addParameter("id", id)
        .executeAndFetchFirst(Review.class);
      return review;
    }
  }

  public static List<Review> all() {
    String sql = "SELECT id, gameId, comment, rating, created_at FROM review_table";
    try(Connection con = DB.sql2o.open()) {
      return con.createQuery(sql).executeAndFetch(Review.class);
    }
  }

  @Override
  public boolean equals(Object otherReview) {
    if (!(otherReview instanceof Review)) {
      return false;
    } else {
      Review newReview = (Review) otherReview;
      return this.getComment().equals(newReview.getComment()) && this.getId() == newReview.getId() && this.getGameId() == newReview.getGameId();
    }
  }

  public void save() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "INSERT INTO review_table (game_id, comment, rating, created_at) VALUES (:game_id, :comment, :rating, :created_at)";
      this.id = (int) con.createQuery(sql, true)
      .addParameter("game_id", this.game_id)
      .addParameter("comment", this.comment)
      .addParameter("rating", this.rating)
      .addParameter("created_at", this.created_at)
      .executeUpdate()
      .getKey();
    }
  }

  public void update(String comment) {
    try(Connection con = DB.sql2o.open()) {
      String sql = "UPDATE review_table SET comment = :comment WHERE id = :id";
      con.createQuery(sql)
        .addParameter("comment", comment)
        .addParameter("id", id)
        .executeUpdate();
      }
    }
  public void delete() {
    try(Connection con = DB.sql2o.open()) {
    String sql = "DELETE FROM review_table WHERE id = :id;";
    con.createQuery(sql)
      .addParameter("id", id)
      .executeUpdate();
    }
  }

}
