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
import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class Stepdefs {
    WebDriver driver = new HtmlUnitDriver();
    String baseUrl = "http://localhost:4567";
    
    // Tests for adding a new book 

    @Given("lisaa kirja lukuvinkkeihin is selected")
    public void commandAddNewBookSelected() throws Throwable {
        driver.get(baseUrl);
        WebElement element = driver.findElement(By.linkText("Lisää lukuvinkki"));
        element.click();  
    }

    @Given("option kirja is selected")
    public void commandTypeOfTipIsBookIsSelected() throws Throwable {
        WebElement element = driver.findElement(By.name("book"));
        element.click();  
        element = driver.findElement(By.name("typeOfReadingTipButton"));
        element.click();
    }
    
    @When("a valid booktitle {string} and writer {string} are entered")
    public void validBookTitleAndWriterAreEntered(String bookTitle, String writer) {
        bookTitleAndWriterAreGiven(bookTitle, writer);
    }

    @When("an empty booktitle {string} and writer {string} are entered")
    public void emptyBookTitleIsEntered(String bookTitle, String writer) {
        bookTitleAndWriterAreGiven(bookTitle, writer);
    }

    @When("a booktitle {string} and empty writer {string} are entered")
    public void emptyWriterIsEntered(String bookTitle, String writer) {
        bookTitleAndWriterAreGiven(bookTitle, writer);
    }

    @When("too short booktitle {string} and writer {string} are entered")
    public void tooShortTitleIsEntered(String bookTitle, String writer) {
        bookTitleAndWriterAreGiven(bookTitle, writer);
    }

    @When("too long booktitle {string} and writer {string} are entered")
    public void tooLongTitleIsEntered(String bookTitle, String writer) {
        bookTitleAndWriterAreGiven(bookTitle, writer);
    }

    @When("a valid booktitle {string} and a too long writer name {string} are entered")
    public void tooLongWriterNameIsEntered(String bookTitle, String writer) {
        bookTitleAndWriterAreGiven(bookTitle, writer);
    }
  
    @Then("system will respond with {string}")
    public void systemWillRespondWith(String expectedOutput) {
        pageHasContent(expectedOutput);
    }

    // Tests for listing the books

    @Given("katsele lukuvinkkeja is selected")
    public void commandCheckTheReadingTipsIsSelected() throws Throwable {
        driver.get(baseUrl);
        WebElement element = driver.findElement(By.linkText("Selaile lukuvinkkejä"));
        element.click();  
    }

    @Given("one book with title {string} and writer {string} has already been successfully added")
    public void oneBookSuccessfullyAdded(String bookTitle, String writer) {
        driver.get(baseUrl);
        WebElement element = driver.findElement(By.linkText("Lisää lukuvinkki"));
        element.click();
        element = driver.findElement(By.name("book"));
        element.click();  
        element = driver.findElement(By.name("typeOfReadingTipButton"));
        element.click();
        bookTitleAndWriterAreGiven(bookTitle, writer);
        element = driver.findElement(By.linkText("Etusivu"));
        element.click();
    }

    // Tests for deleting a book

    @Given("valitse poista lukuvinkki is selected") 
    public void commandCheckDeleteTipIsSelected() throws Throwable {
        driver.get(baseUrl + "/lukuvinkit");
        WebElement element = driver.findElement(By.linkText("poista"));
        element.click();

    }

    //helper-methods


    private void pageHasContent(String content) {
        assertTrue(driver.getPageSource().contains(content));
    }

    private void bookTitleAndWriterAreGiven(String bookTitle, String writer) {
        pageHasContent("Syötä lukuvinkkikirjan tiedot:");
        WebElement element = driver.findElement(By.name("book-title"));
        element.sendKeys(bookTitle);
        element = driver.findElement(By.name("book-author"));
        element.sendKeys(writer);
        element = driver.findElement(By.name("add-book-button"));
        element.submit(); 
    }
    
}
