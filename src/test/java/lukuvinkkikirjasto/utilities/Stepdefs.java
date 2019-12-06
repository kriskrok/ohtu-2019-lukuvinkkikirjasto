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
    
    @Given("lisaa lukuvinkki is selected")
    public void commandAddNewBookSelected() throws Throwable {
        driver.get(baseUrl);
        WebElement element = driver.findElement(By.linkText("Lisää lukuvinkki"));
        element.click();  
    }


    // Tests for adding a new book 

    @Given("option kirja is selected")
    public void commandTypeOfTipIsBookIsSelected() throws Throwable {
        WebElement element = driver.findElement(By.name("book"));
        element.click();  
        element = driver.findElement(By.name("typeOfReadingTipButton"));
        element.click();
    }

    @Given("the page has text {string}")
    public void thePageHasText(String content) {
        pageHasContent(content);
    }
    
    @When("a valid booktitle {string} and author {string} are entered")
    public void validBookTitleAndAuthorAreEntered(String bookTitle, String author) {
        bookTitleAndAuthorAreGiven(bookTitle, author);
    }

    @When("an empty booktitle {string} and author {string} are entered")
    public void emptyBookTitleIsEntered(String bookTitle, String author) {
        bookTitleAndAuthorAreGiven(bookTitle, author);
    }

    @When("a booktitle {string} and empty author {string} are entered")
    public void emptyAuthorIsEntered(String bookTitle, String author) {
        bookTitleAndAuthorAreGiven(bookTitle, author);
    }

    @When("too short booktitle {string} and author {string} are entered")
    public void tooShortTitleIsEntered(String bookTitle, String author) {
        bookTitleAndAuthorAreGiven(bookTitle, author);
    }

    @When("too long booktitle {string} and author {string} are entered")
    public void tooLongTitleIsEntered(String bookTitle, String author) {
        bookTitleAndAuthorAreGiven(bookTitle, author);
    }

    @When("a valid booktitle {string} and a too long author name {string} are entered")
    public void tooLongAuthorNameIsEntered(String bookTitle, String author) {
        bookTitleAndAuthorAreGiven(bookTitle, author);
    }

    @Then("system will respond with {string}")
    public void systemWillRespondWith(String expectedOutput) {
        pageHasContent(expectedOutput);
    }

    @Then("the page does not have content {string}")
    public void thePageDoesNotHaveContent(String content) {
        pageHasContent(content);
    }


    // Tests for listing the books

    @Given("katsele lukuvinkkeja is selected")
    public void commandCheckTheReadingTipsIsSelected() throws Throwable {
        driver.get(baseUrl);
        WebElement element = driver.findElement(By.linkText("Selaile lukuvinkkejä"));
        element.click();  
    }

    @Given("one book with title {string} and author {string} has already been successfully added")
    public void oneBookSuccessfullyAdded(String bookTitle, String author) {
        driver.get(baseUrl);
        WebElement element = driver.findElement(By.linkText("Lisää lukuvinkki"));
        element.click();
        element = driver.findElement(By.name("book"));
        element.click();  
        element = driver.findElement(By.name("typeOfReadingTipButton"));
        element.click();
        bookTitleAndAuthorAreGiven(bookTitle, author);
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


    // Test for adding a new podcast

    @Given("option podcast is selected") 
    public void commandTypeOfTipIsPodcastIsSelected() {
        WebElement element = driver.findElement(By.name("podcast"));
        element.click();  
        element = driver.findElement(By.name("typeOfReadingTipButton"));
        element.click();
    }

    @When("a valid episode title {string} is given and other fields are empty") 
    public void aValidEpisodeTitleAndNoOtherFieldsAreEntered(String title) {
        podcastDataIsEntered(title, "", "", "");   
    }

    @When("an invalid episode title {string} is given and other fields are empty") 
    public void anInvalidEpisodeTitleAndNoOtherFieldsAreEntered(String title) {
        podcastDataIsEntered(title, "", "", "");   
    }

    @When("a valid episode title and series title {string} are given and other fields are empty") 
    public void aValidSeriesAndEpisodeTitleAndNoOtherFieldsAreEntered(String seriesTitle) {
        podcastDataIsEntered("Podcast-Guru", seriesTitle, "", "");   
    }    

    @When("a valid episode title and an invalid series title {string} are given and other fields are empty") 
    public void anInvalidPodcastSeriesTitleAndNoOtherFieldsAreEntered(String seriesTitle) {
        podcastDataIsEntered("Podcast-Guru", seriesTitle, "", "");   
    }  


    //helper-methods

    private void commandIsSelected(String command, String url) throws Throwable {
        driver.get(baseUrl + "" + url);
        WebElement element = driver.findElement(By.linkText(command));
        element.click();
    }

    private void pageHasContent(String content) {
        assertTrue(driver.getPageSource().contains(content));
    }

    private void pageDoesNotHaveContent(String content) {
        assertTrue(!driver.getPageSource().contains(content));
    }

    private void bookTitleAndAuthorAreGiven(String bookTitle, String author) {
        pageHasContent("Syötä lukuvinkkikirjan tiedot:");
        WebElement element = driver.findElement(By.name("book-title"));
        element.sendKeys(bookTitle);
        element = driver.findElement(By.name("book-author"));
        element.sendKeys(author);
        element = driver.findElement(By.name("add-book-button"));
        element.submit(); 
    }

    private void podcastDataIsEntered(String title, String series, String author, String url) {
        pageHasContent("Syötä lukuvinkkipodcastin tiedot:");
        WebElement element = driver.findElement(By.name("podcast-title"));
        element.sendKeys(title);
        element = driver.findElement(By.name("podcast-creator"));
        element.sendKeys(author);
        element = driver.findElement(By.name("podcast-url"));
        element.sendKeys(url);
        element = driver.findElement(By.name("podcast-series"));
        element.sendKeys(series);
        element = driver.findElement(By.name("add-podcast-button"));
        element.submit(); 
    }

    
}
