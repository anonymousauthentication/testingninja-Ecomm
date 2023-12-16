package com.tutorialsninja.qa.testcases;

import static org.testng.Assert.assertEquals;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.tutorialninja.qa.base.Base;
import com.tutorialninja.qa.utils.Utilities;

public class Register extends Base {
	WebDriver driver;

	@BeforeMethod
	public void setup() {
		driver = initializeBrowserAndOpenApplication("chrome");
		driver.findElement(By.linkText("My Account")).click();
		driver.findElement(By.linkText("Register")).click();
	}

	@AfterMethod
	public void tearDown() {
		driver.quit();
	}

	@Test
	public void registerWithMandetoryFields() {
		driver.findElement(By.xpath("//input[@name=\"firstname\"]")).sendKeys("Arun");
		driver.findElement(By.cssSelector("input[name=\"lastname\"]")).sendKeys("Mootori");
		driver.findElement(By.cssSelector("input[name*=\"mail\"]")).sendKeys(Utilities.generateEmailWithTimeStamp());
		driver.findElement(By.xpath("//input[contains(@name,'phone')]")).sendKeys("7889564512");
		driver.findElement(By.cssSelector("div input[name=\"password\"] ")).sendKeys("Admin@123");
		driver.findElement(By.cssSelector("input[name=\"confirm\"]")).sendKeys("Admin@123");
		driver.findElement(By.cssSelector("input[name=\"agree\"]")).click();
		driver.findElement(By.cssSelector("input[type=\"submit\"]")).click();
		String successfulMessage = driver.findElement(By.cssSelector("div h1")).getText();
		Assert.assertEquals(successfulMessage, "Your Account Has Been Created!");
	}

	@Test
	public void registerWithAllMandatoryFiels() {
		driver.findElement(By.xpath("//input[@name=\"firstname\"]")).sendKeys("Arun");
		driver.findElement(By.cssSelector("input[name=\"lastname\"]")).sendKeys("Mootori");
		driver.findElement(By.cssSelector("input[name*=\"mail\"]")).sendKeys(Utilities.generateEmailWithTimeStamp());
		driver.findElement(By.xpath("//input[contains(@name,'phone')]")).sendKeys("7889564512");
		driver.findElement(By.cssSelector("div input[name=\"password\"] ")).sendKeys("Admin@123");
		driver.findElement(By.cssSelector("input[name=\"confirm\"]")).sendKeys("Admin@123");
		driver.findElement(By.cssSelector("input[name=\"agree\"]")).click();
		driver.findElement(By.cssSelector("input[type=\"submit\"]")).click();
		driver.findElement(By.cssSelector("input[type=\"radio\"][value=\"1\"][name*=\"newsletter\"]")).click();
		String successfulMessage = driver.findElement(By.cssSelector("div h1")).getText();
		Assert.assertEquals(successfulMessage, "Your Account Has Been Created!");
	}

	@Test
	public void registerWithAlreadyExistEmail() {
		driver.findElement(By.xpath("//input[@name=\"firstname\"]")).sendKeys("Arun");
		driver.findElement(By.cssSelector("input[name=\"lastname\"]")).sendKeys("Mootori");
		driver.findElement(By.cssSelector("input[name*=\"mail\"]")).sendKeys("testdemo2@gmail.com");
		driver.findElement(By.xpath("//input[contains(@name,'phone')]")).sendKeys("7889564512");
		driver.findElement(By.cssSelector("div input[name=\"password\"] ")).sendKeys("Admin@123");
		driver.findElement(By.cssSelector("input[name=\"confirm\"]")).sendKeys("Admin@123");
		driver.findElement(By.cssSelector("input[name=\"agree\"]")).click();
		driver.findElement(By.cssSelector("input[type=\"submit\"]")).click();
		driver.findElement(By.cssSelector("input[type=\"radio\"][value=\"1\"][name*=\"newsletter\"]")).click();
		String successfulMessage = driver.findElement(By.cssSelector("div[class*=\"alert-danger\"]")).getText();
		Assert.assertEquals(successfulMessage, "Warning: E-Mail Address is already registered!");

	}

	@Test
	public void registerWithhoutFillingAnyDetail() {

        driver.findElement(By.cssSelector("input[type=\"submit\"]")).click();
		
		String privacyPolicyMessag = driver.findElement(By.cssSelector("div[class*=\"alert-danger\"]")).getText();
		Assert.assertEquals(privacyPolicyMessag, "Warning: You must agree to the Privacy Policy!");

		String firstNameValidation = driver.findElement(By.xpath("//input[@name=\"firstname\"]/following-sibling::div")).getText();
		Assert.assertEquals(firstNameValidation, "First Name must be between 1 and 32 characters!");

		String lastNameValidation = driver.findElement(By.cssSelector("")).getText();
		Assert.assertEquals(lastNameValidation, "Last Name must be between 1 and 32 characters!");

		String emailValidation = driver.findElement(By.xpath("//input[@name=\"email\"]/following-sibling::div")).getText();
		Assert.assertEquals(emailValidation, "E-Mail Address does not appear to be valid!");

		String tetephoneValidation = driver.findElement(By.xpath("//div[text()=\"Telephone must be between 3 and 32 characters!\"]")).getText();
		Assert.assertEquals(tetephoneValidation, "Telephone must be between 3 and 32 characters!");

		String passwordValidation = driver.findElement(By.xpath("//input[@name=\"password\"]/following-sibling::div"))				.getText();
		Assert.assertEquals(passwordValidation, "Password must be between 4 and 20 characters!");
	}
}
