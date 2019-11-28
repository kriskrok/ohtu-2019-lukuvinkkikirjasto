package lukuvinkkikirjasto.utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;
import spark.ModelAndView;
import static spark.Spark.*;
import spark.template.velocity.VelocityTemplateEngine;

import lukuvinkkikirjasto.domain.*;
import lukuvinkkikirjasto.data_access.*;


public class Application {

    static String layout = "templates/layout.html";
    static final String osoite = "data.txt";
    static List<Book> kirjatTiedostoon;
    static PrintWriter wr;
    static LukuvinkkiDao dao;

    public static void main(String[] args) throws Exception {
        
        port(findOutPort());
        if (dao == null) setDao(new BookDao());

        get("/", (request, response) -> {
            HashMap<String, String> model = new HashMap<>();
            model.put("template", "templates/index.html");
            return new ModelAndView(model, layout);
        }, new VelocityTemplateEngine());

        get("/lukuvinkit", (request, response) -> {
            HashMap<String, Object> model = new HashMap<>();
            List<Book> books = dao.getBooks();
            if (books.isEmpty()) {
                model.put("info", "Ei vielä lukuvinkkejä");
            }
            model.put("kirjat", books);
            model.put("template", "templates/lukuvinkit.html");
            return new ModelAndView(model, layout);
        }, new VelocityTemplateEngine());
        
        get("/index", (request, response) -> {
            HashMap<String, String> model = new HashMap<>();
            model.put("template", "templates/index.html");
            return new ModelAndView(model, layout);
        }, new VelocityTemplateEngine());

        get("/tyyppi", (request, response) -> {
            HashMap<String, String> model = new HashMap<>();
            model.put("template", "templates/typeOfReadingTip.html");
            return new ModelAndView(model, layout);
        }, new VelocityTemplateEngine());

        post("/tyyppi", (request, response) -> {
            HashMap<String, String> model = new HashMap<>();
            String typeOfReadingTip = request.queryParams("typeOfReadingTip");
            if (typeOfReadingTip.equals("book")) {
                model.put("template", "templates/lisaa_kirja.html");
                return new ModelAndView(model, layout);
            } else { // redirection to adding a book until we have more options available
                model.put("template", "templates/lisaa_kirja.html");
                return new ModelAndView(model, layout);
            }
        }, new VelocityTemplateEngine());

        get("/kirja", (request, response) -> {
            HashMap<String, String> model = new HashMap<>();
            model.put("template", "templates/lisaa_kirja.html");
            return new ModelAndView(model, layout);
        }, new VelocityTemplateEngine());


        get("/lukuvinkit/poista/:id", (request, response) -> {
            HashMap<String, String> model = new HashMap<>();
            dao.deleteBook(request.params(":id"));
            
            model.put("vahvistus", "Vinkki poistettu onnistuneesti");
            model.put("template", "templates/lukuvinkit.html");
            response.redirect("/lukuvinkit");
            return new ModelAndView(model, layout);
        }, new VelocityTemplateEngine());


        post("/kirja", (request, response) -> {
            HashMap<String, String> model = new HashMap<>();
            String kirjanNimi = request.queryParams("kirjan_nimi");
            String kirjoittaja = request.queryParams("kirjoittaja");

            if (!validateInput(kirjanNimi, 3, 100)) {
                model.put("virhe", "Kirjan nimen tulee olla 3-100 merkkiä");
                model.put("template", "templates/lisaa_kirja.html");
                return new ModelAndView(model, layout);
            }

            if (kirjoittaja.length() != 0 && !validateInput(kirjoittaja, 3, 50)) {
                model.put("virhe", "Kirjailijan nimen tulee olla 3-50 merkkiä");
                model.put("template", "templates/lisaa_kirja.html");
                return new ModelAndView(model, layout);
            }

            Book k = new Book(kirjanNimi, kirjoittaja);
            
            dao.newBook(kirjanNimi, kirjoittaja);

            model.put("vahvistus", kirjanNimi + " tallennettu!");
            model.put("template", "templates/lisaa_kirja.html");
            return new ModelAndView(model, layout);

        }, new VelocityTemplateEngine());


        /*
        post("/kirja", (request, response) -> {
            HashMap<String, String> model = new HashMap<>();
            String kirjanNimi = request.queryParams("kirjan_nimi");
            String kirjoittaja = request.queryParams("kirjoittaja");

            if (kirjanNimi.isEmpty() || kirjoittaja.isEmpty()) {
                model.put("virhe", "Täytäthän kummatkin tiedot!");
                model.put("template", "templates/lisaa_kirja.html");
                return new ModelAndView(model, layout);
            }
            
            // Sinin lisäys
            ArrayList<Kirja> kirjat = request.session().attribute("kirjat");
            kirjatTiedostoon = new ArrayList<Kirja>();
            if (kirjat == null) {
                kirjat = new ArrayList<Kirja>();
                request.session().attribute("kirjat", kirjat);
            }

            Kirja k = new Kirja(kirjanNimi, kirjoittaja);
            kirjat.add(k);
            kirjatTiedostoon.add(k);
            kirjoitaTiedostoon(osoite, kirjatTiedostoon);
            // Sinin lisäys

            model.put("vahvistus", kirjanNimi + " tallennettu!");
            model.put("template", "templates/lisaa_kirja.html");
            return new ModelAndView(model, layout);

        }, new VelocityTemplateEngine());

        */


        /* Esimerkki post-kutsun käsittelystä. Pidetään toistaiseksi menossa mukana.
        post("/user", (request, response) -> {
            HashMap<String, String> model = new HashMap<>();
            String username = request.queryParams("username");
            String password = request.queryParams("password");
            String passwordConf = request.queryParams("passwordConfirmation");
            
            CreationStatus status = authenticationService().createUser(username, password, passwordConf);
            
            if ( !status.isOk()) {
                model.put("error", String.join(",  ", status.errors()));
                model.put("template", "templates/user.html");
                return new ModelAndView(model, LAYOUT);
            }
                
           response.redirect("/welcome");
           return new ModelAndView(model, LAYOUT);
        }, new VelocityTemplateEngine());

         */
    }
    
    public static void kirjoitaTiedostoon(String osoite, List<Book> teksti) throws Exception {
        wr = new PrintWriter(osoite);
        for (int i = 0; i < teksti.size(); i++) {
            wr.println(teksti.get(i).toString());
        }
        wr.close();
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
