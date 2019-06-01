package org.testVagrant.pageobjects.FlightBooking;

import java.util.Calendar;
import java.util.List;
import java.util.TimeZone;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testVagrant.core.GenericFunctions;

public class FlightPage {
	
	WebDriver driver;
	GenericFunctions generic;
	WebDriverWait wait;
	
	public FlightPage(WebDriver driver, GenericFunctions generic) {
		this.driver = driver;
		this.generic = generic;
		PageFactory.initElements(driver, this);
		wait = new WebDriverWait(driver, 20);
	}
	
	@FindBy(id="OneWay")
	WebElement oneWay_RdBtn;
	
	@FindBy(id="FromTag")
	WebElement from_Input;
	
	@FindBy(id="ToTag")
	WebElement to_Input;
	
	By originOptionList_Lnk = By.cssSelector("ul[id*=\\\"id-1\\\"]>li>a");
	By destinationOption_Lnk = By.cssSelector("ul[id*=\"id-2\"]>li>a");
	
	public void click_oneWay_RdBtn() {
		generic.Click(oneWay_RdBtn);
	}
	
	public void fillText_from_Input(String location) {
		generic.Fill_Text(from_Input, location);
	}
	
	public void select_Location(String location) {
		fillText_from_Input(location);
		List<WebElement> originOptions = wait.until(ExpectedConditions.
				visibilityOfAllElements(generic.getElementList(By.cssSelector(""))));
		
		for(WebElement element : originOptions) {
			if(element.getText().trim().contains(location.trim())) {
				generic.Click(element);
			}
		}
	}
	
	public void fillText_to_Input() {
		
	}
	
	String getCurrentDay() {
		//Create calendar object
		Calendar calendar = Calendar.getInstance(TimeZone.getDefault());
		
		//Get current day as number
		int todayInt = calendar.get(Calendar.DAY_OF_MONTH);
		System.out.println("Today int: " + todayInt);
		String todayStr = Integer.toString(todayInt);
		return todayStr;
	}

}
