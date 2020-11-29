package org.example;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SignInPage {
    WebDriver driver;
    private static Logger logger = LogManager.getLogger(SignInPage.class);

    public SignInPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(className = "header2__auth")
    private WebElement buttonAuthAndReg;

    @FindBy(css = "form.new-log-reg__form input[name='email']")
    private WebElement inputEmail;

    @FindBy(css = "form.new-log-reg__form input[name='password']")
    private WebElement inputPassword;

    @FindBy(css = "form.new-log-reg__form button")
    private WebElement buttonAuth;

    public SignInPage openPage() {
        driver.get("https://otus.ru/");
        logger.info("Open main page");
        return this;
    }

    private void openSignInPopup() {
        buttonAuthAndReg.click();
    }

    private void inputEmail(String email) {
        inputEmail.sendKeys(email);
    }

    private void inputPassword(String password) {
        inputPassword.sendKeys(password);
    }

    private void signInSubmit() {
        buttonAuth.click();
    }

    public void signIn(String email, String password) {
        openSignInPopup();
        inputEmail(email);
        inputPassword(password);
        signInSubmit();
        logger.info("Authorizated");
    }
}
