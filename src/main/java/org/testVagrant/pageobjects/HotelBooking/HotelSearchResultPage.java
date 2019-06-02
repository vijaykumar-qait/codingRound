package org.testVagrant.pageobjects.HotelBooking;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testVagrant.core.GenericFunctions;

public class HotelSearchResultPage {

	WebDriver driver;
	GenericFunctions generic;
	
	public HotelSearchResultPage(WebDriver driver, GenericFunctions generic) {
		this.driver = driver;
		this.generic = generic;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css="div[class=\"searchSummary\"] span[class=\"fillCityName\"]")
	WebElement searchSummary_WE;
	
	public String getText_searchSummary_WE() {
		return generic.GetText(searchSummary_WE);
	}
		
}
