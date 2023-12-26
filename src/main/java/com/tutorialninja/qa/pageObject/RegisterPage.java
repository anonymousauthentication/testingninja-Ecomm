package com.tutorialninja.qa.pageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.tutorialninja.qa.utils.Utilities;

public class RegisterPage extends Utilities {
	WebDriver driver;

	public RegisterPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(css = "input[name=\"firstname\"]")
	WebElement firstnameField;

	@FindBy(css = "input[name=\"lastname\"]")
	WebElement lastnameField;

	@FindBy(css = "input[name=\"email\"]")
	WebElement emailField;

	@FindBy(css = "input[name=\"telephone\"]")
	WebElement telephoneField;

	@FindBy(css = "input[name=\"password\"]")
	WebElement passwordField;

	@FindBy(css = "input[name=\"confirm\"]")
	WebElement confirmPasswordField;

	@FindBy(css = "input[name=\"newsletter\"][value=\"1\"]")
	WebElement newsLetterCheckBox;

	@FindBy(css = "input[type=\"checkbox\"][value=\"1\"]")
	WebElement privacyPolicyCheckbox;

	@FindBy(css = "input[type=\"submit\"]")
	WebElement continueButton;

	@FindBy(xpath = "//div[@id=\"content\"] //h1")
	WebElement registerSuccessfulMessage;

	@FindBy(css = "div[class*=\"alert-danger\"]")
	WebElement alreadyExistEmailError;

	@FindBy(css = "div[class*=\"alert-danger\"]")
	WebElement privacyPolicyValidationMessage;

	@FindBy(xpath = "//input[@name=\"firstname\"]/following-sibling::div")
	WebElement firstNameValidationMessage;

	@FindBy(xpath = "//input[@name=\"lastname\"]/following-sibling::div")
	WebElement lastNameValidationMessage;

	@FindBy(xpath = "//input[@name=\"email\"]/following-sibling::div")
	WebElement emailValidationMessage;

	@FindBy(xpath = "//div[text()=\"Telephone must be between 3 and 32 characters!\"]")
	WebElement telephoneValidationMessage;

	@FindBy(xpath = "//input[@name=\"password\"]/following-sibling::div")
	WebElement passValidationMessage;

	public void firstNameEnter(String firstName) {
		firstnameField.sendKeys(firstName);
	}

	public void lastNameEnter(String lastName) {
		lastnameField.sendKeys(lastName);
	}

	public void emailEnter(String email) {
		emailField.sendKeys(email);
	}

	public void telephoneEnter(String telephone) {
		telephoneField.sendKeys(telephone);
	}

	public void passwordEnter(String password) {
		passwordField.sendKeys(password);
	}

	public void confirmPasswordEnter(String password) {
		confirmPasswordField.sendKeys(password);
	}

	public void newsLetterCheckBox() {
		newsLetterCheckBox.click();
	}

	public void privacyPolicyClick() {
		privacyPolicyCheckbox.click();
	}

	public void continueButtonClick() {
		continueButton.click();
	}

	public String registerSuccessful() {
		return registerSuccessfulMessage.getText();
	}

	public String alreadyExistEmailErrorGet() {
		WebDriverWait wait = Utilities.explicitlyWait(driver);
		wait.until(ExpectedConditions.visibilityOf(alreadyExistEmailError));
		return alreadyExistEmailError.getText();
	}

	public String privacyPolicyValidationMessageGet() {
		return privacyPolicyValidationMessage.getText();
	}

	public String firstNameValidationMessageGet() {
		return firstNameValidationMessage.getText();
	}

	public String lastNameValidationMessageGet() {
		return lastNameValidationMessage.getText();
	}

	public String emailValidationMessageGet() {
		return emailValidationMessage.getText();
	}

	public String telephoneValidationMessageGet() {
		return telephoneValidationMessage.getText();
	}

	public String passValidationMessageGet() {
		return passValidationMessage.getText();
	}
}
