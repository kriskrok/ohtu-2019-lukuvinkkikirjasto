
package lukuvinkkikirjasto;

import java.util.HashMap;
import spark.ModelAndView;
import static spark.Spark.*;
import spark.template.velocity.VelocityTemplateEngine;

public class Application {
    
    static String LAYOUT = "templates/layout.html";
    
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

        get("/cake", (request, response) -> {
            HashMap<String, String> model = new HashMap<>();
            model.put("template", "templates/fortytwo.html");
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
      
    static int findOutPort() {
        if ( portFromEnv!=null ) {
            return Integer.parseInt(portFromEnv);
        }
        
        return 4567;
    }

    //TODO: Googleta ProcessBuilder();
    static String portFromEnv = new ProcessBuilder().environment().get("PORT");
    
    static void setEnvPort(String port){
        portFromEnv = port;
    }
}
