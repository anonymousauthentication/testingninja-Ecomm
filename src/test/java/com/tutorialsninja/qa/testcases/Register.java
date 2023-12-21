package com.tutorialsninja.qa.testcases;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;import org.testng.annotations.Test;
import com.tutorialninja.qa.base.Base;
import com.tutorialninja.qa.utils.Utilities;

public class Register extends Base {
	WebDriver driver;

	@BeforeMethod
	public void setup() {
		loadPropertiesFile();
		driver = initializeBrowserAndOpenApplication(prop.getProperty("browser"));
		driver.findElement(By.linkText("My Account")).click();
		driver.findElement(By.linkText("Register")).click();
	}

	@AfterMethod
	public void tearDown() {
		driver.quit();
	}

	@Test
	public void registerWithMandetoryFields() {
		driver.findElement(By.xpath("//input[@name=\"firstname\"]")).sendKeys(dataProp.getProperty("firstName"));
		driver.findElement(By.cssSelector("input[name=\"lastname\"]")).sendKeys(dataProp.getProperty("lastName"));
		driver.findElement(By.cssSelector("input[name*=\"mail\"]")).sendKeys(Utilities.generateEmailWithTimeStamp());
		driver.findElement(By.xpath("//input[contains(@name,'phone')]")).sendKeys(dataProp.getProperty("telePhoneNumber"));
		driver.findElement(By.cssSelector("div input[name=\"password\"] ")).sendKeys(prop.getProperty("validpass"));
		driver.findElement(By.cssSelector("input[name=\"confirm\"]")).sendKeys(prop.getProperty("validpass"));
		driver.findElement(By.cssSelector("input[name=\"agree\"]")).click();
		driver.findElement(By.cssSelector("input[type=\"submit\"]")).click();
		String successfulMessage = driver.findElement(By.xpath("//div[@id=\"content\"] //h1")).getText();
		Assert.assertEquals(successfulMessage, dataProp.getProperty("accountSuccessfulCreateMessage"));
	}

	@Test
	public void registerWithAllMandatoryFiels() throws InterruptedException {
		driver.findElement(By.xpath("//input[@name=\"firstname\"]")).sendKeys(dataProp.getProperty("firstName"));
		driver.findElement(By.cssSelector("input[name=\"lastname\"]")).sendKeys(dataProp.getProperty("lastName"));
		driver.findElement(By.cssSelector("input[name*=\"mail\"]")).sendKeys(Utilities.generateEmailWithTimeStamp());
		driver.findElement(By.xpath("//input[contains(@name,'phone')]")).sendKeys(dataProp.getProperty("telePhoneNumber"));
		driver.findElement(By.cssSelector("div input[name=\"password\"] ")).sendKeys(prop.getProperty("validpass"));
		driver.findElement(By.cssSelector("input[name=\"confirm\"]")).sendKeys(prop.getProperty("validpass"));
		driver.findElement(By.cssSelector("input[value='1'][name='newsletter']")).click();
		driver.findElement(By.cssSelector("input[name=\"agree\"]")).click();
		driver.findElement(By.cssSelector("input[type=\"submit\"]")).click();
		String successfulMessage = driver.findElement(By.xpath("//div[@id=\"content\"] //h1")).getText();
		Assert.assertEquals(successfulMessage, dataProp.getProperty("accountSuccessfulCreateMessage"));
	
		
	}

	@Test
	public void registerWithAlreadyExistEmail() {
		driver.findElement(By.xpath("//input[@name=\"firstname\"]")).sendKeys(dataProp.getProperty("firstName"));
		driver.findElement(By.cssSelector("input[name=\"lastname\"]")).sendKeys(dataProp.getProperty("lastName"));
		driver.findElement(By.cssSelector("input[name*=\"mail\"]")).sendKeys("testdemo2@gmail.com");
		driver.findElement(By.cssSelector("div input[name=\"password\"] ")).sendKeys(prop.getProperty("validpass"));
		driver.findElement(By.cssSelector("input[name=\"confirm\"]")).sendKeys(prop.getProperty("validpass"));
		driver.findElement(By.cssSelector("input[value='1'][name='newsletter']")).click();
		driver.findElement(By.cssSelector("input[name=\"agree\"]")).click();
		driver.findElement(By.cssSelector("input[type=\"submit\"]")).click();
		String successfulMessage = driver.findElement(By.cssSelector("div[class*=\"alert-danger\"]")).getText();
		Assert.assertEquals(successfulMessage, dataProp.getProperty("emailAlreadyRegister"));

	}

	@Test
	public void registerWithhoutFillingAnyDetail() {

        driver.findElement(By.cssSelector("input[type=\"submit\"]")).click();
		
		String privacyPolicyMessag = driver.findElement(By.cssSelector("div[class*=\"alert-danger\"]")).getText();
		Assert.assertEquals(privacyPolicyMessag, dataProp.getProperty("privacyPolicyWarning"));

		String firstNameValidation = driver.findElement(By.xpath("//input[@name=\"firstname\"]/following-sibling::div")).getText();
		Assert.assertEquals(firstNameValidation, dataProp.getProperty("firstNameValidation"));

		String lastNameValidation = driver.findElement(By.xpath("//input[@name=\"lastname\"]/following-sibling::div")).getText();
		Assert.assertEquals(lastNameValidation, dataProp.getProperty("lastNameValidation"));

		String emailValidation = driver.findElement(By.xpath("//input[@name=\"email\"]/following-sibling::div")).getText();
		Assert.assertEquals(emailValidation, dataProp.getProperty("emailValidation"));

		String tetephoneValidation = driver.findElement(By.xpath("//div[text()=\"Telephone must be between 3 and 32 characters!\"]")).getText();
		Assert.assertEquals(tetephoneValidation, dataProp.getProperty("tetephoneValidation"));

		String passwordValidation = driver.findElement(By.xpath("//input[@name=\"password\"]/following-sibling::div"))				.getText();
		Assert.assertEquals(passwordValidation, dataProp.getProperty("passwordValidation"));
	}
}
