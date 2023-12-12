package com.tutorialsninja.qa.testcases;

import java.time.Duration;
import java.util.Date;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class Login {
	WebDriver driver;

	@AfterMethod
	public void tearDown() {
		driver.quit();
	}

	@BeforeMethod
	public void setup() {
		String browserName = "chrome";
		if (browserName.equals("chrome")) {
			driver = new ChromeDriver();
		} else if (browserName.equals("firefox")) {
			driver = new FirefoxDriver();
		} else if (browserName.equals("edge")) {
			driver = new EdgeDriver();
		} else {
			System.out.println("Please choose specific Browser");
		}
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(5));
		driver.get("https://tutorialsninja.com/demo/");
		driver.findElement(By.linkText("My Account")).click();
		driver.findElement(By.linkText("Login")).click();
	}

	@Test
	public void verifyLoginWithValidCredentials() {
		driver.findElement(By.cssSelector("input[name=\"email\"]")).sendKeys("manoharkantjoshi@gmail.com");
		driver.findElement(By.xpath("//input[@name=\"password\"]")).sendKeys("Admin@123");
		driver.findElement(By.cssSelector("input[type=\"submit\"]")).click();
		Assert.assertTrue(driver.findElement(By.linkText("Edit your account information")).isDisplayed());
	}

	@Test
	public void verifyLoginWithInvalidCredentials() {
		driver.findElement(By.cssSelector("input[name=\"email\"]"))
				.sendKeys("manoharkantjoshi" + dateTimeStamp() + "@gmail.com");
		driver.findElement(By.xpath("//input[@name=\"password\"]")).sendKeys("Admin@1234");
		driver.findElement(By.cssSelector("input[type=\"submit\"]")).click();
		String actualWarningMessage = driver.findElement(By.cssSelector("div[class*=\"alert\"]")).getText();
		String ExpectedMessage = "Warning: No match for E-Mail Address and/or Password.";
		Assert.assertTrue(actualWarningMessage.contains(ExpectedMessage), "Warning is not displayed");
		// div[contains(@class,"alert")]
	}

	public void verifyLoginWithInvalidEmailValidPassword() {
		driver.findElement(By.cssSelector("input[name=\"email\"]"))
				.sendKeys("manoharkantjoshi" + dateTimeStamp() + "@gmail.com");
		driver.findElement(By.xpath("//input[@name=\"password\"]")).sendKeys("Admin@123");
		driver.findElement(By.cssSelector("input[type=\"submit\"]")).click();
		String actualWarningMessage = driver.findElement(By.cssSelector("div[class*=\"alert\"]")).getText();
		String ExpectedMessage = "Warning: No match for E-Mail Address and/or Password.";
		Assert.assertTrue(actualWarningMessage.contains(ExpectedMessage), "Warning is not displayed");
		// div[contains(@class,"alert")]
	}

	public void verifyLoginWithValidEmailInvalidPassword() {
		driver.findElement(By.cssSelector("input[name=\"email\"]")).sendKeys("manoharkantjoshi@gmail.com");
		driver.findElement(By.xpath("//input[@name=\"password\"]")).sendKeys("Admin@1234");
		driver.findElement(By.cssSelector("input[type=\"submit\"]")).click();
		String actualWarningMessage = driver.findElement(By.cssSelector("div[class*=\"alert\"]")).getText();
		String ExpectedMessage = "Warning: No match for E-Mail Address and/or Password.";
		Assert.assertTrue(actualWarningMessage.contains(ExpectedMessage), "Warning is not displayed");
		// div[contains(@class,"alert")]
	}

	public void VerifyLoginWithoutCredentials() {
		driver.findElement(By.cssSelector("input[name=\"email\"]")).sendKeys("");
		driver.findElement(By.xpath("//input[@name=\"password\"]")).sendKeys("");
		driver.findElement(By.cssSelector("input[type=\"submit\"]")).click();
		String actualWarningMessage = driver.findElement(By.cssSelector("div[class*=\"alert\"]")).getText();
		String ExpectedMessage = "Warning: No match for E-Mail Address and/or Password.";
		Assert.assertTrue(actualWarningMessage.contains(ExpectedMessage), "Warning is not displayed");
		// div[contains(@class,"alert")]
	}

	public String dateTimeStamp() {
		Date date = new Date();
		return date.toString().replace(" ", "_").replace(":", "_");
	}

}
