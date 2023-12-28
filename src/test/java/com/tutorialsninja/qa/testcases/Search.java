package com.tutorialsninja.qa.testcases;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import com.tutorialninja.qa.base.Base;
import com.tutorialninja.qa.pageObject.HomePage;

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
		HomePage homepage = new HomePage(driver);
		homepage.searchBoxEnter(dataProp.getProperty("validProduct"));
		homepage.searchButtonClick();
		Assert.assertTrue(homepage.isProductPresentAfterSearch(), "Product is not Present");
	}

	@Test
	public void verifySearchWithInvalidproduct() {
		HomePage homepage = new HomePage(driver);
		homepage.searchBoxEnter(dataProp.getProperty("invalidProduct"));
		homepage.searchButtonClick();
		String errorMessage = homepage.noProductFoundErrorMessage();
		Assert.assertEquals(errorMessage, dataProp.getProperty("noProductPresentMessage"), "Product is  Present");
	}

	@Test
	public void verifySearchWithNoProduct() {
		HomePage homepage = new HomePage(driver);
		homepage.searchButtonClick();
		String errorMessage = homepage.noProductFoundErrorMessage();
		Assert.assertEquals(errorMessage, dataProp.getProperty("noProductPresentMessage"), "Product is  Present");
	}
}
