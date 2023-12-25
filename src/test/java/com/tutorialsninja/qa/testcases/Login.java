package com.tutorialsninja.qa.testcases;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.tutorialninja.qa.base.Base;
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
		loginPage.submitLogin();
		Assert.assertTrue(driver.findElement(By.linkText("Edit your account information")).isDisplayed());
	}

	@Test
	public void verifyLoginWithInvalidCredentials() {
		driver.findElement(By.cssSelector("input[name=\"email\"]")).sendKeys(Utilities.generateEmailWithTimeStamp());
		driver.findElement(By.xpath("//input[@name=\"password\"]")).sendKeys(dataProp.getProperty("invalidPass"));
		driver.findElement(By.cssSelector("input[type=\"submit\"]")).click();
		String actualWarningMessage = driver.findElement(By.cssSelector("div[class*=\"alert\"]")).getText();
		String ExpectedMessage = dataProp.getProperty("emailPassNotMatchWarning");
		Assert.assertTrue(actualWarningMessage.contains(ExpectedMessage), "Warning is not displayed");
		// div[contains(@class,"alert")]
	}

	@Test
	public void verifyLoginWithInvalidEmailValidPassword() {
		driver.findElement(By.cssSelector("input[name=\"email\"]")).sendKeys(Utilities.generateEmailWithTimeStamp());
		driver.findElement(By.xpath("//input[@name=\"password\"]")).sendKeys(prop.getProperty("validpass"));
		driver.findElement(By.cssSelector("input[type=\"submit\"]")).click();
		String actualWarningMessage = driver.findElement(By.cssSelector("div[class*=\"alert\"]")).getText();
		String ExpectedMessage = dataProp.getProperty("emailPassNotMatchWarning");
		Assert.assertTrue(actualWarningMessage.contains(ExpectedMessage), "Warning is not displayed");
		// div[contains(@class,"alert")]
	}

	@Test
	public void verifyLoginWithValidEmailInvalidPassword() {
		driver.findElement(By.cssSelector("input[name=\"email\"]")).sendKeys(prop.getProperty("validemail"));
		driver.findElement(By.xpath("//input[@name=\"password\"]")).sendKeys(dataProp.getProperty("invalidPass"));
		driver.findElement(By.cssSelector("input[type=\"submit\"]")).click();
		String actualWarningMessage = driver.findElement(By.cssSelector("div[class*=\"alert\"]")).getText();
		String ExpectedMessage = dataProp.getProperty("emailPassNotMatchWarning");
		Assert.assertTrue(actualWarningMessage.contains(ExpectedMessage), "Warning is not displayed");
		// div[contains(@class,"alert")]
	}

	@Test
	public void VerifyLoginWithoutCredentials() {
		driver.findElement(By.cssSelector("input[type=\"submit\"]")).click();
		String actualWarningMessage = driver.findElement(By.cssSelector("div[class*=\"alert\"]")).getText();
		String ExpectedMessage = dataProp.getProperty("emailPassNotMatchWarning");
		Assert.assertTrue(actualWarningMessage.contains(ExpectedMessage), "Warning is not displayed");
		// div[contains(@class,"alert")]
	}
}
