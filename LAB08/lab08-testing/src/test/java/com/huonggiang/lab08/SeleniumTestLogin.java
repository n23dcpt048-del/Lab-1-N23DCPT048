package com.huonggiang.lab08;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.Assert.*;

public class SeleniumTestLogin {

    private WebDriver driver;

    @Before
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "D:\\path\\to\\chromedriver.exe"); // chỉnh lại đường dẫn nếu cần
        driver = new ChromeDriver();
        driver.get("http://localhost:8080/login"); // chỉnh lại URL nếu khác
    }

    @Test
    public void testLoginSuccess() {
        driver.findElement(By.id("username")).sendKeys("admin");
        driver.findElement(By.id("password")).sendKeys("admin123");
        driver.findElement(By.id("loginButton")).click();

        assertTrue(driver.getPageSource().contains("Welcome"));
    }

    @Test
    public void testLoginFailure() {
        driver.findElement(By.id("username")).sendKeys("admin");
        driver.findElement(By.id("password")).sendKeys("wrongpass");
        driver.findElement(By.id("loginButton")).click();

        assertTrue(driver.getPageSource().contains("Invalid credentials"));
    }

    @Test
    public void testLoginEmptyInput() {
        driver.findElement(By.id("loginButton")).click();
        assertTrue(driver.getPageSource().contains("Please enter username and password"));
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}