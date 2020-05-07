package org.atqc;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Set;

import static org.testng.Assert.assertEquals;

public class Windows {
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
        @Test(description = "Check error in new window")
        public void emptyFirstName() {
            WebElement buttonAdd = driver.findElement(By.id("Add"));
            Set <String> oldWindows = driver.getWindowHandles();
         // Оставил, чтобы ты поняла как я мыслил:)
         // System.out.println("Firstly opened windows are " + oldWindows);
            buttonAdd.click();
            ArrayList<String> newWindows = new ArrayList<String>(driver.getWindowHandles());
         //   System.out.println("Secondly opened windows are " + newWindows);
            driver.switchTo().window(newWindows.get(newWindows.size()-1));
            WebElement errorText = driver.findElement(By.id("alertTextOK"));
            assertEquals(errorText.getText(), "Please specify 'First Name' value");
    }
    }
