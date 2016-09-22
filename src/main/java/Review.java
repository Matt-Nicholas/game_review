import java.time.LocalDateTime;
import org.sql2o.*;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

public class Review {
  private int id;
  private int game_id;
  private String title;
  private String comment;
  private int rating;
  private String created_at = "04/20/1969";


  public Review(String title, String comment, int game_id, int rating) {
    this.title = title;
    this.comment = comment;
    this.game_id = game_id;
    this.rating = rating;
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

  public String getTitle() {
    return title;
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
      String sql = "INSERT INTO review_table (game_id, comment, rating, created_at, title) VALUES (:game_id, :comment, :rating, :created_at, :title)";
      this.id = (int) con.createQuery(sql, true)
      .addParameter("game_id", this.game_id)
      .addParameter("comment", this.comment)
      .addParameter("rating", this.rating)
      .addParameter("created_at", this.created_at)
      .addParameter("title", this.title)
      .executeUpdate()
      .getKey();
    }
  }

  public void update(String title, String comment) {
    try(Connection con = DB.sql2o.open()) {
      String sql = "UPDATE review_table SET comment = :comment WHERE id = :id";
      String sql2 = "UPDATE review_table SET title = :title WHERE id = :id";
      con.createQuery(sql)
        .addParameter("comment", comment)
        .addParameter("id", id)
        .executeUpdate();

      con.createQuery(sql2)
        .addParameter("title", title)
        .addParameter("id", id)
        .executeUpdate();
      }
  }
  public void delete() {
    try(Connection con = DB.sql2o.open()) {
    String sql = "DELETE FROM review_table WHERE id = :id";
    con.createQuery(sql)
      .addParameter("id", id)
      .executeUpdate();
    }
  }

}
