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
		String successfulMessage = accountsuccessfullPage.registerSuccessful();
		Assert.assertEquals(successfulMessage, dataProp.getProperty("accountSuccessfulCreateMessage"));
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
		AccountSuccessfullPage accountsuccessfullPage = 	registerPage.continueButtonClick();
		String successfulMessage = accountsuccessfullPage.registerSuccessful();
		Assert.assertEquals(successfulMessage, dataProp.getProperty("accountSuccessfulCreateMessage"));	
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
		String successfulMessage = registerPage.alreadyExistEmailErrorGet();
		Assert.assertEquals(successfulMessage, dataProp.getProperty("emailAlreadyRegister"));

	}

	@Test(priority=4)
	public void registerWithhoutFillingAnyDetail() {
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
