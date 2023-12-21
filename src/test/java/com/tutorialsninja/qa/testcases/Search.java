package com.tutorialsninja.qa.testcases;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.tutorialninja.qa.base.Base;

public class Search extends Base {
	WebDriver driver;

	@BeforeMethod
	public void setup() {
		loadPropertiesFile();
		driver = initializeBrowserAndOpenApplication(prop.getProperty("browser"));
	}

	@AfterMethod
	public void teardown() {
		driver.quit();
	}

	@Test
	public void verifySearchWithValidproduct() {
		driver.findElement(By.cssSelector("input[name=\"search\"]")).sendKeys(dataProp.getProperty("validProduct"));
		driver.findElement(By.xpath("//i[@class='fa fa-search']")).click();
		Assert.assertTrue(driver.findElement(By.linkText("HP LP3065")).isDisplayed(),"Product is not Present");
	}
	
	@Test
	public void  verifySearchWithInvalidproduct() {
		driver.findElement(By.cssSelector("input[name=\"search\"]")).sendKeys(dataProp.getProperty("invalidProduct"));
		driver.findElement(By.xpath("//i[@class='fa fa-search']")).click();
	    String errorMessage = driver.findElement(By.xpath("//div[@id=\"content\"]/p[2]")).getText();	
		Assert.assertEquals(errorMessage, dataProp.getProperty("noProductPresentMessage"),"Product is  Present");
	}
	
	@Test
	public void verifySearchWithNoProduct() {
		driver.findElement(By.xpath("//i[@class='fa fa-search']")).click();
	    String errorMessage = driver.findElement(By.xpath("//div[@id=\"content\"]/p[2]")).getText();	
		Assert.assertEquals(errorMessage, dataProp.getProperty("noProductPresentMessage"),"Product is  Present");
	}
}
