package org.testVagrant.tests;
import org.apache.log4j.xml.DOMConfigurator;
import org.testVagrant.config.ConfigFileReader;
import org.testVagrant.core.BaseDriver;
import org.testVagrant.core.GenericFunctions;
import org.testVagrant.pageobjects.FlightBooking.FlightPage;
import org.testVagrant.pageobjects.FlightBooking.FlightSearchResultPage;
import org.testVagrant.pageobjects.SingIn.LandingPage;
import org.testVagrant.utils.Log;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class FlightBookingTest extends BaseDriver{
	
	String URL;
	String browserType;
	int timeout;
	String toLocation, fromLocation;
	String departDate;
	
	GenericFunctions generic;
	FlightPage flightPage;
	FlightSearchResultPage flightSearchResultPage;
	LandingPage landingPage;
	
	@BeforeTest
	public void BeforeTest() {
		URL = ConfigFileReader.getConfigValue("url");
		browserType = ConfigFileReader.getConfigValue("browser");
		toLocation = ConfigFileReader.getConfigValue("to");
		fromLocation = ConfigFileReader.getConfigValue("from");
		departDate = ConfigFileReader.getConfigValue("departDate");
		generic = new GenericFunctions();
		driver = generic.StartDriver(browserType);
		driver.manage().window().maximize();
		
		landingPage = new LandingPage(driver, generic);
		flightPage = new FlightPage(driver, generic);
		flightSearchResultPage = new FlightSearchResultPage(driver, generic);
		DOMConfigurator.configure("log4j.xml");
		generic.get(URL);
	}

	@Test
	public void TC_001_Verify_Clear_Trip_Home_Page() {
    	String currenTestName = Thread.currentThread().getStackTrace()[1].getMethodName();
		Log.startTestCase(currenTestName);
		landingPage.click_flight_Lnk();
		Assert.assertTrue(landingPage.getText_searchFlight_WE().contains("Search flights"));
		Log.endTestCase(currenTestName);
	}
	
	@Test(dependsOnMethods="TC_001_Verify_Clear_Trip_Home_Page")
	public void TC_002_Verify_That_Results_Appear_For_A_OneWay_Journey() {
    	String currenTestName = Thread.currentThread().getStackTrace()[1].getMethodName();
		Log.startTestCase(currenTestName);
		flightPage.click_oneWay_RdBtn();
		flightPage.select_From_Location(fromLocation);
		flightPage.select_To_Location(toLocation);
		flightPage.select_Date_WE(departDate);
		flightPage.click_searchBtn();
		String actualSearchSummary = flightSearchResultPage.getString_searchSummary().trim();
		Assert.assertTrue(actualSearchSummary.contains(toLocation) &&
				actualSearchSummary.contains(fromLocation), 
				"Location: " + toLocation + " or " + fromLocation + " is not displayed");
		Log.endTestCase(currenTestName);
	}
	
	@AfterTest
	public void TearDown() {
		generic.closeBrowser();
	}
}
