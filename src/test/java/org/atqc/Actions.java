package org.atqc;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;

import static org.testng.Assert.assertEquals;


public class Actions
{
   private WebDriver driver;

   @BeforeMethod
   public void initDriver() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get("https://www.ranorex.com/web-testing-examples/vip/");
   }
   @AfterMethod
    public void tearDown() {
        driver.quit();
    }
    @Test
    public void checkUrl() {
        assertEquals(driver.getCurrentUrl(), "https://www.ranorex.com/web-testing-examples/vip/");
    }
    @Test
       public void loadData() {
           WebElement loadButton = driver.findElement(By.id("Load"));
           loadButton.click();
     String loadFirstName = driver.findElement(By.xpath("//*[@id='VIPs']/tbody/tr[2]/td[2]")).getText();
     assertEquals(loadFirstName, "Sylvester");
      }
    @Test
    public void userCreation() {
        WebElement firstNameField = driver.findElement(By.id("FirstName"));
        firstNameField.click();
        firstNameField.sendKeys("Will");
        WebElement lastNameField = driver.findElement(By.id("LastName"));
        lastNameField.click();
        lastNameField.sendKeys("Smith");
        WebElement buttonAdd = driver.findElement(By.id("Add"));
        buttonAdd.click();
        String addedFirstName = driver.findElement(By.xpath("//*[@id='VIPs']/tbody/tr[2]/td[2]")).getText();
        assertEquals(addedFirstName, "Will");
           }
}