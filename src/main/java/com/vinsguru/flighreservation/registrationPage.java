package com.vinsguru.flighreservation;

import com.vinsguru.AbstractPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class registrationPage extends AbstractPage {
    
    @FindBy (id = "firstName")
    private WebElement firstName;

    @FindBy (id = "lastName")
    private WebElement lastName;

    @FindBy (id = "email")
    private WebElement email;

    @FindBy (id = "password")
    private WebElement Password;

    @FindBy (name = "street")
    private WebElement Street;

    @FindBy (name = "city")
    private WebElement City;

    @FindBy (name = "zip")
    private WebElement Zip;

    @FindBy (id = "register-btn")
    private WebElement registerButton;

    public registrationPage(WebDriver driver){
        super(driver);
    }

    @Override
    public boolean isAt() {
        this.wait.until(ExpectedConditions.visibilityOf(this.firstName));
        return this.firstName.isDisplayed();
    }

    public void goTo(String url){
        this.driver.get(url);
    }
    public void enterUserDetails(String lastName, String firstName){
        this.firstName.sendKeys(firstName);
        this.lastName.sendKeys(lastName);
    }
    public void enterUserCredentials(String email, String password){
        this.email.sendKeys(email);
        this.Password.sendKeys(password);
    }
    public void enterAddress(String street, String city, String zip){
        this.Street.sendKeys(street);
        this.City.sendKeys(city);
        this.Zip.sendKeys(zip);
    }
    public void register(){
        this.registerButton.click();
    }
}
