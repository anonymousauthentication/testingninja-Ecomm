package com.tutorialninja.qa.pageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {

	WebDriver driver;

	public HomePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(linkText = "My Account")
	private WebElement myAccountDropDownMenu;

	@FindBy(linkText = "Login")
	private WebElement loginOption;

	public void clickOnMyAccount() {
		myAccountDropDownMenu.click();
	}

	public void clickOnLogin() {
		loginOption.click();
	}
}
