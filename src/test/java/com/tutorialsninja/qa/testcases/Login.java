package com.tutorialsninja.qa.testcases;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.tutorialninja.qa.base.Base;
import com.tutorialninja.qa.pageObject.AccountPage;
import com.tutorialninja.qa.pageObject.HomePage;
import com.tutorialninja.qa.pageObject.LoginPage;
import com.tutorialninja.qa.utils.Utilities;

public class Login extends Base {
	WebDriver driver;

	@BeforeMethod
	public void setup() {
		loadPropertiesFile();
		driver = initializeBrowserAndOpenApplication(prop.getProperty("browser"));
		HomePage homePage = new HomePage(driver);
		homePage.clickOnMyAccount();
		homePage.clickOnLogin();
		driver.findElement(By.linkText("Login")).click();
	}

	@AfterMethod
	public void tearDown() {
		driver.quit();
	}

	@DataProvider
	public Object supplyTestData() {
		Object[][] data = Utilities.getTestDataFromExcel("Login");
		//Object data[][] = {{"tst@fff.com","Admin123"},{"test22@gmi.com","Admin123"}};
		return data;
	}

	@Test(dataProvider = "supplyTestData")
	public void verifyLoginWithValidCredentials(String email, String pass) {
		LoginPage loginPage = new LoginPage(driver);
		loginPage.enterEmailAddress(email);
		loginPage.enterPassword(pass);
		AccountPage accountPage = new AccountPage(driver);
		Assert.assertTrue(accountPage.isaccountPageDisplayed());
	}

	@Test
	public void verifyLoginWithInvalidCredentials() {
		LoginPage loginPage = new LoginPage(driver);
		loginPage.enterEmailAddress(Utilities.generateEmailWithTimeStamp());
		loginPage.enterPassword(dataProp.getProperty("invalidPass"));
		loginPage.submitLogin();
		String actualWarningMessage = loginPage.invalidLoginCredWarning();
		String ExpectedMessage = dataProp.getProperty("emailPassNotMatchWarning");
		Assert.assertTrue(actualWarningMessage.contains(ExpectedMessage), "Warning is not displayed");
	}

	@Test
	public void verifyLoginWithInvalidEmailValidPassword() {
		LoginPage loginPage = new LoginPage(driver);
		loginPage.enterEmailAddress(Utilities.generateEmailWithTimeStamp());
		loginPage.enterPassword(dataProp.getProperty("validpass"));
		loginPage.submitLogin();
		String actualWarningMessage = loginPage.invalidLoginCredWarning();
		String ExpectedMessage = dataProp.getProperty("emailPassNotMatchWarning");
		Assert.assertTrue(actualWarningMessage.contains(ExpectedMessage), "Warning is not displayed");
	}

	@Test
	public void verifyLoginWithValidEmailInvalidPassword() {
		LoginPage loginPage = new LoginPage(driver);
		loginPage.enterEmailAddress(prop.getProperty("validemail"));
		loginPage.enterPassword(dataProp.getProperty("invalidPass"));
		loginPage.submitLogin();
		String actualWarningMessage = loginPage.invalidLoginCredWarning();
		String ExpectedMessage = dataProp.getProperty("emailPassNotMatchWarning");
		Assert.assertTrue(actualWarningMessage.contains(ExpectedMessage), "Warning is not displayed");
	}

	@Test
	public void VerifyLoginWithoutCredentials() {
		LoginPage loginPage = new LoginPage(driver);
		loginPage.submitLogin();
		String actualWarningMessage = loginPage.invalidLoginCredWarning();
		String ExpectedMessage = dataProp.getProperty("emailPassNotMatchWarning");
		Assert.assertTrue(actualWarningMessage.contains(ExpectedMessage), "Warning is not displayed");
	}
}
