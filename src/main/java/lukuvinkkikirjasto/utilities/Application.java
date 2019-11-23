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
import lukuvinkkikirjasto.domain.Kirja;


public class Application {

    static String LAYOUT = "templates/layout.html";
    // huom ! user täytyy muokata alla omaksi
    static final String osoite = "path to the file";
    static List<Kirja> kirjat = new ArrayList<>();
    static PrintWriter wr;

    public static void main(String[] args) {
        port(findOutPort());

        get("/", (request, response) -> {
            HashMap<String, String> model = new HashMap<>();
            model.put("template", "templates/index.html");
            return new ModelAndView(model, LAYOUT);
        }, new VelocityTemplateEngine());

        get("/index", (request, response) -> {
            HashMap<String, String> model = new HashMap<>();
            model.put("template", "templates/index.html");
            return new ModelAndView(model, LAYOUT);
        }, new VelocityTemplateEngine());

        get("/kirja", (request, response) -> {
            HashMap<String, String> model = new HashMap<>();
            model.put("template", "templates/lisaa_kirja.html");
            return new ModelAndView(model, LAYOUT);
        }, new VelocityTemplateEngine());

        post("/kirja", (request, response) -> {
            HashMap<String, String> model = new HashMap<>();
            String kirjanNimi = request.queryParams("kirjan_nimi");
            String kirjoittaja = request.queryParams("kirjoittaja");

            if (kirjanNimi.isEmpty() || kirjoittaja.isEmpty()) {
                model.put("virhe", "Täytäthän kummatkin tiedot!");
                model.put("template", "templates/lisaa_kirja.html");
                return new ModelAndView(model, LAYOUT);
            }
            
            // Sinin lisäys
            Kirja k = new Kirja(kirjanNimi, kirjoittaja);
            kirjat.add(k);
            kirjoitaTiedostoon(osoite, kirjat);
            // Sinin lisäys

            model.put("vahvistus", kirjanNimi + " tallennettu!");
            model.put("template", "templates/lisaa_kirja.html");
            return new ModelAndView(model, LAYOUT);

            //return new ModelAndView(model, LAYOUT);
        }, new VelocityTemplateEngine());

        get("/katsele", (request, response) -> {

            HashMap<String, String> model = new HashMap<>();

            for(int i = 0; i < kirjat.size(); i++) {
            model.put("kirjalista", kirjat.get(i).toString());
            }
            model.put("template", "templates/lukuvinkit.html");

            return new ModelAndView(model, LAYOUT);
        }, new VelocityTemplateEngine());


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
    
    public static void kirjoitaTiedostoon(String osoite, List<Kirja> teksti) throws Exception {
        wr = new PrintWriter(osoite);
        for(int i = 0; i < teksti.size(); i++) {
        wr.println(teksti.get(i).toString());
        }
        wr.close();
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
