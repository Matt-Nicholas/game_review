import java.util.HashMap;
import java.util.Map;
import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;
import static spark.Spark.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;



public class App {
  public static void main(String[] args) {
    staticFileLocation("/public");
    String layout = "templates/layout.vtl";

    get("/", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      model.put("template", "templates/index.vtl");
      model.put("games", Game.all());
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    get("/game/new", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      model.put("template", "templates/new-game.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    get("/games/:game_id/reviews/:id", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      Game game = Game.find(Integer.parseInt(request.params(":game_id")));
      Review review = Review.find(Integer.parseInt(request.params(":id")));
      model.put("game", game);
      model.put("review", review);
      model.put("template", "templates/review.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    post("/games/:id/reviews/new", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      Integer game_id = Integer.parseInt(request.params(":id"));
      String title = request.queryParams("title");
      String comment = request.queryParams("comment");
      Review newReview = new Review(title, comment, game_id, 5);
      newReview.save();
      model.put("game", Game.find(game_id));
      String url = String.format("/games/%d", game_id);
      response.redirect(url);
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    post("/games/:game_id/reviews/:id", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      Review review = Review.find(Integer.parseInt(request.params(":id")));
      String title = request.queryParams("title");
      String comment = request.queryParams("comment");
      Game game = Game.find(review.getGameId());
      review.update(title, comment);
      String url = String.format("/games/%d", Integer.parseInt(request.params(":game_id")));
      response.redirect(url);
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());



    post("/games/:game_id/reviews/:id/delete", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      Review review = Review.find(Integer.parseInt(request.params(":id")));
      Integer game_id = Integer.parseInt(request.params(":game_id"));
      Game game = Game.find(review.getGameId());
      review.delete();
      model.put("game", game);
      model.put("template", "templates/game.vtl");
      String url = String.format("/games/%d", game_id);
      response.redirect(url);

      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());



    post("/games/new", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      String title = request.queryParams("title");
      String summary = request.queryParams("summary");
      Game game = new Game(title, summary);
      game.save();
      for (int i = 1;i < 4 ;i++) {
        String temp = request.queryParams("console" + i);
        GameSystem newSystem = new GameSystem(temp);
        if(!(temp == null)) {
          game.createSystemLink(newSystem.findSystem());
        }
      }
      for (int i = 1;i < 4 ;i++) {
        String temp = request.queryParams("genre" + i);
        Genre newGenre = new Genre(temp);

        if(!(temp == null)) {
          game.createGenreLink(newGenre.findGenre());
        }
      }

      model.put("game", game);
      // model.put("gameS", game);
      String url = String.format("/games/%d", game.getId());
      response.redirect(url);
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    get("/games/:id", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      Game game = Game.find(Integer.parseInt(request.params(":id")));
      model.put("game", game);
      model.put("GameSystem", GameSystem.class);
      model.put("Genre", Genre.class);
      model.put("template", "templates/game.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

  }


}
