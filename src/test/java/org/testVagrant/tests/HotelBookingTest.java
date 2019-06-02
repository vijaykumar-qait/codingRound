package org.testVagrant.tests;
import org.apache.log4j.xml.DOMConfigurator;
import org.testVagrant.config.ConfigFileReader;
import org.testVagrant.core.BaseDriver;
import org.testVagrant.core.GenericFunctions;
import org.testVagrant.pageobjects.HotelBooking.HotelBookingPage;
import org.testVagrant.pageobjects.HotelBooking.HotelSearchResultPage;
import org.testVagrant.pageobjects.SingIn.LandingPage;
import org.testVagrant.utils.Log;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class HotelBookingTest extends BaseDriver{
	
	String URL;
	String browserType;
	int timeout;
	String checkIn, CheckOut;
	String whereLocation;
	String travellers;
	
	GenericFunctions generic;
	HotelBookingPage hotelBookingPage;
	HotelSearchResultPage hotelSearchResultPage;
	LandingPage landingPage;
	
	@BeforeTest
	public void BeforeTest() {
		URL = ConfigFileReader.getConfigValue("url");
		browserType = ConfigFileReader.getConfigValue("browser");
		whereLocation = ConfigFileReader.getConfigValue("where");
		checkIn = ConfigFileReader.getConfigValue("check_in");
		CheckOut = ConfigFileReader.getConfigValue("check_out");
		travellers = ConfigFileReader.getConfigValue("travellers");
		
		generic = new GenericFunctions();
		driver = generic.StartDriver(browserType);
		driver.manage().window().maximize();
		
		landingPage = new LandingPage(driver, generic);
		hotelBookingPage = new HotelBookingPage(driver, generic);
		hotelSearchResultPage = new HotelSearchResultPage(driver, generic);
		DOMConfigurator.configure("log4j.xml");
		generic.get(URL);
	}

	@Test
	public void TC_001_Verify_That_User_Is_On_Hotel_Page() {
    	String currenTestName = Thread.currentThread().getStackTrace()[1].getMethodName();
		Log.startTestCase(currenTestName);
		landingPage.click_hotel_Lnk();
		Assert.assertTrue(hotelBookingPage.getText_searchForHotel_WE().trim().equals("Search for hotels"), "User is not on Hotel Page");
		Log.endTestCase(currenTestName);
	}
	
	
	@Test(dependsOnMethods="TC_001_Verify_That_User_Is_On_Hotel_Page")
	public void TC_002_Verify_User_Should_Be_Able_To_Search_For_Hotels() {
    	String currenTestName = Thread.currentThread().getStackTrace()[1].getMethodName();
		Log.startTestCase(currenTestName);
		hotelBookingPage.select_To_Location(whereLocation);
		hotelBookingPage.select_checkInOutDate_WE(checkIn);
		hotelBookingPage.click_checkOutDate();
		hotelBookingPage.select_checkInOutDate_WE(CheckOut);
		hotelBookingPage.select_travellers_DD(travellers);
		hotelBookingPage.click_searchHotel_Btn();
		Assert.assertTrue(whereLocation.contains(hotelSearchResultPage.getText_searchSummary_WE().trim()), whereLocation + 
				" is not displayed on Search Result page");
		Log.endTestCase(currenTestName);
	}
	
	@AfterTest
	public void TearDown() {
		generic.closeBrowser();
	}
}
