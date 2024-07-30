package org.art.playground.misc.selenium;

import java.io.File;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SimpleWebBot {

    public static void main(String[] args) {
        String url = "https://www.google.com/";

        ChromeOptions options = new ChromeOptions();
        options.addExtensions(new File("/Users/artsiomh/IdeaProjects/playground/misc/src/main/resources/ext/Helium-10.crx"));

        WebDriver driver = new ChromeDriver(options);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        driver.navigate().refresh();

        String signInPage = "https://members.helium10.com/user/signin";
        driver.navigate().to(signInPage);

        String emailInput = "/html/body/div[2]/div/div/div/form/div[1]/input";
        driver.findElement(By.xpath(emailInput)).sendKeys("ahlukhouski@gmail.com");

        String passwordInput = "/html/body/div[2]/div/div/div/form/div[2]/input";
        driver.findElement(By.xpath(passwordInput)).sendKeys("Gjvigbw14!");

        try {
            Thread.sleep(120000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

//        String loginButton = "/html/body/div[2]/div/div/div/form/button";
//        wait.until(d -> d.findElement(By.xpath(loginButton)).isDisplayed());
//        driver.findElement(By.xpath(loginButton)).click();

        String search = "/html/body/div[1]/div[2]/div[1]/div/div[1]/div[5]/div[2]/div/div[1]/div/div/div/div/div/div/div/div/input";
        wait.until(d -> d.findElement(By.xpath(search)).isDisplayed());
        driver.findElement(By.xpath(search)).sendKeys("bla-bla-bla");



//        String logInHelium = "/html/body/section[1]/div/div/div[2]/div/form/div[6]/p/a";
//        wait.until(d -> d.findElement(By.xpath(logInHelium)).isEnabled());
//        driver.findElement(By.xpath(logInHelium)).click();


//        driver.navigate().to(url);
//
//        String acceptCookies = "/html/body/div[2]/div[3]/div[3]/span/div/div/div/div[3]/div[1]/button[2]/div";
//        wait.until(d -> d.findElement(By.xpath(acceptCookies)).isDisplayed());
//        driver.findElement(By.xpath(acceptCookies)).click();
//
//        String searchBox = "/html/body/div[1]/div[3]/form/div[1]/div[1]/div[1]/div/div[2]/textarea";
//        driver.findElement(By.xpath(searchBox)).sendKeys("Hello, Google!");
//        driver.findElement(By.xpath(searchBox)).submit();
    }

}
