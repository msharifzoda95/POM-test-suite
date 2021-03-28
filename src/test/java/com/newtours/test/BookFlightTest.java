package com.newtours.test;

import com.newtours.pages.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class BookFlightTest {

    private WebDriver driver;
    private String numOfPassengers;
    private String expectedPrice;

    @BeforeTest
    @Parameters({"numOfPassengers", "expectedPrice"})
    public void setupDriver(String numOfPassengers, String expectedPrice){
        this.numOfPassengers = numOfPassengers;
        this.expectedPrice = expectedPrice;
        System.setProperty("webdriver.chrome.driver","C:\\Users\\murod\\Desktop\\JAVA\\ChromeDriver\\chromedriver.exe");
        this.driver = new ChromeDriver();
    }

    @Test
    public void registrationPage(){
        RegistrationPage registrationPage = new RegistrationPage((driver));
        registrationPage.goTo();
        registrationPage.enterUserDetails("murodjon", "sharifzoda");
        registrationPage.enterUSerCredentials("msharifzoda", "skeepbop");
        registrationPage.submit();
    }

    @Test (dependsOnMethods = "registrationPage")
    public void registrationConfirmationPage(){
        RegistrationConfirmation registrationConfirmationPage = new RegistrationConfirmation(driver);
        registrationConfirmationPage.goToFlightDetailsPage();
    }

    @Test (dependsOnMethods = "registrationConfirmationPage")
    public void flightDetailsPage(){
        FlightsDetailsPage flightsDetailsPage = new FlightsDetailsPage(driver);
        flightsDetailsPage.selectPassengers(numOfPassengers);
        flightsDetailsPage.goToFindFlightsPage();
    }

    @Test(dependsOnMethods = "flightDetailsPage")
    public void findFlightPage(){
        FindFlightsPage findFlightsPage = new FindFlightsPage(driver);
        findFlightsPage.submitFindFlightsPage();
        findFlightsPage.goToFlightConfirmationPage();
    }
    @Test(dependsOnMethods = "findFlightPage")
    public void flightConfirmationPage(){
        FlightItineraryPage flightItineraryPage = new FlightItineraryPage(driver);
        String actualPrice = flightItineraryPage.getPrice();
        Assert.assertEquals(actualPrice, expectedPrice);
    }
    @AfterTest
    public void quitBrowser(){
        this.driver.quit();

    }
}
