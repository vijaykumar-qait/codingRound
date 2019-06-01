package org.testVagrant.tests;
import org.apache.log4j.xml.DOMConfigurator;
import org.testVagrant.config.ConfigFileReader;
import org.testVagrant.core.BaseDriver;
import org.testVagrant.core.GenericFunctions;
import org.testVagrant.pageobjects.SingIn.LandingPage;
import org.testVagrant.pageobjects.SingIn.SignInFormPage;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class SignInTest extends BaseDriver{
	
	String URL;
	String browserType;
	int timeout;
	
	GenericFunctions generic;
	LandingPage landingPage;
	SignInFormPage signInFormPage;
	
	@BeforeTest
	public void BeforeTest() {
		URL = ConfigFileReader.getConfigValue("url");
		browserType = ConfigFileReader.getConfigValue("browser");
		generic = new GenericFunctions();
		driver = generic.StartDriver(browserType);
		driver.manage().window().maximize();
		
		landingPage = new LandingPage(driver, generic);
		signInFormPage = new SignInFormPage(driver, generic);
		DOMConfigurator.configure("log4j.xml");
		generic.get(URL);
	}
	
	@Test
	public void TC_001_Verify_SignIn_Modal_Window() {
    	landingPage.click_yourTrip_WE();
    	landingPage.click_singIn_Btn();
    	Assert.assertTrue(signInFormPage.isDisplayed_email_Input(), "Email input is not displayed");
    	Assert.assertTrue(signInFormPage.isDisplayed_password_Input(), "Password input is not displayed");
	}
	
    @Test(dependsOnMethods="TC_001_Verify_SignIn_Modal_Window")
    public void TC_002_Verify__Error_Message_is_Thrown_If_SignIn_Details_Are_Missing() {
    	signInFormPage.click_loginSingIn_Btn();
    	Assert.assertTrue(signInFormPage.getText_errorMessage_WE().contains("There were errors in your submission"), "Error message is not"
    			+ "thrown on sigining in with missing details");
    }

    @AfterTest
    public void TearDown() {
    	generic.closeBrowser();
    }
}
