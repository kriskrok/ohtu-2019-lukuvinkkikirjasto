package lukuvinkkikirjasto.utilities;

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
    static BookDao bookDao;
    static PodcastDao podcastDao;

    public static void main(String[] args) throws Exception {
        
        // temporary arraylist for podcasts for testing
        //ArrayList<Podcast> podcasts = new ArrayList<>();


        if (db == null) {
            db = new Database();
        }
        //staticFileLocation("/templates");
        
        port(findOutPort());
        
        if (bookDao == null) {
            bookDao = new BookDao(db);
        }

        if (podcastDao == null) {
            podcastDao = new PodcastDao(db);
        }

        get("/", (request, response) -> {   //rooth path
            HashMap<String, String> model = new HashMap<>();
            model.put("template", "templates/index.html");
            return new ModelAndView(model, layout);
        }, new VelocityTemplateEngine());

        // browsing the reading tips


        get("/lukuvinkit", (request, response) -> {
            HashMap<String, Object> model = new HashMap<>();
            List<Lukuvinkki> books = bookDao.findAll();
            List<Lukuvinkki> podcasts = podcastDao.findAll();
            if (books.isEmpty() && podcasts.isEmpty()) {
                model.put("info", "Ei vielä lukuvinkkejä");
            }
            if (books.size() > 0) {
                model.put("books", books);
            } 
            if (podcasts.size() > 0) {
                model.put("podcasts", podcasts);
            } 
            model.put("template", "templates/lukuvinkit.html");
            return new ModelAndView(model, layout);
        }, new VelocityTemplateEngine());

        get("/lukuvinkit/poista/:id", (request, response) -> {
            HashMap<String, String> model = new HashMap<>();
            bookDao.delete(request.params(":id"));
            response.redirect("/lukuvinkit");
            return new ModelAndView(model, layout);
        }, new VelocityTemplateEngine());

        get("/lukuvinkit/poistaPod/:id", (request, response) -> {
            HashMap<String, String> model = new HashMap<>();
            podcastDao.delete(request.params(":id"));
            response.redirect("/lukuvinkit");
            return new ModelAndView(model, layout);
        }, new VelocityTemplateEngine());

        get("lukuvinkit/muokkaakirjaa/:id", (request, response) -> {
            HashMap<String, String> model = new HashMap<>();
            Book book = bookDao.findById(request.params(":id"));
            model.put("PreviousTitle", book.title);
            model.put("PreviousAuthor", book.author);
            model.put("PreviousUrl", book.url);
            model.put("PreviousDescription", book.description);
            model.put("PreviousComment", book.comment);
            model.put("template", "templates/updateBook.html");
         // model.put("book", book);
            return new ModelAndView(model, layout);
        }, new VelocityTemplateEngine());

        post("lukuvinkit/muokkaakirjaa/:id", (request, response) -> {
            HashMap<String, String> model = new HashMap<>();
            Book book = bookDao.findById(request.params(":id"));


            String booktitle = request.queryParams("book-title");
            String author = request.queryParams("book-author");

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

            book.title = request.queryParams("book-title");
            book.author = request.queryParams("book-author");
            book.description = request.queryParams("book-description");
            book.comment = request.queryParams("book-comment");
            book.url = request.queryParams("book-url");
            String read = request.queryParams("book-date");

            bookDao.update(book);
            model.put("vahvistus", booktitle + " tallennettu!");
            model.put("template", "templates/addNewPodcast.html");
            return new ModelAndView(model, layout);


        }, new VelocityTemplateEngine());

        get("lukuvinkit/muokkaapodcastia/:id", (request, response) -> {
            HashMap<String, String> model = new HashMap<>();
            Podcast podcast = podcastDao.findById(request.params(":id"));

         // model.put("podcast", podcast);  
         // The next lines few lines provide an example of the autofill in works with the updating form. 
            model.put("PreviousTitle", podcast.title);
            model.put("PreviousCreator", podcast.creator);
            model.put("PreviousUrl", podcast.url);
            model.put("PreviousSeries", podcast.series);
            model.put("template", "templates/updatePodcast.html");
            return new ModelAndView(model, layout);
        }, new VelocityTemplateEngine());

        post("lukuvinkit/muokkaapodcastia/:id", (request, response) -> {
            HashMap<String, String> model = new HashMap<>();
            System.out.println(request.params(":id"));
            Podcast podcast = podcastDao.findById(request.params(":id"));

            String episodeTitle = request.queryParams("podcast-title");

            if (!validateInput(episodeTitle, 3, 100)) {
                model.put("virhe", "Podcastin nimen tulee olla 3-100 merkkiä");
                model.put("template", "templates/addNewPodcast.html");
                return new ModelAndView(model, layout);
            }

            podcast.title = request.queryParams("podcast-title");
            podcast.creator = request.queryParams("podcast-creator");
            podcast.url = request.queryParams("podcast-url");
            podcast.series = request.queryParams("podcast-series");
            podcast.description = request.queryParams("podcast-description");


            podcastDao.update(podcast);
            model.put("vahvistus", episodeTitle + " tallennettu!");
            model.put("template", "templates/addNewPodcast.html");
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
            bookDao.update(request.params(":id"));
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
            
            bookDao.insert(booktitle, author);
            
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
            podcastDao.insert(episodeTitle, series, creator, url, description );
            //podcasts.add(new Podcast(episodeTitle, series, creator, url, description));

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
