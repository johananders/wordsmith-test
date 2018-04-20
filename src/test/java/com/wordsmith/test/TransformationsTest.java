package com.wordsmith.test;

import static org.junit.Assert.assertEquals;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class TransformationsTest {

    private static final String URL = "https://transformation-web.herokuapp.com/";

    private WebDriver driver;

    @BeforeClass
    public static void setupClass() {
        WebDriverManager.chromedriver().setup();
    }

    @Before
    public void setUp() throws Exception {
        driver = new ChromeDriver();
    }

    @After
    public synchronized void tearDown() throws Exception {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    public void reversesText() {
        driver.get(URL);

        final WebElement textarea = new WebDriverWait(driver, 10)
            .until(ExpectedConditions.presenceOfElementLocated(By.id("formControlsTextarea")));

        textarea.sendKeys("The red fox crosses the ice, intent on none of my business.");

        driver.findElement(By.className("btn")).click();

        final WebElement td = new WebDriverWait(driver, 10)
            .until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("td")));

        assertEquals(
            "ehT der xof sessorc eht eci, tnetni no enon fo ym ssenisub.",
            td.getText()
        );
    }
}
