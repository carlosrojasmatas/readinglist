package com.spia.readinglist;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;


@RunWith(SpringRunner.class)
@SpringBootTest(
        classes = ReadinglistApplication.class,
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ServerWebTest {
    @LocalServerPort
    private int port;

    private static ChromeDriver browser;

    @BeforeClass
    public static void openBrowser() {
        var opts = new ChromeOptions();
        opts.addArguments("--remote-allow-origins=*");
        browser = new ChromeDriver(opts);
        browser.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        System.setProperty("webdriver.chrome.driver","/opt/homebrew/bin/chromedriver");

    }

    @Test
    public void addBookToEmptyList() {
        browser.get("http://localhost:" + port + "/login");
        browser.findElement(By.id("username")).sendKeys("carlos");
        browser.findElement(By.id("password")).sendKeys("carlos");
        browser.findElement(By.tagName("button")).click();
        browser.get("http://localhost:" + port + "/carlos");
        assertEquals("You have no books in your book list", browser.findElement(By.tagName("div")).getText());
        browser.findElement(By.name("title"))
                .sendKeys("BOOK TITLE");
        browser.findElement(By.name("author"))
                .sendKeys("BOOK AUTHOR");
        browser.findElement(By.name("isbn"))
                .sendKeys("12345");
        browser.findElement(By.name("description"))
                .sendKeys("DESCRIPTION");
        browser.findElement(By.tagName("form")).submit();

        var dl = browser.findElement(By.cssSelector("dt.bookHeadline"));
        assertEquals("BOOK TITLE by BOOK AUTHOR (ISBN: 12345)",dl.getText());

        var dt = browser.findElement(By.cssSelector("dd.bookDescription"));
        assertEquals("DESCRIPTION",dt.getText());

    }

    @AfterClass
    public static void closeBrowser() {
        browser.quit();
    }
}
