package com.tutorialninja.qa.pageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SearchPage {
	WebDriver driver;
	public SearchPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(linkText="HP LP3065")
	private WebElement isProductPresent;
	
	@FindBy(xpath="//div[@id=\"content\"]/p[2]")
	WebElement noProductFoundError;
	
	public boolean isProductPresentAfterSearch() {
		return isProductPresent.isDisplayed();
	}
	
	public String noProductFoundErrorMessage() {
		return noProductFoundError.getText();
	}
}
