package com.salesforce.automation.tests;

import java.time.Duration;

import com.salesforce.automation.pages.LoginPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.SkipException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.Assert.assertTrue;

public class ValidLoginTest {
    private static final Duration WAIT_TIMEOUT = Duration.ofSeconds(20);
    private WebDriver driver;
    private LoginPage loginPage;

    @BeforeMethod
    public void setUp() {
        try {
            ChromeOptions options = new ChromeOptions();
            if (Boolean.parseBoolean(System.getProperty("headless", "true"))) {
                options.addArguments("--headless=new");
            }
            driver = new ChromeDriver(options);
            driver.manage().timeouts().implicitlyWait(Duration.ZERO);
            loginPage = new LoginPage(driver, WAIT_TIMEOUT);
            loginPage.open();
        } catch (RuntimeException exception) {
            closeDriver();
            throw new IllegalStateException("Valid-login test setup failed.", exception);
        }
    }

    @Test
    public void loginWithValidCredentials() {
        String username = System.getenv("SALESFORCE_USERNAME");
        String password = System.getenv("SALESFORCE_PASSWORD");
        if (username == null || username.isBlank() || password == null || password.isBlank()) {
            throw new SkipException("Set SALESFORCE_USERNAME and SALESFORCE_PASSWORD to run the valid-login test.");
        }

        try {
            loginPage.enterUsername(username);
            if (!loginPage.isPasswordFieldVisible()) {
                loginPage.submit();
                if (!loginPage.waitForPasswordOrLoginError()) {
                    throw new IllegalStateException(loginPage.getLoginErrorText());
                }
            }
            loginPage.enterPassword(password);
            loginPage.submit();

            new WebDriverWait(driver, WAIT_TIMEOUT)
                    .until(ExpectedConditions.not(ExpectedConditions.urlContains("login.salesforce.com")));
            assertTrue(
                    !driver.getCurrentUrl().contains("login.salesforce.com"),
                    "A valid Salesforce login should leave the login page."
            );
        } catch (RuntimeException exception) {
            throw new AssertionError("Valid Salesforce login did not complete successfully.", exception);
        }
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        closeDriver();
    }

    private void closeDriver() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }
}
