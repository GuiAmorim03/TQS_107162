package tqs.ua.pt.backend;

// Generated by Selenium IDE
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.JavascriptExecutor;

import io.github.bonigarcia.seljup.SeleniumJupiter;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith(SeleniumJupiter.class)
public class FrontendTest {
  private WebDriver driver;
  private Map<String, Object> vars;
  JavascriptExecutor js;

  @BeforeEach
  public void setUp() {
    driver = new ChromeDriver();
    js = (JavascriptExecutor) driver;
    vars = new HashMap<String, Object>();
  }

  @AfterEach
  public void tearDown() {
    driver.quit();
  }

  @Test
  public void frontend() {
    driver.get("http://localhost:8000/");

    // index.html
    // driver.manage().window().setSize(new Dimension(1366, 768));
    assertThat(driver.getTitle()).isEqualTo("BuzzyTrips | Search for a Trip");
    driver.findElement(By.id("origin")).sendKeys("Madrid");
    driver.findElement(By.id("destination")).sendKeys("Paris");
    driver.findElement(By.id("date")).sendKeys("13/04/2024");
    driver.findElement(By.cssSelector(".btn")).click();

    // chooseTicket.html
    assertThat(driver.getTitle()).isEqualTo("BuzzyTrips | Search for a Ticket");
    assertThat(driver.findElement(By.id("from")).getText()).isEqualTo("Madrid");
    assertThat(driver.findElement(By.id("to")).getText()).isEqualTo("Paris");
    assertThat(driver.findElement(By.id("currentDate")).getText()).isEqualTo("on April 13, 2024");

    driver.findElement(By.cssSelector(".form-select")).sendKeys("GBP (£)");

    WebElement element = driver.findElement(By.id("18"));
    Actions actions = new Actions(driver);
    actions.moveToElement(element).click().perform();
    // as 3 linhas anteriores foram para dar scroll até ao elemento, porque não
    // estava a conseguir clicá-lo

    // makeReservation.html
    assertThat(driver.getTitle()).isEqualTo("BuzzyTrips | Make Reservation");
    driver.findElement(By.id("name")).sendKeys("John Character");
    driver.findElement(By.id("email")).sendKeys("johny@ua.pt");
    driver.findElement(By.id("phone")).sendKeys("987654321");
    driver.findElement(By.id("nif")).sendKeys("159159159");
    driver.findElement(By.id("address")).sendKeys("Somewhere Street, New Imaginary City, no.1");
    {
      WebElement dropdown = driver.findElement(By.id("paymentMethod"));
      dropdown.findElement(By.xpath("//option[. = 'Bank Card']")).click();
    }
    driver.findElement(By.id("paymentNumber")).sendKeys("0005524861657");
    {
      WebElement dropdown = driver.findElement(By.id("qtt"));
      dropdown.findElement(By.xpath("//option[. = '3']")).click();
    }
    element = driver.findElement(By.cssSelector(".btn"));
    actions = new Actions(driver);
    actions.moveToElement(element).click().perform();

    // reservationSuccess.html
    assertThat(driver.getTitle()).isEqualTo("BuzzyTrips | Thank you for your reservation");

  }
}