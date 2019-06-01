package org.testVagrant.pageobjects.FlightBooking;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testVagrant.core.GenericFunctions;

public class FlightPage {
	
	WebDriver driver;
	GenericFunctions generic;
	
	public FlightPage(WebDriver driver, GenericFunctions generic) {
		this.driver = driver;
		this.generic = generic;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(id="OneWay")
	WebElement oneWay_RdBtn;
	
	@FindBy(id="FromTag")
	WebElement from_Input;
	
	@FindBy(id="ToTag")
	WebElement to_Input

}
