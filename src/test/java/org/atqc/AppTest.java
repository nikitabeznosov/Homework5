package org.atqc;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;


public class AppTest
{
   private WebDriver driver;

   @BeforeTest
   public void initDriver() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get("https://www.ranorex.com/web-testing-examples/vip/");
   }
    @Test
    public void checkUrl() {
        assertEquals(driver.getCurrentUrl(), "https://www.ranorex.com/web-testing-examples/vip/");
    }
    @Test
    public void userCreation() throws InterruptedException {
        WebElement firstNameField = driver.findElement(By.id("FirstName"));
        firstNameField.click();
        firstNameField.sendKeys("Will");
        WebElement lastNameField = driver.findElement(By.id("LastName"));
        lastNameField.click();
        lastNameField.sendKeys("Smith");
        WebElement buttonAdd = driver.findElement(By.id("Add"));
        buttonAdd.click();
        Thread.sleep(1000);
        WebElement addedFirstName = driver.findElement(By.xpath("//*[@id=\"VIPs\"]/tbody/tr[2]/td[2]"));
        assertEquals(addedFirstName, "Will");
    }
   @AfterTest
   public void tearDown() {
      driver.quit();
    }
}
