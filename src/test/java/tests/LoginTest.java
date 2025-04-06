package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.LoginPage;
import utils.ExtentManager;
import utils.ScreenshotUtil;

public class LoginTest extends BaseTest {

    @DataProvider(name = "loginData")
    public Object[][] getLoginData() {
        return new Object[][]{
                {"standard_user", "secret_sauce", true},
                {"locked_out_user", "secret_sauce", false},
                {"invalid_user", "invalid_pass", false},
                {"", "secret_sauce", false},
                {"standard_user", "", false},
        };
    }

    @Test(dataProvider = "loginData")
    public void loginTest(String username, String password, boolean expectedSuccess) {
        ExtentManager.createTest("Login Test with username: " + username);
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(username, password);

        boolean actualSuccess = driver.getCurrentUrl().contains("inventory.html");
        String screenshotPath = ScreenshotUtil.captureScreenshot(driver, username);

        if (expectedSuccess) {
            if (actualSuccess) {
                ExtentManager.test.pass("Login successful. Screenshot: " + screenshotPath);
            } else {
                ExtentManager.test.fail("Expected login success but failed. Screenshot: " + screenshotPath);
            }
        } else {
            if (!actualSuccess) {
                ExtentManager.test.pass("Login failed as expected. Screenshot: " + screenshotPath);
            } else {
                ExtentManager.test.fail("Expected login failure but succeeded. Screenshot: " + screenshotPath);
            }
        }

        Assert.assertEquals(actualSuccess, expectedSuccess);
    }
}