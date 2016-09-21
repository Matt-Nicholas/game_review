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

    get("/games/:game_id/reviews/:id", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      Game game = Game.find(Integer.parseInt(request.params(":game_id")));
      Review review = Review.find(Integer.parseInt(request.params(":id")));
      model.put("game", game);
      model.put("review", review);
      model.put("template", "templates/review.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    post("/games/:game_id/reviews/:id", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      Review review = Review.find(Integer.parseInt(request.params("id")));
      String comment = request.queryParams("comment");
      Game game = Game.find(review.getGameId());
      review.update(comment);
      String url = String.format("/games/%d/reviews/%d", game.getId(), review.getId());
      response.redirect(url);
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    post("/games/:game_id/reviews/:id/delete", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      Review review = Review.find(Integer.parseInt(request.params("id")));
      Game game = Game.find(review.getGameId());
      review.delete();
      model.put("game", game);
      model.put("template", "templates/game.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    post("/reviews/new", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      Game game = Game.find(Integer.parseInt(request.queryParams("gameId")));
      String comment = request.queryParams("comment");
      Review newReview = new Review(comment, game.getId());
      newReview.save();
      model.put("game", game);
      String url = String.format("/games/%d", game.getId());
      response.redirect(url);
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    post("/games/new", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      String title = request.queryParams("title");
      Game game = new Game(title);
      game.save();
      for (int i = 1;i < 4 ;i++ ) {
        String temp = request.queryParams("console" + i);
        GameSystem newSystem = new GameSystem(temp);
        if(!(temp == null)) {
          game.createSystemLink(newSystem.findSystem());
        }
      }
      for (int i = 1;i < 4 ;i++ ) {
        String temp = request.queryParams("genre" + i);
        Genre newGenre = new Genre(temp);

        if(!(temp == null)) {
          game.createGenreLink(newGenre.findGenre());
        }
      }



      // if(!(request.queryParams("xbox") == null)){
      //   if ((GameSystem.findMatch("Xbox One"))) {
      //     GameSystem newBox = new GameSystem("Xbox One");
      //     newBox.save();
      //   }
      // }
      // if(!(request.queryParams("ps4") == null)){
      //   if ((GameSystem.findMatch("PS4"))) {
      //     GameSystem newBox = new GameSystem("PS4");
      //     newBox.save();
      //   }
      // }
      // if(!(request.queryParams("pc") == null)){
      //   if ((GameSystem.findMatch("PC"))) {
      //     GameSystem newBox = new GameSystem("PC");
      //     newBox.save();
      //   }
      // }

      // for (int i = 1;i < 4 ;i++ ) {
      //   String temp = request.queryParams("genre"+i);
      //   try {
      //
      //     System.out.println(temp);
      //   } catch (Exception ex) {
      //     System.out.println("catch hit");
      //   }
      // }
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
      model.put("template", "templates/game.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

  }


}
