package com.tutorialsninja.qa.testcases;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import com.tutorialninja.qa.base.Base;
import com.tutorialninja.qa.pageObject.HomePage;
import com.tutorialninja.qa.pageObject.SearchPage;

public class SearchTest extends Base {
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

	@Test(priority=1)
	public void verifySearchWithValidproduct() {
		HomePage homepage = new HomePage(driver);
		homepage.searchBoxEnter(dataProp.getProperty("validProduct"));
		SearchPage searchPage = homepage.searchButtonClick();
		Assert.assertTrue(searchPage.isProductPresentAfterSearch(), "Product is not Present");
	}

	@Test(priority=2)
	public void verifySearchWithInvalidproduct() {
		HomePage homepage = new HomePage(driver);
		homepage.searchBoxEnter(dataProp.getProperty("invalidProduct"));
		SearchPage searchPage = homepage.searchButtonClick();
		String errorMessage = searchPage.noProductFoundErrorMessage();
		Assert.assertEquals(errorMessage, dataProp.getProperty("noProductPresentMessage"), "Product is  Present");
	}

	@Test(priority=3)
	public void verifySearchWithNoProduct() {
		HomePage homepage = new HomePage(driver);
		SearchPage searchPage = homepage.searchButtonClick();
		String errorMessage = searchPage.noProductFoundErrorMessage();
		Assert.assertEquals(errorMessage, dataProp.getProperty("noProductPresentMessage"), "Product is  Present");
	}
}
