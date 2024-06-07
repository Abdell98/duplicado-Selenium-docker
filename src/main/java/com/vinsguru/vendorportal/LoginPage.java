package com.vinsguru.vendorportal;


import com.vinsguru.AbstractPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class LoginPage extends AbstractPage {

    @FindBy (id = "username")
    private WebElement usernameInput;
    @FindBy (id = "password")
    private WebElement passwordInput;

    @FindBy (id = "login")
    private WebElement loginButton;

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public boolean isAt() {
        this.wait.until(ExpectedConditions.visibilityOf(this.loginButton));
        return this.loginButton.isDisplayed();
    }
    public void goTo(String url){
        this.driver.get(url);
    }
    public void login (String usuario, String contraseña){
        this.usernameInput.sendKeys(usuario);
        this.passwordInput.sendKeys(contraseña);
        this.loginButton.click();
    }
}
