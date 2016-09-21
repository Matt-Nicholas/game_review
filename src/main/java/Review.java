import java.time.LocalDateTime;
import org.sql2o.*;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

public class Review {
  private int id;
  private int gameId;
  private String comment;
  private int rating;
  private LocalDateTime created_at;


  public Review(String comment, int gameId) {
    this.gameId = gameId;
    this.comment = comment;
    this.rating = 5;
    created_at = LocalDateTime.now();
  }

  public int getId() {
    return id;
  }

  public int getGameId() {
    return gameId;
  }

  public String getComment() {
    return comment;
  }

  public int getRating(){
    return rating;
  }

  public LocalDateTime getCreatedAt() {
    return created_at;
  }

  public static Review find(int id) {
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM reviews WHERE id=:id";
      Review review = con.createQuery(sql)
        .addParameter("id", id)
        .executeAndFetchFirst(Review.class);
      return review;
    }
  }

  public static List<Review> all() {
    String sql = "SELECT id, gameId, comment, rating, created_at FROM reviews";
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
      String sql = "INSERT INTO reviews (gameId, comment, rating, created_at) VALUES (:gameId, :comment, :rating, :created_at)";
      this.id = (int) con.createQuery(sql, true)
      .addParameter("gameId", this.gameId)
      .addParameter("comment", this.comment)
      .addParameter("rating", this.rating)
      .addParameter("created_at", this.created_at)
      .executeUpdate()
      .getKey();
    }
  }

  public void update(String comment) {
    try(Connection con = DB.sql2o.open()) {
      String sql = "UPDATE reviews SET comment = :comment WHERE id = :id";
      con.createQuery(sql)
        .addParameter("comment", comment)
        .addParameter("id", id)
        .executeUpdate();
      }
    }
  public void delete() {
    try(Connection con = DB.sql2o.open()) {
    String sql = "DELETE FROM reviews WHERE id = :id;";
    con.createQuery(sql)
      .addParameter("id", id)
      .executeUpdate();
    }
  }

}
