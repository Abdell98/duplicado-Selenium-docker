package com.vinsguru.tests.vendorportal;

import com.google.common.util.concurrent.Uninterruptibles;
import com.vinsguru.tests.AbstractTest;
import com.vinsguru.tests.vendorportal.model.VendorPortalTestData;
import com.vinsguru.util.JsonUtil;
import com.vinsguru.vendorportal.DashboardPage;
import com.vinsguru.vendorportal.LoginPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class VendorPortalTest extends AbstractTest {

    private LoginPage loginPage;
    private DashboardPage dashboardPage;
    private VendorPortalTestData testData;
    @BeforeTest
    @Parameters("testDataPath")
    public void setPageObjects(String testDataPath){
        this.loginPage = new LoginPage(driver);
        this.dashboardPage = new DashboardPage(driver);
        this.testData = JsonUtil.getTestData(testDataPath, VendorPortalTestData.class);
    }

    @Test
    public void loginTest(){
        loginPage.goTo("https://d1uh9e7cu07ukd.cloudfront.net/selenium-docker/vendor-app/login.html#");
        Assert.assertTrue(loginPage.isAt());
        loginPage.login(testData.username(), testData.password());
        Uninterruptibles.sleepUninterruptibly(5, TimeUnit.SECONDS);
    }
    @Test (dependsOnMethods = "loginTest")
    public void dashboardTest(){
        Assert.assertTrue(dashboardPage.isAt());
        Uninterruptibles.sleepUninterruptibly(5, TimeUnit.SECONDS);
        Assert.assertEquals(dashboardPage.getMonthlyEarning(),testData.monthlyEarning());
        Assert.assertEquals(dashboardPage.getAnnualEarning(), testData.annualEarning());
        Assert.assertEquals(dashboardPage.getProfitMargin(), testData.profitMargin());
        Assert.assertEquals(dashboardPage.getAvailableInventory(), testData.availableInventory());
        dashboardPage.searchOrderHistoryBy(testData.searchKeyword());
        Uninterruptibles.sleepUninterruptibly(5, TimeUnit.SECONDS);
        Assert.assertEquals(dashboardPage.getSearchResultsCount(), 8);
    }

    @Test (dependsOnMethods = "dashboardTest")
    public void LogoutTest(){
        dashboardPage.logout();
        Assert.assertTrue(loginPage.isAt());
    }
}
