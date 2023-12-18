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
		driver = initializeBrowserAndOpenApplication("chrome");
	}

	@AfterMethod
	public void teardown() {
		driver.quit();
	}

	@Test
	public void verifySearchWithValidproduct() {
		driver.findElement(By.cssSelector("input[name=\"search\"]")).sendKeys("HP");
		driver.findElement(By.xpath("//i[@class='fa fa-search']")).click();
		Assert.assertTrue(driver.findElement(By.linkText("HP LP3065")).isDisplayed(),"Product is not Present");
	}
	
	@Test
	public void  verifySearchWithInvalidproduct() {
		driver.findElement(By.cssSelector("input[name=\"search\"]")).sendKeys("Honda");
		driver.findElement(By.xpath("//i[@class='fa fa-search']")).click();
	    String errorMessage = driver.findElement(By.xpath("//div[@id=\"content\"]/p[2]")).getText();	
		Assert.assertEquals(errorMessage, "There is no product that matches the search criteria.","Product is  Present");
	}
	
	@Test
	public void verifySearchWithNoProduct() {
		driver.findElement(By.cssSelector("input[name=\"search\"]")).sendKeys("");
		driver.findElement(By.xpath("//i[@class='fa fa-search']")).click();
	    String errorMessage = driver.findElement(By.xpath("//div[@id=\"content\"]/p[2]")).getText();	
		Assert.assertEquals(errorMessage, "There is no product that matches the search criteria.","Product is  Present");
	}

}
