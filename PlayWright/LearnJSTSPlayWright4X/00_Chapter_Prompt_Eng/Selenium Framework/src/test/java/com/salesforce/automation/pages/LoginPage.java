package com.salesforce.automation.pages;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage {
    private static final String LOGIN_URL = "https://login.salesforce.com/?locale=in";

    private final WebDriver driver;
    private final WebDriverWait wait;

    @FindBy(xpath = "//input[@id='username']")
    private WebElement username;

    @FindBy(xpath = "//input[@id='password']")
    private WebElement password;

    @FindBy(xpath = "//input[@id='Login']")
    private WebElement loginButton;

    @FindBy(xpath = "//input[@id='rememberUn']")
    private WebElement rememberMe;

    @FindBy(xpath = "//*[@id='error']")
    private WebElement loginError;

    public LoginPage(WebDriver driver, Duration timeout) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, timeout);
        PageFactory.initElements(driver, this);
    }

    public void open() {
        try {
            driver.get(LOGIN_URL);
            wait.until(ExpectedConditions.visibilityOf(username));
        } catch (RuntimeException exception) {
            throw new IllegalStateException("Unable to open the Salesforce login page.", exception);
        }
    }

    public void enterUsername(String value) {
        try {
            WebElement field = wait.until(ExpectedConditions.visibilityOf(username));
            field.clear();
            field.sendKeys(value);
        } catch (RuntimeException exception) {
            throw new IllegalStateException("Unable to enter the Salesforce username.", exception);
        }
    }

    public void enterPassword(String value) {
        try {
            WebElement field = wait.until(ExpectedConditions.visibilityOf(password));
            field.clear();
            field.sendKeys(value);
        } catch (RuntimeException exception) {
            throw new IllegalStateException("Unable to enter the Salesforce password.", exception);
        }
    }

    public void submit() {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(loginButton)).click();
        } catch (RuntimeException exception) {
            throw new IllegalStateException("Unable to submit the Salesforce login form.", exception);
        }
    }

    public String getLoginErrorText() {
        try {
            return wait.until(ExpectedConditions.visibilityOf(loginError)).getText().trim();
        } catch (RuntimeException exception) {
            throw new IllegalStateException("Salesforce did not display the expected login error.", exception);
        }
    }

    public boolean isPasswordFieldVisible() {
        try {
            return driver.findElements(By.xpath("//input[@id='password']"))
                    .stream()
                    .anyMatch(WebElement::isDisplayed);
        } catch (RuntimeException exception) {
            throw new IllegalStateException("Unable to inspect the Salesforce password step.", exception);
        }
    }

    public boolean waitForPasswordOrLoginError() {
        try {
            wait.until(currentDriver -> {
                List<WebElement> passwordFields = currentDriver.findElements(By.xpath("//input[@id='password']"));
                List<WebElement> errors = currentDriver.findElements(By.xpath("//*[@id='error']"));
                return passwordFields.stream().anyMatch(WebElement::isDisplayed)
                        || errors.stream().anyMatch(WebElement::isDisplayed);
            });
            return isPasswordFieldVisible();
        } catch (RuntimeException exception) {
            throw new IllegalStateException("Salesforce did not advance to a password step or display a login error.", exception);
        }
    }

    public boolean isRememberMeSelected() {
        try {
            return wait.until(ExpectedConditions.visibilityOf(rememberMe)).isSelected();
        } catch (RuntimeException exception) {
            throw new IllegalStateException("Unable to read the Salesforce remember-me control.", exception);
        }
    }

    public void toggleRememberMe() {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(rememberMe)).click();
        } catch (RuntimeException exception) {
            throw new IllegalStateException("Unable to toggle the Salesforce remember-me control.", exception);
        }
    }
}
