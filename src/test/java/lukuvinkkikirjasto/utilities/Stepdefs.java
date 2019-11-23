package lukuvinkkikirjasto.utilities;
import lukuvinkkikirjasto.domain.*;
import lukuvinkkikirjasto.utilities.*;

import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;


public class Stepdefs {
    WebDriver driver = new HtmlUnitDriver();
    String baseUrl = "http://localhost:4567";
    
    // Tests for adding a new book 
    @Given("lisaa kirja lukuvinkkeihin is selected")
    public void commandAddNewBookSelected() throws Throwable {
        driver.get(baseUrl);
        WebElement element = driver.findElement(By.linkText("Lisää kirja lukuvinkkeihin"));
        element.click();  
    }

    
    @When("a valid booktitle {string} and writer {string} are entered")
    public void validBookTitleAndWriterAreEntered(String bookTitle, String writer) {
        bookTitleAndWriterAreGiven(bookTitle, writer);
    }

    @When("an empty booktitle {string} and writer {string} are entered")
    public void emplyBookTitleIsEntered(String bookTitle, String writer) {
        bookTitleAndWriterAreGiven(bookTitle, writer);
    }

    @Then("system will respond with {string}")
    public void systemWillRespondWith(String expectedOutput) {
        pageHasContent(expectedOutput);
    }

    //helper-methods
    private void pageHasContent(String content) {
        assertTrue(driver.getPageSource().contains(content));
    }

    private void bookTitleAndWriterAreGiven(String bookTitle, String writer) {
        pageHasContent("Syötä lukuvinkkikirjan tiedot:");
        WebElement element = driver.findElement(By.name("kirjan_nimi"));
        element.sendKeys(bookTitle);
        element = driver.findElement(By.name("kirjoittaja"));
        element.sendKeys(writer);
        element = driver.findElement(By.name("lisaa"));
        element.submit(); 
    }

}