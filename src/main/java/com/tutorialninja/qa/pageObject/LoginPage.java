package com.tutorialninja.qa.pageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
	
	WebDriver driver;
	public LoginPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css="input[name=\"email\"]")
	private WebElement emailFiels;
	
	@FindBy(css="input[name=\'password\"]")
	private WebElement passFiels;
	
	@FindBy(css="input[type=\'submit\"]")
	private WebElement loginSubmit;
	
	public void enterEmailAddress(String email) {
		emailFiels.sendKeys(email);
	}
	
	public void enterPassword(String passFields) {
		passFiels.click();
	}
	
	public void submitLogin() {
		loginSubmit.click();
	}

}
