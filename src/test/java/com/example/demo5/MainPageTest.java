package com.example.demo5;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

import org.openqa.selenium.By;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class MainPageTest {
    private WebDriver driver;

    @BeforeEach
    public void setUp() {
        ChromeOptions options = new ChromeOptions();
        options.setPageLoadStrategy(PageLoadStrategy.EAGER);
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
    }

    @AfterEach
    public void tearDown() {driver.quit();}

    @Test
    public void enable() {
        driver.get("https://demoqa.com/dynamic-properties");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(6));
        WebElement disableButton = driver.findElement(By.cssSelector("#enableAfter"));
        wait.until(ExpectedConditions.elementToBeClickable(disableButton));
        assertTrue(disableButton.isEnabled(), "Кнопка не активна");
    }

    @Test
    public void hidden() {

        driver.get("https://the-internet.herokuapp.com/dynamic_loading/1");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(6));
        driver.findElement(By.cssSelector("#start button")).click();
        WebElement helloText = driver.findElement(By.cssSelector("#finish h4"));
        wait.until(ExpectedConditions.visibilityOf(helloText));
        assertTrue(helloText.isDisplayed(), "Нет нужного текста");

    }
        @Test
        public void notLoaded () {

            driver.get("https://the-internet.herokuapp.com/dynamic_loading/2");
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(6));

            driver.findElement(By.cssSelector("#start button")).click();
            WebElement helloText = driver.findElement(By.cssSelector("#finish h4"));
            wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("#finish h4")));
            assertTrue(driver.findElement(By.cssSelector("#finish h4")).isDisplayed(), "Нет нужного текста");
        }

    @Test
    public void visibleAfter() {
        driver.get("https://demoqa.com/dynamic-properties");
        By button = By.cssSelector("#visibleAfter");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.elementToBeClickable(button));
        WebElement visibleButton = driver.findElement(By.cssSelector("#visibleAfter"));
        assertTrue(visibleButton.isEnabled(), "Кнопка видима после 5 сек");
    }
    }



