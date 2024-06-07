package com.vinsguru.tests.flightreservation;

import com.google.common.util.concurrent.Uninterruptibles;
import com.vinsguru.flighreservation.*;
import com.vinsguru.tests.AbstractTest;
import com.vinsguru.tests.flightreservation.model.FlightReservationTestData;
import com.vinsguru.util.JsonUtil;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class FlightReservationTest extends AbstractTest {

    private FlightReservationTestData testData;

    @BeforeTest
    @Parameters("testDataPath")
    public void setParameters(String testDataPath){
        this.testData = JsonUtil.getTestData(testDataPath, FlightReservationTestData.class);
    }

    @Test
    public void userRegistrationTest(){
        registrationPage registrationpage = new registrationPage(driver);
        registrationpage.goTo("https://d1uh9e7cu07ukd.cloudfront.net/selenium-docker/reservation-app/index.html");
        Assert.assertTrue(registrationpage.isAt());
        Uninterruptibles.sleepUninterruptibly(5, TimeUnit.SECONDS);
        registrationpage.enterUserDetails(testData.LastName(), testData.firstName());
        registrationpage.enterUserCredentials(testData.email(), testData.password());
        registrationpage.enterAddress(testData.street(), testData.city(), testData.zip());
        registrationpage.register();
    }
    @Test (dependsOnMethods = "userRegistrationTest")
    public void registrationConfirmationTest(){
        RegistrationConfirmationPage confirmation = new RegistrationConfirmationPage(driver);
        Assert.assertTrue(confirmation.isAt());
        Assert.assertEquals(confirmation.getFirstNameElement(), testData.firstName());
        Uninterruptibles.sleepUninterruptibly(5, TimeUnit.SECONDS);
        confirmation.goToFlightsSearch();
    }
    @Test (dependsOnMethods = "registrationConfirmationTest")
    public void flightSearchTest(){
       FlightsSearchPage flightsSearchPage = new FlightsSearchPage(driver);
       Assert.assertTrue(flightsSearchPage.isAt());
       Uninterruptibles.sleepUninterruptibly(5, TimeUnit.SECONDS);
       flightsSearchPage.selectPassengers(testData.passengersCount());
        Uninterruptibles.sleepUninterruptibly(5, TimeUnit.SECONDS);
       flightsSearchPage.searchFlights();
        Uninterruptibles.sleepUninterruptibly(5, TimeUnit.SECONDS);
    }
    @Test (dependsOnMethods = "flightSearchTest")
    public void flightsSelectionTest(){
        FlightsSelectionPage flightsSelectionPage = new FlightsSelectionPage(driver);
        Assert.assertTrue(flightsSelectionPage.isAt());
        Uninterruptibles.sleepUninterruptibly(5, TimeUnit.SECONDS);
        flightsSelectionPage.selectFlights();
        Uninterruptibles.sleepUninterruptibly(7, TimeUnit.SECONDS);
        flightsSelectionPage.confirmFlights();
        Uninterruptibles.sleepUninterruptibly(7, TimeUnit.SECONDS);
    }
    @Test (dependsOnMethods = "flightsSelectionTest")
    public void flightReservationConfirmationTest(){
        FlightConfirmationPage flightConfirmationPage = new FlightConfirmationPage(driver);
        Assert.assertTrue(flightConfirmationPage.isAt());
        Uninterruptibles.sleepUninterruptibly(5, TimeUnit.SECONDS);
        Assert.assertEquals(flightConfirmationPage.getPrice(), testData.expectedPrice());
    }
}
