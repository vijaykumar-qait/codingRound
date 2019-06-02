package org.testVagrant.pageobjects.SingIn;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testVagrant.core.GenericFunctions;

/*
 * This is LandingPage class where all the page actions regarding the landing page will be defined
 */

public class LandingPage {
	
	WebDriver driver;
	GenericFunctions generic;
	
	public LandingPage(WebDriver driver, GenericFunctions generic) {
		this.driver = driver;
		this.generic = generic;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css="form[id=\"SearchForm\"]>h1")
	WebElement searchFlight_WE;
	
	@FindBy(css="a[id=\"userAccountLink\"]>span[class *= \"truncate\"]")
	WebElement yourTrip_WE;
	
	@FindBy(id="SignIn")
	WebElement singIn_Btn;
	
	@FindBy(css="a[title*=\"Find flights\"]")
	WebElement flight_Lnk;
	
	public String getText_searchFlight_WE() {
		return generic.GetText(searchFlight_WE);
	}
	
	public void click_yourTrip_WE() {
		generic.Click(yourTrip_WE);
	}
	
	public void click_singIn_Btn() {
		generic.MouseHoverAndClick(singIn_Btn);
	}
	
	public void click_flight_Lnk() {
		generic.Click(flight_Lnk);
	}
}
