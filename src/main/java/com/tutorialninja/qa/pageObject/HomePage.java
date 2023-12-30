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
	
	@FindBy(linkText="Register")
	private WebElement registerButton;
	
	@FindBy(css="input[name=\"search\"]")
	private WebElement searchBox;
	
	@FindBy(css="i[class*=\"search\"]")
	private WebElement searchButton;

	@FindBy(linkText="HP LP3065")
	private WebElement isProductPresent;
	
	@FindBy(xpath="//div[@id=\"content\"]/p[2]")
	WebElement noProductFoundError;
	
	public void clickOnMyAccount() {
		myAccountDropDownMenu.click();
	}

	public LoginPage clickOnLogin() {
		loginOption.click();
		return new LoginPage(driver);
		
	}
	
	public RegisterPage registerClick() {
		registerButton.click();
		return new RegisterPage(driver);
	}
	
	public void searchBoxEnter(String searchTerm) {
		searchBox.sendKeys(searchTerm);
	}
	
	public void searchButtonClick() {
		searchButton.click();
	}
	
	public boolean isProductPresentAfterSearch() {
		return isProductPresent.isDisplayed();
	}
	
	public String noProductFoundErrorMessage() {
		return noProductFoundError.getText();
	}
}
