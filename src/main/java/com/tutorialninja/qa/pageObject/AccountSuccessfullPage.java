package com.tutorialninja.qa.pageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AccountSuccessfullPage {
	WebDriver driver;
	
	public AccountSuccessfullPage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath = "//div[@id=\"content\"] //h1")
	WebElement registerSuccessfulMessage;
	
	public String registerSuccessful() {
		return registerSuccessfulMessage.getText();
	}
}
