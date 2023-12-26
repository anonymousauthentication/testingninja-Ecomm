package com.tutorialsninja.qa.testcases;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;import org.testng.annotations.Test;
import com.tutorialninja.qa.base.Base;
import com.tutorialninja.qa.pageObject.RegisterPage;
import com.tutorialninja.qa.utils.Utilities;

public class Register extends Base {
	WebDriver driver;

	@BeforeMethod
	public void setup() {
		loadPropertiesFile();
		driver = initializeBrowserAndOpenApplication(prop.getProperty("browser"));
		driver.findElement(By.linkText("My Account")).click();
		driver.findElement(By.linkText("Register")).click();
		
	}

	@AfterMethod
	public void tearDown() {
		driver.quit();
	}

	@Test
	public void registerWithMandetoryFields() {
		RegisterPage registerPage = new RegisterPage(driver);
		registerPage.firstNameEnter(dataProp.getProperty("firstName"));
		registerPage.lastNameEnter(dataProp.getProperty("lastName"));
		registerPage.emailEnter(Utilities.generateEmailWithTimeStamp());
		registerPage.telephoneEnter(dataProp.getProperty("telePhoneNumber"));
		registerPage.passwordEnter(prop.getProperty("validpass"));
		registerPage.confirmPasswordEnter(prop.getProperty("validpass"));
		registerPage.privacyPolicyClick();
		registerPage.continueButtonClick();
		String successfulMessage = registerPage.registerSuccessful();
		Assert.assertEquals(successfulMessage, dataProp.getProperty("accountSuccessfulCreateMessage"));
	}

	@Test
	public void registerWithAllMandatoryFiels() throws InterruptedException {
		RegisterPage registerPage = new RegisterPage(driver);
		registerPage.firstNameEnter(dataProp.getProperty("firstName"));
		registerPage.lastNameEnter(dataProp.getProperty("lastName"));
		registerPage.emailEnter(Utilities.generateEmailWithTimeStamp());
		registerPage.telephoneEnter(dataProp.getProperty("telePhoneNumber"));
		registerPage.passwordEnter(prop.getProperty("validpass"));
		registerPage.confirmPasswordEnter(prop.getProperty("validpass"));
		registerPage.newsLetterCheckBox();
		registerPage.privacyPolicyClick();
		registerPage.continueButtonClick();
		String successfulMessage = registerPage.registerSuccessful();
		Assert.assertEquals(successfulMessage, dataProp.getProperty("accountSuccessfulCreateMessage"));	
	}

	@Test
	public void registerWithAlreadyExistEmail() {
		RegisterPage registerPage = new RegisterPage(driver);
		registerPage.firstNameEnter(dataProp.getProperty("firstName"));
		registerPage.lastNameEnter(dataProp.getProperty("lastName"));
		registerPage.emailEnter("testdemo2@gamail.com");
		registerPage.telephoneEnter(dataProp.getProperty("telePhoneNumber"));
		registerPage.passwordEnter(prop.getProperty("validpass"));
		registerPage.confirmPasswordEnter(prop.getProperty("validpass"));
		registerPage.newsLetterCheckBox();
		registerPage.privacyPolicyClick();
		registerPage.continueButtonClick();
		String successfulMessage = registerPage.alreadyExistEmailErrorGet();
		Assert.assertEquals(successfulMessage, dataProp.getProperty("emailAlreadyRegister"));

	}

	@Test
	public void registerWithhoutFillingAnyDetail() {

		RegisterPage registerPage = new RegisterPage(driver);
		registerPage.continueButtonClick();
		
		String privacyPolicyMessag = registerPage.privacyPolicyValidationMessageGet();
		Assert.assertEquals(privacyPolicyMessag, dataProp.getProperty("privacyPolicyWarning"));

		String firstNameValidation = registerPage.firstNameValidationMessageGet();
		Assert.assertEquals(firstNameValidation, dataProp.getProperty("firstNameValidation"));

		String lastNameValidation = registerPage.lastNameValidationMessageGet();
		Assert.assertEquals(lastNameValidation, dataProp.getProperty("lastNameValidation"));

		String emailValidation = registerPage.emailValidationMessageGet();
		Assert.assertEquals(emailValidation, dataProp.getProperty("emailValidation"));

		String tetephoneValidation = registerPage.telephoneValidationMessageGet();
		Assert.assertEquals(tetephoneValidation, dataProp.getProperty("tetephoneValidation"));

		String passwordValidation = registerPage.passValidationMessageGet();
		Assert.assertEquals(passwordValidation, dataProp.getProperty("passwordValidation"));
	}
}
