package com.tutorialsninja.qa.testcases;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;import org.testng.annotations.Test;
import com.tutorialninja.qa.base.Base;
import com.tutorialninja.qa.pageObject.AccountSuccessfullPage;
import com.tutorialninja.qa.pageObject.HomePage;
import com.tutorialninja.qa.pageObject.RegisterPage;
import com.tutorialninja.qa.utils.Utilities;

public class RegisterTest extends Base {
	WebDriver driver;
	RegisterPage registerPage;
	AccountSuccessfullPage accountsuccessfullPage;

	@BeforeMethod
	public void setup() {
		loadPropertiesFile();
		driver = initializeBrowserAndOpenApplication(prop.getProperty("browser"));
		HomePage homePage = new HomePage(driver);
		homePage.clickOnMyAccount();
	    registerPage = homePage.registerClick();

	}

	@AfterMethod
	public void tearDown() {
		driver.quit();
	}

	@Test(priority=1)
	public void registerWithMandetoryFields() {
		registerPage.firstNameEnter(dataProp.getProperty("firstName"));
		registerPage.lastNameEnter(dataProp.getProperty("lastName"));
		registerPage.emailEnter(Utilities.generateEmailWithTimeStamp());
		registerPage.telephoneEnter(dataProp.getProperty("telePhoneNumber"));
		registerPage.passwordEnter(prop.getProperty("validpass"));
		registerPage.confirmPasswordEnter(prop.getProperty("validpass"));
		registerPage.privacyPolicyClick();
		accountsuccessfullPage=	registerPage.continueButtonClick();
		Assert.assertEquals(accountsuccessfullPage.registerSuccessful(), dataProp.getProperty("accountSuccessfulCreateMessage"));
	}        

	@Test(priority=2)
	public void registerWithAllMandatoryFiels() throws InterruptedException {
		registerPage.firstNameEnter(dataProp.getProperty("firstName"));
		registerPage.lastNameEnter(dataProp.getProperty("lastName"));
		registerPage.emailEnter(Utilities.generateEmailWithTimeStamp());
		registerPage.telephoneEnter(dataProp.getProperty("telePhoneNumber"));
		registerPage.passwordEnter(prop.getProperty("validpass"));
		registerPage.confirmPasswordEnter(prop.getProperty("validpass"));
		registerPage.newsLetterCheckBox();
		registerPage.privacyPolicyClick();
		Assert.assertEquals(accountsuccessfullPage.registerSuccessful(), dataProp.getProperty("accountSuccessfulCreateMessage"));	
	}

	@Test(priority=3)
	public void registerWithAlreadyExistEmail() {
		registerPage.firstNameEnter(dataProp.getProperty("firstName"));
		registerPage.lastNameEnter(dataProp.getProperty("lastName"));
		registerPage.emailEnter("testdemo2@gamail.com");
		registerPage.telephoneEnter(dataProp.getProperty("telePhoneNumber"));
		registerPage.passwordEnter(prop.getProperty("validpass"));
		registerPage.confirmPasswordEnter(prop.getProperty("validpass"));
		registerPage.newsLetterCheckBox();
		registerPage.privacyPolicyClick();
		registerPage.continueButtonClick();
		Assert.assertEquals(registerPage.alreadyExistEmailErrorGet(), dataProp.getProperty("emailAlreadyRegister"));

	}

	@Test(priority=4)
	public void registerWithhoutFillingAnyDetail() {
		registerPage.continueButtonClick();
		Assert.assertEquals(registerPage.privacyPolicyValidationMessageGet(), dataProp.getProperty("privacyPolicyWarning"));
		Assert.assertEquals(registerPage.firstNameValidationMessageGet(), dataProp.getProperty("firstNameValidation"));
		Assert.assertEquals(registerPage.lastNameValidationMessageGet(), dataProp.getProperty("lastNameValidation"));
		Assert.assertEquals(registerPage.emailValidationMessageGet(), dataProp.getProperty("emailValidation"));
		Assert.assertEquals(registerPage.telephoneValidationMessageGet(), dataProp.getProperty("tetephoneValidation"));
		Assert.assertEquals(registerPage.passValidationMessageGet(), dataProp.getProperty("passwordValidation"));
	}
}
