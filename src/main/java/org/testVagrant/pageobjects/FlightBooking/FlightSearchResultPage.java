package org.testVagrant.pageobjects.FlightBooking;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testVagrant.core.GenericFunctions;

public class FlightSearchResultPage {
	
	WebDriver driver;
	GenericFunctions generic;
	
	public FlightSearchResultPage(WebDriver driver, GenericFunctions generic) {
		this.driver = driver;
		this.generic = generic;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css="div[class=\"searchSummary\"]>strong")
	WebElement searchSummary;
	
	public String getString_searchSummary() {
		return generic.GetText(searchSummary);
	}
}
