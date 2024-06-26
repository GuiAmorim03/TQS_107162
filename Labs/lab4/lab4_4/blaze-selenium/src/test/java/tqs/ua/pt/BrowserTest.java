package tqs.ua.pt;

// Generated by Selenium IDE
// import org.junit.Test;
// import org.junit.Before;
// import org.junit.After;
// import static org.junit.Assert.*;
// import static org.hamcrest.CoreMatchers.is;
// import static org.hamcrest.core.IsNot.not;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import io.github.bonigarcia.seljup.BrowserType;
import io.github.bonigarcia.seljup.DockerBrowser;
import io.github.bonigarcia.seljup.SeleniumJupiter;

import static org.assertj.core.api.Assertions.assertThat;


@ExtendWith(SeleniumJupiter.class)

public class BrowserTest {

  @Test
  public void test(@DockerBrowser(type = BrowserType.EDGE) WebDriver driver) {
    driver.get("https://blazedemo.com/");
    driver.manage().window().setSize(new Dimension(1920, 983));
    driver.findElement(By.cssSelector(".btn-primary")).click();
    driver.findElement(By.cssSelector("tr:nth-child(3) .btn")).click();
    driver.findElement(By.id("inputName")).click();
    driver.findElement(By.id("inputName")).sendKeys("John John");
    driver.findElement(By.id("address")).sendKeys("123 Main St.");
    driver.findElement(By.id("city")).sendKeys("Newark");
    driver.findElement(By.id("state")).sendKeys("NJ");
    driver.findElement(By.id("zipCode")).sendKeys("123546");
    driver.findElement(By.id("creditCardNumber")).sendKeys("123");
    driver.findElement(By.id("creditCardMonth")).sendKeys("09");
    driver.findElement(By.id("creditCardYear")).sendKeys("2023");
    driver.findElement(By.id("nameOnCard")).sendKeys("John John");
    driver.findElement(By.cssSelector(".btn-primary")).click();
    assertThat(driver.getTitle()).isEqualTo("BlazeDemo Confirmation");
  }
}
