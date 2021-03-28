package com.searchmodule.tests;

import com.searchmodule.pages.SearchPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class SearchTest {
    private WebDriver driver;

    @BeforeTest
    public void setupDriver(){
        System.setProperty("webdriver.chrome.driver","C:\\Users\\murod\\Desktop\\JAVA\\ChromeDriver\\chromedriver.exe");
        this.driver = new ChromeDriver();
    }

    @Test
    @Parameters({"keyword"})
    public void search(String keyword){
        SearchPage searchPage = new SearchPage(driver);
        searchPage.goTo();
        searchPage.doSearch(keyword);
        searchPage.clickVideos();
        int size = searchPage.getResult();

        Assert.assertTrue(size > 0);
    }

    @AfterTest
    public void quitDriver(){

    }
}
