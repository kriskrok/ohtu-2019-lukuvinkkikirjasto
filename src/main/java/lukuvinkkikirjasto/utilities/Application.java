package lukuvinkkikirjasto.utilities;

import java.sql.Date;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;


import spark.ModelAndView;
import static spark.Spark.*;
import spark.template.velocity.VelocityTemplateEngine;
import lukuvinkkikirjasto.domain.*;
import lukuvinkkikirjasto.data_access.*;


public class Application {

    static String layout = "templates/layout.html";
    static Database db;
    static LukuvinkkiDao dao;

    public static void main(String[] args) throws Exception {
        
        // temporary arraylist for podcasts for testing
        ArrayList<Podcast> podcasts = new ArrayList<>();


        if (db == null) {
            db = new Database();
        }
        //staticFileLocation("/templates");
        
        port(findOutPort());
        
        if (dao == null) {
            setDao(new BookDao(db));
        }

        get("/", (request, response) -> {   //rooth path
            HashMap<String, String> model = new HashMap<>();
            model.put("template", "templates/index.html");
            return new ModelAndView(model, layout);
        }, new VelocityTemplateEngine());

        // browsing the reading tips
        // EDIT FOR CASE WHEN NO BOOKS BUT 1+ PODCASTS!

        get("/lukuvinkit", (request, response) -> {
            HashMap<String, Object> model = new HashMap<>();
            List<Book> books = dao.findAll();
            if (books.isEmpty()) {
                model.put("info", "Ei vielä lukuvinkkejä");
            }
            if (books.size() > 0) {
                model.put("books", books);
            } 
            if (podcasts.size() > 0) {
                model.put("podcasts", podcasts);
            } 
            model.put("template", "templates/lukuvinkit.html");
            model.put("person1", "Mahtijanis");
            return new ModelAndView(model, layout);
        }, new VelocityTemplateEngine());

        get("/lukuvinkit/poista/:id", (request, response) -> {
            HashMap<String, String> model = new HashMap<>();
            dao.delete(request.params(":id"));
            response.redirect("/lukuvinkit");
            return new ModelAndView(model, layout);
        }, new VelocityTemplateEngine());

        get("lukuvinkit/muokkaakirjaa/:id", (request, response) -> {
            HashMap<String, String> model = new HashMap<>();
         // Book book = dao.findbyId(id);
         // model.put("book", book);  
         // The next lines few lines provide an example of the autofill in works with the updating form. 
            model.put("PreviousTitle", "Tira-kirja");
            model.put("PreviousAuthor", "Laaksonen");
            model.put("PreviousUrl", "www.tirakirjaOnLit.com");
            model.put("template", "templates/updateBook.html");
            return new ModelAndView(model, layout);
        }, new VelocityTemplateEngine());

        get("lukuvinkit/muokkaapodcastia/:id", (request, response) -> {
            HashMap<String, String> model = new HashMap<>();
         // Book book = dao.findbyId(id);
         // model.put("podcast", podcast);  
         // The next lines few lines provide an example of the autofill in works with the updating form. 
            model.put("PreviousTitle", "Tira-podcast");
            model.put("PreviousCreator", "Algoritmikuningas");
            model.put("PreviousUrl", "www.tirautus.com");
            model.put("PreviousSeries", "Tirailut");
            model.put("template", "templates/updatePodcast.html");
            return new ModelAndView(model, layout);
        }, new VelocityTemplateEngine());

        get("lukuvinkit/muokkaa/:id", (request, response) -> {
            HashMap<String, String> model = new HashMap<>();

            String booktitle = request.queryParams("book-title");
            String author = request.queryParams("book-author");
            String descr = request.queryParams("book-description");
            String comment = request.queryParams("book-comment");
            String url = request.queryParams("book-url");
            String read = request.queryParams("book-date");
            
            if (!validateInput(booktitle, 3, 100)) {
                model.put("virhe", "Kirjan nimen tulee olla 3-100 merkkiä");
                model.put("template", "templates/updateBook.html");
                return new ModelAndView(model, layout);
            }

            if (author.length() != 0 && !validateInput(author, 3, 50)) {
                model.put("virhe", "Kirjailijan nimen tulee olla 3-50 merkkiä");
                model.put("template", "templates/updateBook.html");
                return new ModelAndView(model, layout);
            }
            
            if(!validateInput(descr, 0, 255)) {
                model.put("virhe", "Kuvauksen on oltava alle 255 merkkiä");
                model.put("template", "templates/updateBook.html");
                return new ModelAndView(model, layout);
            }
            
            if(!validateInput(comment, 0, 255)) {
                model.put("virhe", "Kommentin on oltava alle 255 merkkiä");
                model.put("template", "templates/updateBook.html");
                return new ModelAndView(model, layout);
            }
            model.put("template", "templates/updateBook.html");
            dao.update(request.params(":id"));
            return new ModelAndView(model, layout);
        }, new VelocityTemplateEngine());

        // Adding new reading tips:

        get("/tyyppi", (request, response) -> {
            HashMap<String, String> model = new HashMap<>();
            model.put("template", "templates/typeOfReadingTip.html");
            return new ModelAndView(model, layout);
        }, new VelocityTemplateEngine());

        post("/tyyppi", (request, response) -> {
            HashMap<String, String> model = new HashMap<>();
            String typeOfReadingTip = request.queryParams("typeOfReadingTip");
            
            if (typeOfReadingTip.equals("book")) {
                model.put("template", "templates/addNewBook.html");
                return new ModelAndView(model, layout);
                
            } else if (typeOfReadingTip.equals("podcast")) {
                model.put("template", "templates/addNewPodcast.html");
                return new ModelAndView(model, layout);
            } else {
                model.put("template", "templates/typeOfReadingTip.html");
                return new ModelAndView(model, layout);
            }
            
        }, new VelocityTemplateEngine());
        
        // Book

        get("/kirja", (request, response) -> {
            HashMap<String, String> model = new HashMap<>();
            model.put("template", "templates/addNewBook.html");
            return new ModelAndView(model, layout);
        }, new VelocityTemplateEngine());

        post("/kirja", (request, response) -> {
            HashMap<String, String> model = new HashMap<>();
            
            String booktitle = request.queryParams("book-title");
            String author = request.queryParams("book-author");
            String descr = request.queryParams("book-description");
            String comment = request.queryParams("book-comment");
            String url = request.queryParams("book-url");
            
            if (!validateInput(booktitle, 3, 100)) {
                model.put("virhe", "Kirjan nimen tulee olla 3-100 merkkiä");
                model.put("template", "templates/addNewBook.html");
                return new ModelAndView(model, layout);
            }

            if (author.length() != 0 && !validateInput(author, 3, 50)) {
                model.put("virhe", "Kirjailijan nimen tulee olla 3-50 merkkiä");
                model.put("template", "templates/addNewBook.html");
                return new ModelAndView(model, layout);
            }
            
            if(!validateInput(descr, 0, 255)) {
                model.put("virhe", "Kuvauksen on oltava alle 255 merkkiä");
                model.put("template", "templates/addNewBook");
                return new ModelAndView(model, layout);
            }
            
            if(!validateInput(comment, 0, 255)) {
                model.put("virhe", "Kommentin on oltava alle 255 merkkiä");
                model.put("template", "templates/addNewBook");
                return new ModelAndView(model, layout);
            }
            
            dao.insert(booktitle, author);
            
            model.put("vahvistus", booktitle + " tallennettu!");
            model.put("template", "templates/addNewBook.html");
            return new ModelAndView(model, layout);

        }, new VelocityTemplateEngine());

        // podcast

        get("/podcast", (request, response) -> {
            HashMap<String, String> model = new HashMap<>();
            model.put("template", "templates/addNewPodcast.html");
            return new ModelAndView(model, layout);
        }, new VelocityTemplateEngine());


        post("/podcast", (request, response) -> {
            HashMap<String, String> model = new HashMap<>();
            String episodeTitle = request.queryParams("podcast-title");
            String creator = request.queryParams("podcast-creator");
            String series = request.queryParams("podcast-series");
            String url = request.queryParams("podcast-url");
            String description = request.queryParams("podcast-description");
            
            if (!validateInput(episodeTitle, 3, 100)) {
                model.put("virhe", "Podcastin nimen tulee olla 3-100 merkkiä");
                model.put("template", "templates/addNewPodcast.html");
                return new ModelAndView(model, layout);
            }

            if (creator.length() != 0 && !validateInput(creator, 3, 50)) {
                model.put("virhe", "Tekijän nimen tulee olla 3-50 merkkiä");
                model.put("template", "templates/addNewPodcast.html");
                return new ModelAndView(model, layout);
            }

            if (series.length() != 0 && !validateInput(series, 3, 100)) {
                model.put("virhe", "Sarjan nimen tulee olla 3-100 merkkiä");
                model.put("template", "templates/addNewPodcast.html");
                return new ModelAndView(model, layout);
            }
            
          //  dao.insert(episodeTitle, series, creator, url, description);
            podcasts.add(new Podcast(episodeTitle, series, creator, url, description));

            model.put("vahvistus", episodeTitle + " tallennettu!");
            model.put("template", "templates/addNewPodcast.html");
            return new ModelAndView(model, layout);

        }, new VelocityTemplateEngine());
  
    }

    private static boolean validateInput(String input, int minimumLenght, int maximumLength) {
        if (input.length() < minimumLenght || input.length() > maximumLength) {
            return false;
        }
        return true;
    }

    public static void setDao(LukuvinkkiDao dao) {
        Application.dao = dao;
    }
       
    static int findOutPort() {
        if (portFromEnv != null) {
            return Integer.parseInt(portFromEnv);
        }

        return 4567;
    }

    //TODO: Googleta ProcessBuilder();
    static String portFromEnv = new ProcessBuilder().environment().get("PORT");

    static void setEnvPort(String port) {
        portFromEnv = port;
    }
}
