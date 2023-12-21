package com.tutorialsninja.qa.testcases;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.tutorialninja.qa.base.Base;
import com.tutorialninja.qa.utils.Utilities;

public class Login extends Base {
	WebDriver driver;

	@BeforeMethod
	public void setup()  {
		loadPropertiesFile();
		driver = initializeBrowserAndOpenApplication(prop.getProperty("browser"));
		driver.findElement(By.linkText("My Account")).click();
		driver.findElement(By.linkText("Login")).click();
	}

	@AfterMethod
	public void tearDown() {
		driver.quit();
	}

	@Test
	public void verifyLoginWithValidCredentials() {
		driver.findElement(By.cssSelector("input[name=\"email\"]")).sendKeys(prop.getProperty("validemail"));
		driver.findElement(By.xpath("//input[@name=\"password\"]")).sendKeys(prop.getProperty("validpass"));
		driver.findElement(By.cssSelector("input[type=\"submit\"]")).click();
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
