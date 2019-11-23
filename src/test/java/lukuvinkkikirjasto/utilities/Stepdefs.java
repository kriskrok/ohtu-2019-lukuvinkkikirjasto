package lukuvinkkikirjasto.utilities;

import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.*;

import lukuvinkkikirjasto.domain.*;
import lukuvinkkikirjasto.utilities.*;

public class Stepdefs {
    Application app;
    
    @Before
    public void setup(){
    }
    
    @Given("^command lisaa uusi kirja is selected$")
    public void commandAddNewBookSelected() throws Throwable {
    }

    @When("booktitle {string} and writer {string} are entered")
    public void bookTitleAndWriterAreEntered(String bookTitle, String writer) {       
    }
    
    @Then("system will respond with {string}")
    public void systemWillRespondWith(String expectedOutput) {
    }
 

}