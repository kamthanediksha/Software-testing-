package com.salesforce.automation.tests;

import java.time.Duration;

import com.salesforce.automation.pages.LoginPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

public class InvalidLoginTest {
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
            throw new IllegalStateException("Invalid-login test setup failed.", exception);
        }
    }

    @Test
    public void rejectsInvalidCredentials() {
        try {
            loginPage.enterUsername("invalid.user@example.invalid");
            if (loginPage.isPasswordFieldVisible()) {
                loginPage.enterPassword("InvalidPassword-NotARealSecret");
                loginPage.submit();
            } else {
                loginPage.submit();
                if (loginPage.waitForPasswordOrLoginError()) {
                    loginPage.enterPassword("InvalidPassword-NotARealSecret");
                    loginPage.submit();
                }
            }

            String error = loginPage.getLoginErrorText();
            assertFalse(error.isBlank(), "Salesforce should explain why invalid credentials were rejected.");
            assertTrue(
                    error.toLowerCase().contains("check your username and password")
                            || error.toLowerCase().contains("did you forget your password"),
                    "Salesforce should display its invalid-credentials message."
            );
        } catch (RuntimeException exception) {
            throw new AssertionError("Invalid-credentials scenario did not complete as expected.", exception);
        }
    }

    @Test
    public void rejectsMissingUsername() {
        try {
            loginPage.submit();

            String error = loginPage.getLoginErrorText();
            assertTrue(error.toLowerCase().contains("username"), "Salesforce should request a username.");
        } catch (RuntimeException exception) {
            throw new AssertionError("Missing-username scenario did not complete as expected.", exception);
        }
    }

    @Test
    public void rememberMeCanBeToggled() {
        try {
            boolean initialState = loginPage.isRememberMeSelected();
            loginPage.toggleRememberMe();
            assertTrue(loginPage.isRememberMeSelected() != initialState, "Remember-me selection should toggle.");
        } catch (RuntimeException exception) {
            throw new AssertionError("Remember-me scenario did not complete as expected.", exception);
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
