package com.schogini.container.test;

import java.net.MalformedURLException;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.net.URL;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterTest;

import com.schogini.container.pages.GooglePage;

public class GoogleTest {

    private WebDriver driver;
    private GooglePage google;

    @BeforeTest
    public void setUp() throws MalformedURLException {
        DesiredCapabilities dc = DesiredCapabilities.chrome();
        //IP address of local machine - 192.168.1.104
        driver = new RemoteWebDriver(new URL("http://192.168.1.104:4444/wd/hub"), dc);
        google = new GooglePage(driver);
    }

    @Test
    public void googleTest() throws InterruptedException {
        google.goTo();
        google.searchFor("automation");
        // google.searchFor("dsagtqwe80-");
        Assert.assertTrue(google.getResults().size() >= 10);
    }

    @Test
    public void googleSecondTest() throws InterruptedException {
        google.goTo();
        Thread.sleep(1000);
        Boolean ispresent = false;
        if (driver.getPageSource().contains("I'm Feeling Lucky")) {
            ispresent = true;
            System.out.println("Pass");
        } else {
            ispresent = false;
            System.out.println("Fail");
        }
        Assert.assertTrue(ispresent);
    }
    
    @AfterTest
    public void tearDown() throws InterruptedException {
        driver.quit();
    }    

}