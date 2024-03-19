package tqs.ua.pt;

import java.util.NoSuchElementException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;

public class BlazeSteps {

    private WebDriver driver;

    @When("I navigate to {string}")
    public void iNavigateTo(String url) {
        driver = WebDriverManager.chromedriver().create();
        driver.get(url);
    }

    @When("I choose depart in {string} and arrive in {string}")
    public void i_choose_depart_in_and_arrive_in(String departure, String arrive) {
        driver.findElement(By.xpath("//option[. = '" + departure + "']")).click();
        driver.findElement(By.xpath("//option[. = '" + arrive + "']")).click();
    }

    @When("I click Find Flights")
    public void i_click_find_flights() {
        driver.findElement(By.cssSelector(".btn-primary")).click();
    }

    @Then("I should be see the message {string}")
    public void iShouldSee(String result) {
        try {
            driver.findElement(
                    By.xpath("//*[contains(text(), '" + result + "')]"));
        } catch (NoSuchElementException e) {
            throw new AssertionError(
                    "\"" + result + "\" not available in results");
        }
    }

    @And("I choose the flight number {int}")
    public void i_choose_the_flight_number(Integer numFlight) {
        driver.findElement(By.cssSelector("tr:nth-child(" + numFlight + ") .btn")).click();
    }

    @And("I fill in the blanks with my data: I'm {string}, I live in {string}, {int}, {string}, {string}. My credit card from {string} has the number {int} and expires on {int}\\/{int}")
    public void fill_the_blanks(String name, String street, Integer zipCode, String city, String state,
            String creditCardType, Integer creditCardNumber, Integer creditCardMonthExpires,
            Integer creditCardYearExpires) {
        driver.findElement(By.id("inputName")).sendKeys(name);
        driver.findElement(By.id("address")).sendKeys(street);
        driver.findElement(By.id("city")).sendKeys(city);
        driver.findElement(By.id("state")).sendKeys(state);
        driver.findElement(By.xpath("//option[. = 'American Express']")).click();
        driver.findElement(By.id("creditCardNumber")).sendKeys(creditCardNumber.toString());
        driver.findElement(By.id("creditCardMonth")).sendKeys(creditCardMonthExpires.toString());
        driver.findElement(By.id("creditCardYear")).sendKeys(creditCardYearExpires.toString());
        driver.findElement(By.id("nameOnCard")).sendKeys(name);
        driver.findElement(By.cssSelector(".checkbox")).click();
        driver.findElement(By.cssSelector(".btn-primary")).click();
    }

}